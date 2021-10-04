package com.util;

import com.anotations.Required;
import com.exception.BadRequestException;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public final class ServletUtil {

    public static <T> T expect(Class<T> Class, HttpServletRequest request) throws BadRequestException {
        try {
            Gson gson = new Gson();
            T pojo = gson.fromJson(request.getReader(), Class);

            Field[] fields = pojo.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(Required.class) != null) {
                    f.setAccessible(true);
                    if (f.get(pojo) == null) {
                        throw new BadRequestException("Missing field in JSON: " + f.getName());
                    }
                }
            }
            return pojo;

        } catch (IOException | IllegalAccessException e) {
            throw new BadRequestException("Invalid request payload");
        }
    }

    public static void respond(Object payload, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(payload));
    }

}

