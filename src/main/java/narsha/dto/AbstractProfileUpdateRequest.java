package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 프로필 업데이트 요청")
public abstract class AbstractProfileUpdateRequest<T extends User> {

    @Schema(description = "나이", example = "30")
    private int age;

    @Schema(description = "성별 (0: 남성, 1: 여성)", example = "0")
    private int gender;

    @Schema(description = "위치", example = "서울특별시 강남구")
    private String location;

    public AbstractProfileUpdateRequest(int age, int gender, String location) {
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    public abstract T toEntity(T existingUser);
}
