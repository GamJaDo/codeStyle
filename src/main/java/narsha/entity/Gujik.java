package narsha.entity;

import narsha.dto.gujik.GujikResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Gujik")
public class Gujik extends Post<Caregiver, GujikResponse> {

    public Gujik() {
        super();
    }

    public Gujik(String title, String content, Caregiver author) {
        super(title, content, author);
    }

    @Override
    public GujikResponse toDto() {
        return new GujikResponse(
            this.getId(),
            this.getTitle(),
            this.getContent(),
            this.getPostImageUrl(),
            this.getCreatedAt(),
            this.getAuthor()
        );
    }
}
