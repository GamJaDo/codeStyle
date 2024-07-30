package narsha.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import narsha.dto.CommentEntityRequest;
import narsha.dto.CommentEntityResponse;
import narsha.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    @Operation(summary = "댓글생성")
    public ResponseEntity<Void> createComment(
            @RequestBody CommentEntityRequest request, BindingResult bindingResult, HttpSession session
    ) {
        commentService.createComment(request, bindingResult, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시판 ID에 포함 되어 있는 댓글보기")
    public ResponseEntity<List<CommentEntityResponse>> findCommentByBoardId(
            @PathVariable Long id
    ) {
        List<CommentEntityResponse> response = commentService.findCommentByBoardId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id, HttpSession session
    ) {
        commentService.deleteComment(id, session);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
