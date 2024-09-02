package cjy.aigis.javagis.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码和消息
 */
@Getter
@AllArgsConstructor
public enum StatusCode {
    SUCCESS(200, "成功"),
    USER_NOT_EXIST(201, "用户不存在"),
    NO_LOGIN(401, "未登录,请检查"),
    NO_PERMISSION(403, "无权限"),
    NOT_FOUND(404, "未找到"),
    SERVER_ERROR(500, "服务器错误, 请联系管理员！"),
    // token 无效
    TOKEN_INVALID(401, "token 无效"),
    PARAM_ERROR(400, "参数错误");

    private final int code;
    private final String message;
}
