package taxreceipt;

import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Product {
    public String name;
    public String price;
    
    public Product(String name,String price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice() {
        return Double.parseDouble(this.price);
    }
    
}
