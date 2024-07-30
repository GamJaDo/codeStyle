package narsha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import narsha.entity.User;

@Getter
@AllArgsConstructor
public class CommentEntityResponse {

    @Schema(description = "id")
    private long id;

    @Schema(description = "user id")
    private User user_id;

    @Schema(description = "댓글 내용")
    private String contents;

}

