package narsha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import narsha.entity.Board;
import narsha.entity.User;

@Getter
@AllArgsConstructor
public class JobPostingBoardRequest {

    @NotBlank(message = "제목 입력은 필수입니다.")
    @Schema(description = "제목")
    private String title;


    @Schema(description = "글 내용")
    private String contents;

    public Board toEntity(User user){
    return new Board(user, this.title, this.contents);
}

}
