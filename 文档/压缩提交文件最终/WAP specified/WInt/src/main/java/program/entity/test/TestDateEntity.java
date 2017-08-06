package program.entity.test;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class TestDateEntity {
    @Id
    String id;
    Date dateNoTime;
    Date dateTime;
}
