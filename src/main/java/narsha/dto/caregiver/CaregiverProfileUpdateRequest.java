package narsha.dto.caregiver;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.AbstractProfileUpdateRequest;
import narsha.entity.Caregiver;
import narsha.entity.CaregiverIntro;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 제공자 프로필 업데이트 요청")
public class CaregiverProfileUpdateRequest extends AbstractProfileUpdateRequest<Caregiver> {

    @Schema(description = "돌봄 제공자 소개")
    private CaregiverIntro caregiverIntro;

    public CaregiverProfileUpdateRequest(int age, int gender, String location) {
        super(age, gender, location);
    }

    @Override
    public Caregiver toEntity(Caregiver existingCaregiver) {
        existingCaregiver.setAge(getAge());
        existingCaregiver.setGender(getGender());
        existingCaregiver.setLocation(getLocation());
        existingCaregiver.setCaregiverInfo(caregiverIntro);
        return existingCaregiver;
    }
}
