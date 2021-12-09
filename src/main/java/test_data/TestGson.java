package test_data;

import com.google.gson.Gson;

public class TestGson {
    public static void main(String[] args) {
        //Convert from Json to Object
        String jsonObject = "{\n" +
                "  \"username\": \"Gson@mail.com\",\n" +
                "  \"password\": \"12345678\"\n" +
                "}";
        Gson gson = new Gson();
        LoginCreds loginCreds = gson.fromJson(jsonObject,LoginCreds.class);
        System.out.println(loginCreds);
        System.out.println(gson.toJson(loginCreds));
    }
}
