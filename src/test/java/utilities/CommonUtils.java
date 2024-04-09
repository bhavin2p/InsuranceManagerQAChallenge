package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class CommonUtils {

    public Properties readProp(){
        Properties prop = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/test/resources/data.properties");
            prop.load(fis);

        } catch (Exception e){
            System.out.println("Error in File Input, please check the file");
        }
        return prop;
    }

}
