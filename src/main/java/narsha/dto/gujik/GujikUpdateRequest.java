package narsha.dto.gujik;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostUpdateRequest;
import narsha.entity.Gujik;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "구직 업데이트 요청")
public class GujikUpdateRequest extends PostUpdateRequest<Gujik> {

    public GujikUpdateRequest(String title, String content, String boardImageUrl) {
        super(title, content, boardImageUrl);
    }

    @Override
    public Gujik toEntity(Gujik existingBoard) {
        existingBoard.setTitle(this.getTitle());
        existingBoard.setContent(this.getContent());
        existingBoard.setPostImageUrl(this.getPostImageUrl());
        return existingBoard;
    }
}
