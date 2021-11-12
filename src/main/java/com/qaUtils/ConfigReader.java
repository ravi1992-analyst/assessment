package com.qaUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;
    private String filePath = "src/main/resources/configFiles/config.properties";
    public Properties initiateProperties(){
        prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream(filePath);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
