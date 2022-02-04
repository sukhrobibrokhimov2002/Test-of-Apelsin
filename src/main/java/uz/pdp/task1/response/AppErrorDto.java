package uz.pdp.task1.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppErrorDto implements Serializable {

    private String friendlyMessage;

    @ApiModelProperty(hidden = true)
    private String systemName;

    private String action;

    private Boolean show;

    private Boolean test;

    private String showType;

    private String fieldKey;

    private int code;

    public AppErrorDto(int code, String friendlyMessage) {
        this.code = code;
        this.friendlyMessage = friendlyMessage;
        this.show = true;
    }

    public AppErrorDto(int code, String friendlyMessage, boolean show) {
        this.code = code;
        this.friendlyMessage = friendlyMessage;
        this.show = show;
    }

    public AppErrorDto(String friendlyMessage, String systemName, int code) {
        this.friendlyMessage = friendlyMessage;
        this.systemName = systemName;
        this.code = code;
    }

    public AppErrorDto(int code, Exception e, boolean show) {
        this.code = code;
        this.show = show;
        this.friendlyMessage = e instanceof NullPointerException || e.getMessage() == null ? HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() : e.getMessage();
    }
}
