package taxreceipt;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String name;
    private int amount;
    private double price;
    
    public Order(String name,int amount,double price){
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return name+"\t"+amount+"\t"+price;
    }
}
