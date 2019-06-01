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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddProductUIController implements Initializable {
    
    @FXML
    private TextField find_product;
    @FXML
    private Button search;
    @FXML
    private TableView<Order> add_order_table;
    @FXML
    private TableColumn<Order,String> nameCol;
    @FXML
    private TableColumn<Order,Integer> amountCol;
    
    @FXML
    private void handleAddProductAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddProductUI.fxml"));
        
        Scene scene = new Scene(root);
        
        TaxReceipt.stage.setScene(scene);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Order> orders = FXCollections.observableArrayList();
        nameCol.setCellValueFactory(new PropertyValueFactory<Order,String>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Order,Integer>("amount"));
        
        add_order_table.setEditable(true);
        for (int i=0;i<TaxReceipt.stock.size();i++){
            //System.out.println(TaxReceipt.stock.products.get(i).getName());
            //add_order_table.getItems().add(TaxReceipt.stock.products.get(i));
         
            orders.add(new Order(TaxReceipt.stock.products.get(i).getName(),
                    1,TaxReceipt.stock.products.get(i).getPrice()));
        }
        add_order_table.setItems(orders);

    }    
    
}
