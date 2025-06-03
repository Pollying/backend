package pollying.server.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
    private int code;
    private String message;
    private Object data;
}
