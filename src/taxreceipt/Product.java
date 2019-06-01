package taxreceipt;

import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Product {
    private String name;
    private double price;
    
    public Product(String name,double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
}
