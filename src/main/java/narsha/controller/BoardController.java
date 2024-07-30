package narsha.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import narsha.dto.JobPostingBoardEntityResponse;
import narsha.dto.JobPostingBoardRequest;
import narsha.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobposting")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    @Operation(summary = "게시판 생성")
    public ResponseEntity<Void> createJobPostingBoard(
            @RequestBody JobPostingBoardRequest request, BindingResult bindingResult, HttpSession session
    ) {
        boardService.createBoard(request, bindingResult, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "상세 게시판")
    public ResponseEntity<JobPostingBoardEntityResponse> findBoardById(
            @PathVariable Long id
    ) {
        JobPostingBoardEntityResponse response = boardService.findBoardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/lists")
    @Operation(summary = "게시판 목록")
    public ResponseEntity<List<JobPostingBoardEntityResponse>> findBoardPart(
            Integer howMany, Integer pageNum
    ) {
        List<JobPostingBoardEntityResponse> response = boardService.findBoardPart(howMany, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시판 삭제")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id, HttpSession session
    ) {
        boardService.deleteBoard(id, session);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
