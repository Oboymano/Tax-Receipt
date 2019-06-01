
package taxreceipt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import static taxreceipt.InitialConfig.id;

public class Exit {
    public static void start(){
        try{
            Properties prop = new Properties();
            String path_file = System.getProperty("user.dir")+"/src/config/config.properties";
            OutputStream file = new FileOutputStream(path_file);
            
            prop.setProperty("id", Integer.toString(Receipt.receipt_id));
            prop.store(file,null);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
