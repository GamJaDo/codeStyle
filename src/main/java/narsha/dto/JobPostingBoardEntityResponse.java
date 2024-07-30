package narsha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JobPostingBoardEntityResponse {

    @Schema(description = "계시판 id")
    private long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String contents;

    @Schema(description = "수정 시간")
    private LocalDateTime editDt;

    @Schema(description = "생성 시간")
    private LocalDateTime createDt;

}

