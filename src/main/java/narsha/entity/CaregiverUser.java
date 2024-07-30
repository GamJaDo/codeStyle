package narsha.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import narsha.enums.Caring;

@Getter
@Setter
@Entity
public class CaregiverUser extends User{

    private Caring capableOfCaring;
    public CaregiverUser() {}
    public CaregiverUser(Caring capableOfCaring){
        super();
        this.capableOfCaring = capableOfCaring;
    }
}
