package taxreceipt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteCSV {
    public static void writeCsv(int row, int col,String detail) throws IOException {
        try {
            String path_file = ".\\src\\excel\\Products.xlsx";
            FileInputStream file = new FileInputStream(path_file);

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = null;

            //Update the value of cell
            XSSFRow sheetrow = sheet.getRow(row);
            if(sheetrow == null){
                sheetrow = sheet.createRow(row);
            }
            //Update the value of cell
            cell = sheetrow.getCell(col);
            if(cell == null){
                cell = sheetrow.createCell(col);
            }
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
}
