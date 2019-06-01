/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxreceipt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainUIController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField consumer_tax;
    @FXML
    private TextField consumer_name;
    @FXML
    private TextField consumer_address;
    @FXML
    private TableView order_table;
    @FXML
    private SplitMenuButton add_order;
    @FXML
    private Button submit;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException {
        TaxReceipt.receipt.setId(InitialConfig.id);
        TaxReceipt.receipt.setConsumerName(consumer_name.getText());
        TaxReceipt.receipt.setConsumerAddress(consumer_address.getText());
        TaxReceipt.receipt.setConsumerTax(consumer_tax.getText());
        //receipt.addProduct(order);
        WriteExcel.writeAll(TaxReceipt.receipt);
    }
    
    @FXML
    private void handleAddProductAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddProductUI.fxml"));
        
        Scene scene = new Scene(root);
        
        TaxReceipt.stage.setScene(scene);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Order snack = new Order("kitkat",2,20.3);
        Receipt receipt = new Receipt("Google","เกกีงาม 1","58010866");
        receipt.addProduct(snack);
        System.out.println(receipt.orders.get(0).toString());

    }    
    
}