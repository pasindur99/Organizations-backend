package com.util;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class ServletUtil {

    public static <T> T expect(Class<T> Class, HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(request.getReader(), Class);
    }

    public static void respond(Object payload, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(payload));
    }

}
