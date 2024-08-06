package narsha.entity;

import narsha.dto.guin.GuinResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Guin")
public class Guin extends Post<Patient, GuinResponse> {

    public Guin() {
        super();
    }

    public Guin(String title, String content, String imageUrl, Patient author) {
        super(title, content, imageUrl, author);
    }

    @Override
    public GuinResponse toDto() {
        return new GuinResponse(
            this.getId(),
            this.getTitle(),
            this.getContent(),
            this.getPostImageUrl(),
            this.getCreatedAt(),
            this.getAuthor()
        );
    }
}
