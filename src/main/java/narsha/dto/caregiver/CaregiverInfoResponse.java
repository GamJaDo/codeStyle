package narsha.dto.caregiver;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.UserInfoResponse;
import narsha.entity.CaregiverIntro;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 제공자 정보 응답")
public class CaregiverInfoResponse extends UserInfoResponse {

    @Schema(description = "돌봄 제공자 소개")
    private CaregiverIntro caregiverInfo;

    public CaregiverInfoResponse(String caregiverAccount, String caregiverName, String profileImageUrl, int caregiverAge,
                                 int caregiverGender, String caregiverLocation, CaregiverIntro caregiverInfo) {
        super(caregiverAccount, caregiverName, profileImageUrl, caregiverAge, caregiverGender, caregiverLocation);
        this.caregiverInfo = caregiverInfo;
    }
}
