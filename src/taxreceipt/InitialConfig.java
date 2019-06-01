package taxreceipt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class InitialConfig {
    
    public static int id ;
    
    public static void start(){
        try{
            Properties prop = new Properties();
            String path_file = System.getProperty("user.dir")+"/src/config/config.properties";
            FileInputStream file = new FileInputStream(path_file);
            
            prop.load(file);
            String id_string = prop.getProperty("id");
            id = Integer.parseInt(id_string);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
