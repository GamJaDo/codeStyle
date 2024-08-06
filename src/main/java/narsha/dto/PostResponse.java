package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.entity.User;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "게시물 응답")
public abstract class PostResponse<T extends User> {

    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "제목", example = "Sample Title")
    private String title;

    @Schema(description = "내용", example = "Sample Content")
    private String content;

    @Schema(description = "이미지 URL", example = "http://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "생성일시", example = "2024-08-05T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "작성자 이름", example = "홍길동")
    private String authorName;

    protected PostResponse(Long id, String title, String content, String imageUrl, LocalDateTime createdAt, T author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.authorName = author.getName();
    }
}
