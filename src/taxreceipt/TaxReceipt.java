/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxreceipt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tubtim
 */
public class TaxReceipt extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        InitialConfig.start();
        Stock stock = new Stock();
        stock.setProduct();
        
        Order order = new Order("snack", 2, 30);
        
        Receipt receipt = new Receipt();
        receipt.setId(InitialConfig.id);
        receipt.setConsumerName("ภีมรจรัษ  ณควัชรภาณิชภูษิตนิกรโยธิน");
        receipt.setConsumerAddress("ตึก ECC");
        receipt.setCompanyTax("58010866");
        receipt.addProduct(order);
        WriteExcel.writeAll(receipt);
        
        Product product = new Product("กระเบื้อง",10);
        WriteCSV.writeCsv(0,0,"test");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
