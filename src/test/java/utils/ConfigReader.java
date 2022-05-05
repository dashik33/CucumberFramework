package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static Properties readProperties(String filePath) {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop=new Properties(); //initializing Properties object
            prop.load(fis); //loading the file in order to read
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;

    }
    //this method gets a single value based on the key
    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }
}
