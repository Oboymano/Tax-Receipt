package taxreceipt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            String path_file = System.getProperty("user.dir")+"/src/config/Tax_receipt_form.xlsx";
            FileInputStream file = new FileInputStream(path_file);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = null;

            //Update the value of cell
            cell = sheet.getRow(row).getCell(col);
            cell.setCellValue(detail);

            file.close();

            //String timeStamp = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new java.util.Date());
           // String output_path = System.getProperty("user.dir")+"/output/"+timeStamp+".xlsx";
            FileOutputStream outFile =new FileOutputStream(new File(path_file));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeTempFile() throws IOException {
        try {
            String path_file = System.getProperty("user.dir")+"/src/config/Tax_receipt_form.xlsx";
            FileInputStream file = new FileInputStream(path_file);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            file.close();

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new java.util.Date());
            String output_path = System.getProperty("user.dir")+"/output/"+timeStamp+".xlsx";
            FileOutputStream outFile =new FileOutputStream(new File(output_path));
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
        int row = 2;
        int col = 6;
        writeXLSXFile(row, col, ""+receipt_id);
    }
    
    public static void writeDate() throws IOException {
        int row = 4;
        int col = 6;
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
        double total = 0;
        for(int i=0;i<17;i++) {
            writeXLSXFile(row+i, col, "");
            writeXLSXFile(row+i, col+1, "");
            writeXLSXFile(row+i, col+4, "");
            writeXLSXFile(row+i, col+5, "");
            writeXLSXFile(row+i, col+6, "");
        }
        for(int i=0;i<orders.size();i++) {
            writeXLSXFile(row+i, col, ""+(i+1));
            writeXLSXFile(row+i, col+1, orders.get(i).getName());
            writeXLSXFile(row+i, col+4, ""+orders.get(i).getAmount());
            writeXLSXFile(row+i, col+5, String.format("%.2f",orders.get(i).getPrice()));
            writeXLSXFile(row+i, col+6, String.format("%.2f",orders.get(i).getPrice()*orders.get(i).getAmount()));
            total += orders.get(i).getPrice()*orders.get(i).getAmount();
        }
        row =29;
        col = 6;
        writeXLSXFile(row, col, String.format("%.2f",0.93*total));
        writeXLSXFile(row+1, col, String.format("%.2f",0.07*total));
        writeXLSXFile(row+2, col, String.format("%.2f",total));
        writeAlphabet(generateString(total));
    }
    
    public static void writePayment(String payment) throws IOException {
        int row=32;
        int col=2;
        writeXLSXFile(row, col, payment);
    }
    
    public static void writeAlphabet(String contain) throws IOException {
        int row=31;
        int col=0;
        writeXLSXFile(row, col, contain);
    }
    
    public static void writeAll(Receipt receipt) throws IOException {
        writeConsumerName(receipt.getConsumerName());
        writeConsumerAddress(receipt.getConsumerAddress());
        writeConsumerTax(receipt.getCompanyTax());
        writeReceiptId(receipt.getId());
        writeDate();
        writeOrders(receipt.orders);
        writePayment(receipt.payment);
        receipt.increaseId();
        
        writeTempFile();
        Exit.start();
    }
    
    public static String generateString(double total) {
        String total_string;
        if(total%1==0) {
            total_string = Integer.toString((int)total);
            total_string+=".";
        } else
            total_string = Double.toString(total);
        System.out.println(total_string);
        ListEnum list = new ListEnum();
        String word ="";
        int old =0;
        for(int i=0;i<total_string.length();i++) {
            String base="",extend="";
            int dot = total_string.indexOf('.');
            int current = i;
            int charAt=0;
            if(total_string.charAt(i)!='.'&&dot-current>0) {
                charAt = Integer.parseInt(Character.toString(total_string.charAt(i)));
                if(charAt!=0)
                    base = list.enum_list.get(charAt).getSpell(charAt);
                if(charAt==1&&dot-current==1&&old!=0)
                    base = "เอ็ด";
                if(charAt==2&&dot-current==2)
                    base = "ยี่";
            }else if(total_string.charAt(i)!='.'&&dot-current<0){
                base = list.enum_list.get(charAt).getSpell(charAt);
            }
            switch (dot-current) {
                case 0:
                    if(i!=total_string.length()-1)
                        extend = "จุด";
                case 1:
                    extend = "";
                    break;
                case 2:
                    if(charAt!=0)
                        extend = "สิบ";
                    break;
                case 3:
                    if(charAt!=0)
                        extend = "ร้อย";
                    break;
                case 4:
                    if(charAt!=0)
                        extend = "พัน";
                    break;
                case 5:
                    if(charAt!=0)
                        extend = "หมื่น";
                    break;
                case 6:
                    if(charAt!=0)
                        extend = "แสน";
                    break;
                case 7:
                    if(charAt!=0)
                        extend = "ล้าน";
                    break;
                default:
                    extend = "";
            }
            word += base;
            word += extend;
            old = charAt;
            
        }
        System.out.println(word);
        return word;
    }
    
}

class ListEnum{
    ArrayList<Enum> enum_list = new ArrayList<Enum>();
    
    public ListEnum() {
        enum_list.add(new Enum(0,"ศูนย์"));
        enum_list.add(new Enum(1,"หนึ่ง"));
        enum_list.add(new Enum(2,"สอง"));
        enum_list.add(new Enum(3,"สาม"));
        enum_list.add(new Enum(4,"สี่"));
        enum_list.add(new Enum(5,"ห้า"));
        enum_list.add(new Enum(6,"หก"));
        enum_list.add(new Enum(7,"เจ็ด"));
        enum_list.add(new Enum(8,"แปด"));
        enum_list.add(new Enum(9,"เก้า"));
    }
    
}

class Enum{
    int number;
    String spell;
    
    public Enum (int number,String spell){
        this.number = number;
        this.spell = spell;
    }
    
    public String getSpell(int number){
        return spell;
    }
}
