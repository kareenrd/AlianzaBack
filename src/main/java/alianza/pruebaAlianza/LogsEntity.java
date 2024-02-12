package alianza.pruebaAlianza;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class LogsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String log;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date create;
}
