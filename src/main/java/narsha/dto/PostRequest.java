package narsha.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import narsha.entity.Post;
import narsha.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "게시물 요청")
public abstract class PostRequest<T extends Post<U, ?>, U extends User> {

    @Schema(description = "제목", example = "Sample Title")
    private String title;

    @Schema(description = "내용", example = "Sample Content")
    private String content;
    
    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public abstract T toEntity(U author);
}
