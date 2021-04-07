package com.kbldemo.config.http.response;

import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by kbl on 2019/7/12.
 */
public class HttpHelperResult {
    public HttpHelperResult(ServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(message);
    }

}
