package uz.pdp.task1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result3 {

    private String message;
    private boolean status;
    private Object object;
}
