package narsha.dto.guin;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostUpdateRequest;
import narsha.entity.Guin;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "구인 업데이트 요청")
public class GuinUpdateRequest extends PostUpdateRequest<Guin> {

    public GuinUpdateRequest(String title, String content, String boardImageUrl) {
        super(title, content, boardImageUrl);
    }

    @Override
    public Guin toEntity(Guin existingBoard) {
        existingBoard.setTitle(this.getTitle());
        existingBoard.setContent(this.getContent());
        existingBoard.setPostImageUrl(this.getPostImageUrl());
        return existingBoard;
    }
}
