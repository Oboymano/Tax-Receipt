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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class AddProductUIController implements Initializable {
    
    @FXML
    private TextField find_product;
    @FXML
    private Button search;
    @FXML
    private TableView<Order> order_table;
    @FXML
    private TableColumn<Order,String> nameOrderCol;
    @FXML
    private TableColumn<Order,Number> amountOrderCol;
    @FXML
    private TableColumn<Product,String> nameProductCol;
    @FXML
    private TableColumn<Product,Number> priceProductCol;
    @FXML
    private TableView<Product> product_table;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button submit;
    
    ObservableList<Product> products = FXCollections.observableArrayList();
    ObservableList<Product> filter = FXCollections.observableArrayList();
    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException {
        submit.getScene().getWindow().hide();
        /*
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        TaxReceipt.main_scene.setRoot(root);*/
    }
    
    @FXML 
    private void handleAddAction(ActionEvent event) throws IOException {
        Product selected_product = product_table.getSelectionModel().getSelectedItem();
        MainUIController.orders.add(new Order(selected_product.name,"1",selected_product.price));
        order_table.setItems(MainUIController.orders);
    }
    
    @FXML
    private void handleRemoveAction(ActionEvent event) throws IOException {
        Order selected_order = order_table.getSelectionModel().getSelectedItem();
        MainUIController.orders.remove(selected_order);
        order_table.setItems(MainUIController.orders);
    }
    
    @FXML
    private void handleOnChangeAction(ActionEvent event) throws IOException {
        System.out.println();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        product_table.setEditable(true);
        order_table.setEditable(true);
        nameProductCol.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        nameOrderCol.setCellValueFactory(new PropertyValueFactory<Order,String>("name"));
        
        //nameOrderCol.setCellFactory(TextFieldTableCell.forTableColumn());
        priceProductCol.setCellValueFactory(new PropertyValueFactory<Product,Number>("price"));
        amountOrderCol.setCellValueFactory(new PropertyValueFactory<Order,Number>("amount"));
        
        amountOrderCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        for (int i=0;i<TaxReceipt.stock.size();i++){
         
            products.add(new Product(TaxReceipt.stock.products.get(i).getName(),
                    TaxReceipt.stock.products.get(i).price));
        }
        filter = products;
        if(products.size()!=0)
            product_table.setItems(products);
        if(MainUIController.orders.size()!=0)
            order_table.setItems(MainUIController.orders);
        amountOrderCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Order, Number>>() {
                @Override
                public void handle(CellEditEvent<Order, Number> t) {
                    int index = MainUIController.orders.indexOf(t.getTableView().getItems().get( t.getTablePosition().getRow()));
                    MainUIController.orders.get(index).setAmount(Integer.toString(t.getNewValue().intValue()));
                }
            }
        );
        find_product.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!="") {
                filter = FXCollections.observableArrayList();
                for(int i=0;i<products.size();i++) {
                    if(products.get(i).name.indexOf(newValue)!=-1) {
                        filter.add(products.get(i));
                    }
                }
            }else {
                filter=products;
            }
            product_table.setItems(filter);
            product_table.refresh();
        });
    }    
    
}
