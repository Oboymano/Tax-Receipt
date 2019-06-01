package taxreceipt;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Stock {
    private List<Product> products = new ArrayList<Product>();
    
    public Stock() {
        
    }
    
    public void setProduct(){
        try{
            String path_file = System.getProperty("user.dir")+"/src/config/Products.xlsx";
            FileInputStream file = new FileInputStream(path_file);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                Cell cell_0=row.getCell(0);
                Cell cell_1=row.getCell(1);
                Product tmp = new Product(cell_0.getStringCellValue(),cell_1.getNumericCellValue());
                addProduct(tmp);
            }
            printStock();
        } catch(Exception io) {
            
        }
    }
    
    public void addProduct(Product product){
        this.products.add(product);
    }
    
    public int size() {
        return this.products.size();
    }
    
    public void printStock(){
        for(int i=0;i<this.products.size();i++){
            System.out.println(this.products.get(i).getName()+" "+this.products.get(i).getPrice());
        }
    }
}
