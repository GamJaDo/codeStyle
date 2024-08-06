package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "게시물 업데이트 요청")
public abstract class PostUpdateRequest<T extends Post<?, ?>> {

    @Schema(description = "제목", example = "Updated Title")
    private String title;

    @Schema(description = "내용", example = "Updated Content")
    private String content;

    @Schema(description = "게시물 이미지 URL", example = "http://example.com/updated-image.jpg")
    private String postImageUrl;

    public PostUpdateRequest(String title, String content, String postImageUrl) {
        this.title = title;
        this.content = content;
        this.postImageUrl = postImageUrl;
    }

    public abstract T toEntity(T existingPost);
}
