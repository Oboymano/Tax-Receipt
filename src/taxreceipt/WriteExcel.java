package taxreceipt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
    public static void writeXLSXFile(int row, int col,String detail) throws IOException {
        try {
            String path_file = "C:\\Users\\NEEN\\Documents\\GitHub\\Tax-Receipt\\src\\excel\\Tax_receipt_form.xlsx";
            FileInputStream file = new FileInputStream(path_file);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = null;

            //Update the value of cell
            cell = sheet.getRow(row).getCell(col);
            cell.setCellValue(detail);

            file.close();

            FileOutputStream outFile =new FileOutputStream(new File(path_file));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeConsumerName(String consumer_name) throws IOException {
        int row = 7;
        int col = 1;
        writeXLSXFile(row, col, consumer_name);
    }
    
    public static void writeConsumerAddress(String consumer_address) throws IOException {
        int row = 8;
        int col = 1;
        writeXLSXFile(row, col, consumer_address);
    }
    
    public static void writeReceiptId(int receipt_id) throws IOException {
        int row = 7;
        int col = 5;
        writeXLSXFile(row, col, ""+receipt_id);
    }
    
    public static void writeDate() throws IOException {
        int row = 8;
        int col = 5;
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        writeXLSXFile(row, col, timeStamp);
    }
    
    public static void writeConsumerTax(String consumer_tax) throws IOException {
        int row = 9;
        int col = 2;
        writeXLSXFile(row, col, consumer_tax);
    }
    
    public static void writeOrders(List<Order> orders) throws IOException {
        int row = 12;
        int col = 0;
        for(int i=0;i<orders.size();i++) {
            writeXLSXFile(row, col, ""+(i+1));
            writeXLSXFile(row, col+1, orders.get(i).getName());
            writeXLSXFile(row, col+3, ""+orders.get(i).getAmount());
            writeXLSXFile(row, col+4, ""+orders.get(i).getPrice());
            writeXLSXFile(row, col+5, ""+(orders.get(i).getPrice()*orders.get(i).getAmount()));
        }
    }
    
    public static void writeAll(Receipt receipt) throws IOException {
        writeConsumerName(receipt.getConsumerName());
        writeConsumerAddress(receipt.getConsumerAddress());
        writeConsumerTax(receipt.getCompanyTax());
        writeReceiptId(receipt.getId());
        writeDate();
        writeOrders(receipt.orders);
    }
    
}
