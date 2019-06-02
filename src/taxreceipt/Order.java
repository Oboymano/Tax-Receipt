package taxreceipt;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public String name;
    public String amount;
    public String price;
    
    public Order(String name,String amount,String price){
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public int getAmount() {
        return Integer.parseInt(amount);
    }
    
    public double getPrice() {
        return Double.parseDouble(price);
    }
    
}
