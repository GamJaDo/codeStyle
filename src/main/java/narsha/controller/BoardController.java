package narsha.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import narsha.dto.BoardEntityResponse;
import narsha.dto.BoardRequest;
import narsha.enums.BoardTag;
import narsha.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/jobposting/create")
    @Operation(summary = "구인 게시판 생성")
    public ResponseEntity<Void> createJobPostBoard(
            @RequestBody BoardRequest request, BindingResult bindingResult, HttpSession session
    ) {
        boardService.createBoard(request, bindingResult, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/jobposting/{id}")
    @Operation(summary = "구인 상세 게시판")
    public ResponseEntity<BoardEntityResponse> findJobPostBoardById(
            @PathVariable Long id
    ) {
        BoardEntityResponse response = boardService.findBoardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/jobposting/lists")
    @Operation(summary = "구인 게시판 전체 목록")
    public ResponseEntity<List<BoardEntityResponse>> findJobPostBoardPart(
            Integer howMany, Integer pageNum
    ) {
        List<BoardEntityResponse> response = boardService.findBoardPart(BoardTag.jobPosting,howMany, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/jobsearch/create")
    @Operation(summary = "구직 게시판 생성")
    public ResponseEntity<Void> createJobSearchBoard(
            @RequestBody BoardRequest request, BindingResult bindingResult, HttpSession session
    ) {
        boardService.createBoard(request, bindingResult, session);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/jobsearch/{id}")
    @Operation(summary = "구직 상세 게시판")
    public ResponseEntity<BoardEntityResponse> findJobSearchBoardById(
            @PathVariable Long id
    ) {
        BoardEntityResponse response = boardService.findBoardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/jobsearch/lists")
    @Operation(summary = "구직 게시판 전체 목록")
    public ResponseEntity<List<BoardEntityResponse>> findJobSearchBoardPart(
            Integer howMany, Integer pageNum
    ) {
        List<BoardEntityResponse> response = boardService.findBoardPart(BoardTag.jobSearch, howMany, pageNum);
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
