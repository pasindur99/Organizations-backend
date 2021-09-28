package com.util;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class ServletUtil {

    public static <T> T expect(HttpServletRequest request , Class<T> Class) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(request.getReader(), Class);
    }

    public static void respond(HttpServletResponse response, Object payload) throws IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(payload));
    }

}
