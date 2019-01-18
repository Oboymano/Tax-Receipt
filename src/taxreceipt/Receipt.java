package taxreceipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    
    private String company_name;
    private String company_address;
    private String company_tax;
    private String consumer_name;
    private String consumer_address;
    private int receipt_id;
    public List<Order> orders = new ArrayList<Order>();
    
    public Receipt(String company_name,String company_address,String company_tax) {
        this.company_name = company_name;
        this.company_address = company_address;
        this.company_tax = company_tax;
        this.receipt_id = 0;
    }
    
    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }
    
    public String getCompanyName() {
        return company_name;
    }
    
    public void setCompanyAddress(String company_address) {
        this.company_address = company_address;
    }
    
    public String getCompanyAddress() {
        return company_address;
    }
    
    public void setCompanyTax(String company_tax) {
        this.company_tax = company_tax;
    }
    
    public String getCompanyTax() {
        return company_tax;
    }
    
    public void setConsumerName(String consumer_name) {
        this.consumer_name = consumer_name;
    }
    
    public String getConsumerName() {
        return consumer_name;
    }
    
    public void setConsumerAddress(String consumer_address) {
        this.consumer_address = consumer_address;
    }
    
    public String getConsumerAddress() {
        return consumer_address;
    }
    
    public void increaseId() {
        this.receipt_id++;
    }
    
    public int getId() {
        return receipt_id;
    }
    
    public void addProduct(Order order) {
        this.orders.add(order);
    }
    
}
