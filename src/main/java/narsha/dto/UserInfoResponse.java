package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 정보 응답")
public class UserInfoResponse {

    @Schema(description = "계정", example = "user123")
    private String account;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "프로필 이미지 URL", example = "http://example.com/profile.jpg")
    private String profileImageUrl;

    @Schema(description = "나이", example = "30")
    private int age;

    @Schema(description = "성별 (0: 남성, 1: 여성)", example = "0")
    private int gender;

    @Schema(description = "위치", example = "서울특별시 강남구")
    private String location;

    protected UserInfoResponse(String account, String name, String profileImageUrl, int age, int gender, String location) {
        this.account = account;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }
}
