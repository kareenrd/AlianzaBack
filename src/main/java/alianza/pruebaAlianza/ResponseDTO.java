package alianza.AlianzaBack;

import lombok.Data;

@Data
public class ResponseDTO {
    private Integer code;
    private String message;
    private Object data;
}
