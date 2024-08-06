package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 제안 응답")
public class CareOfferResponse {

    @Schema(description = "돌봄 제공자 이름", example = "홍길동")
    private String caregiverName;

    @Schema(description = "돌봄 제공자 프로필 이미지 URL", example = "http://example.com/profile.jpg")
    private String caregiverProfileImageUrl;

    @Schema(description = "일일 요금", example = "100000")
    private int dailyRate;

    @Schema(description = "총 금액", example = "700000")
    private int totalAmount;

    public CareOfferResponse(String caregiverName, String caregiverProfileImageUrl, int dailyRate, int totalAmount) {
        this.caregiverName = caregiverName;
        this.caregiverProfileImageUrl = caregiverProfileImageUrl;
        this.dailyRate = dailyRate;
        this.totalAmount = totalAmount;
    }
}
