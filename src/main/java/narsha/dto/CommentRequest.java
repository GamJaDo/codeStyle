package narsha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import narsha.entity.Board;
import narsha.entity.Comment;
import narsha.entity.User;

@Getter
@AllArgsConstructor
public class CommentRequest {

    @Schema(description = "user id")
    private long board_id;

    @Schema(description = "댓글 내용")
    private String contents;

    public Comment toEntity(User user, Board board){
        return new Comment(user, board, contents);
    }

}

