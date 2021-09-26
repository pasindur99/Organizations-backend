package com.util;

import com.entities.Organization;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public final class RequestUtil {

    public static Organization expect(HttpServletRequest request) throws IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();

        try {
            String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }

        Gson gson = new Gson();
        Organization organization = gson.fromJson(builder.toString(), Organization.class);

        return organization;
    }

}
