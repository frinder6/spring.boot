package spring.controller;

import lombok.Data;

/**
 * Created by frinder6 on 2016/12/21.
 */
@Data
public class Response {

    public static final Integer SUCCESS = 200;

    public static final Integer ERROR = 404;

    private Integer status;
    private String message;

    private Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * @return
     */
    public static Response sucess() {
        return new Response(SUCCESS, "成功");
    }

    /**
     * @return
     */
    public static Response error() {
        return new Response(ERROR, "成功");
    }

}
