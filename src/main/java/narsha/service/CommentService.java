package narsha.service;

import jakarta.servlet.http.HttpSession;
import narsha.dto.CommentRequest;
import narsha.dto.CommentResponse;
import narsha.entity.Board;
import narsha.entity.Comment;
import narsha.entity.User;
import narsha.exception.InvalidRegisterException;
import narsha.repository.BoardRepository;
import narsha.repository.CommentRepository;
import narsha.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository, UserRepository userRepository, AuthService authService) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public void createComment(CommentRequest request, BindingResult bindingResult, HttpSession session) {
        validateBindingResult(bindingResult);

        User user = authService.getUserFromSession(session);
        Board board = boardRepository.findById(request.getBoard_id())
                .orElseThrow(() -> new NotFoundException("Board not found with id " + request.getBoard_id()));
        Comment comment = request.toEntity(user, board);
        commentRepository.save(comment);
    }

    public List<CommentResponse> findCommentByBoardId(long boardId) {
        checkBoardExists(boardId);

        return commentRepository.findAll().stream()
                        .filter(c -> c.getBoard().getId() == boardId)
                        .map(comment -> new CommentResponse(comment.getId(), comment.getUser(), comment.getContents()))
                        .collect(Collectors.toList());
    }

    public List<CommentResponse> findCommentByUserId(long userId) {
        checkUserExists(userId);

        return commentRepository.findAll().stream()
                .filter(c -> c.getUser().getId() == userId)
                .map(comment -> new CommentResponse(comment.getId(), comment.getUser(), comment.getContents()))
                .collect(Collectors.toList());
    }

    public CommentResponse findCommentById(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id " + id));
        return new CommentResponse(comment.getId(), comment.getUser(), comment.getContents());
    }

    public void deleteComment(Long id, HttpSession session) {
        authService.getUserFromSession(session);

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id " + id));
        authService.isAuthenticated(session, comment.getUser().getId());
        commentRepository.delete(comment);
    }

    private void validateBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Unknown error";
            throw new InvalidRegisterException(errorMessage);
        }
    }

    private void checkBoardExists(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new NotFoundException("Board not found with id " + id);
        }
    }

    private void checkUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with id " + id);
        }
    }
}