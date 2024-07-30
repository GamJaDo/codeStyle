package narsha.service;

import jakarta.servlet.http.HttpSession;
import narsha.dto.BoardEntityResponse;
import narsha.dto.BoardRequest;
import narsha.entity.Board;
import narsha.entity.User;
import narsha.enums.BoardTag;
import narsha.exception.InvalidRegisterException;
import narsha.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.webjars.NotFoundException;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final AuthService authService;

    public BoardService(BoardRepository boardRepository, AuthService authService) {
        this.boardRepository = boardRepository;
        this.authService = authService;
    }

    public void createBoard(BoardRequest request, BindingResult bindingResult, HttpSession session) {
        validateBindingResult(bindingResult);

        User user = authService.getUserFromSession(session);
        Board board = request.toEntity(user, request.getBoardTag());
        boardRepository.save(board);
    }

    public BoardEntityResponse findBoardById(Long id) {
        checkBoardExists(id);

        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        return new BoardEntityResponse(board.getId(), board.getTitle(), board.getContents(), board.getEditDt(), board.getCreateDt());
    }

    public List<BoardEntityResponse> findBoardPart(BoardTag boardTag, Integer howMany, Integer pageNum) {
        validatePaginationParameters(howMany, pageNum);
        validateUserTag(boardTag);

        Pageable pageable = PageRequest.of(pageNum, howMany);
        Page<Board> page = boardRepository.findAllByBoardTag(boardTag,pageable);

        return page.getContent().stream()
                .map(board -> new BoardEntityResponse(
                                board.getId(),
                                board.getTitle(),
                                board.getContents(),
                                board.getEditDt(),
                                board.getCreateDt()
                        )
                )
                .collect(Collectors.toList());
    }

    public void deleteBoard(Long id, HttpSession session) {
        checkBoardExists(id);

        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        authService.isAuthenticated(session, board.getUser().getId());

        boardRepository.delete(board);
    }

    private void validateBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Unknown error";
            throw new InvalidRegisterException(errorMessage);
        }
    }

    // ID 존재 여부 검사
    private void checkBoardExists(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new NotFoundException("Post not found with id " + id);
        }
    }

    // 페이징 파라미터 유효성 검사
    private void validatePaginationParameters(Integer howMany, Integer pageNum) {
        if (howMany == null || howMany <= 0) {
            throw new IllegalArgumentException("howMany must be greater than 0");
        }
        if (pageNum == null || pageNum < 0) {
            throw new IllegalArgumentException("pageNum must be 0 or greater");
        }
    }

    private void validateUserTag(BoardTag boardTag) {
        if (boardTag == null || !EnumSet.allOf(BoardTag.class).contains(boardTag)) {
            throw new IllegalArgumentException("Invalid UserTag value");
        }
    }

}
