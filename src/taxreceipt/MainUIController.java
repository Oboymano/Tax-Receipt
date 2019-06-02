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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

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
    private TableView<Order> summary_table;
    @FXML
    private TableColumn<Order,String> nameSummaryCol;
    @FXML
    private TableColumn<Order,Number> amountSummaryCol;
    @FXML
    private SplitMenuButton add_order;
    @FXML
    private Button submit;
    @FXML
    public RadioButton cash_radio;
    @FXML
    private RadioButton option_radio;
    @FXML
    private TextField option;
    Scene add_scene;
    public static ObservableList<Order> orders = FXCollections.observableArrayList();
    public static ObservableList<Order> print = FXCollections.observableArrayList();
    
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
        if(cash_radio.isSelected())
            TaxReceipt.receipt.payment = "เงินสด";
        else
            TaxReceipt.receipt.payment = option.getText();
        for(int i=0;i<orders.size();i++) {
            TaxReceipt.receipt.addProduct(orders.get(i));
        }
        WriteExcel.writeAll(TaxReceipt.receipt);
    }
    
    
    @FXML
    private void handleAddProductAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProductUI.fxml"));
        //TaxReceipt.main_scene.setRoot(root);
        Stage stage = new Stage();
        stage.initOwner(submit.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));
        stage.showAndWait();

        //controller.orders
        
        nameSummaryCol.setCellValueFactory(new PropertyValueFactory<Order,String>("name"));
        amountSummaryCol.setCellValueFactory(new PropertyValueFactory<Order,Number>("amount"));
        //amountSummaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        summary_table.setItems(orders);
        summary_table.refresh();
        //text1.setText(controller.getText());
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cash_radio.selectedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
            if (isNowSelected) { 
                cash_radio.setSelected(true);
                option_radio.setSelected(false);
                option.setVisible(false);
            } else {
                option.setVisible(true);
            }
        }
        });
        
        option_radio.selectedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
            if (isNowSelected) {
                option_radio.setSelected(true);
                cash_radio.setSelected(false);
                option.setVisible(true);
            } else {
                option.setVisible(true);
                
            }
        }
        });

    }    
    
}
