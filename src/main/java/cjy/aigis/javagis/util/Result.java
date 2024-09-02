package cjy.aigis.javagis.util;

/**
 * 统一信息封装
 */
import cjy.aigis.javagis.pojo.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> of(StatusCode statusCode, T data) {
        return Result.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> Result<T> of(StatusCode statusCode) {
        return Result.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build();
    }


    public static <T> Result<T> success(T data) {
        return of(StatusCode.SUCCESS, data);
    }

    public static <T> Result<T> userNotFound() {
        return of(StatusCode.USER_NOT_EXIST);
    }

    public static <T> Result<T> noLogin() {
        return of(StatusCode.NO_LOGIN);
    }

    public static <T> Result<T> noPermission() {
        return of(StatusCode.NO_PERMISSION);
    }
    // token 无效
    public static <T> Result<T> tokenInvalid() {
        return of(StatusCode.TOKEN_INVALID);
    }

    public static <T> Result<T> notFound() {
        return of(StatusCode.NOT_FOUND);
    }

    public static <T> Result<T> serverError() {
        return of(StatusCode.SERVER_ERROR);
    }

    public static <T> Result<T> paramError() {
        return of(StatusCode.PARAM_ERROR);
    }
}

