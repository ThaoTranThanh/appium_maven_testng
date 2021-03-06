package test_data.authentication;

import com.google.gson.Gson;
import test_data.LoginCreds;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {
    public static <T>  T buildDataObject(String filePath, Class<T> dataType){
//        LoginCreds[] loginCreds = new LoginCreds[]{};

        String absoluteFilePath = System.getProperty("user.dir") + filePath;
        try(
                Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));
        ){
            // Create Gson instance
            Gson gson = new Gson();

            // Convert json array data intro LoginCreds []
            return gson.fromJson(reader,dataType);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
