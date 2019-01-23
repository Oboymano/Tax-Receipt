package taxreceipt;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<Product> products = new ArrayList<Product>();
    
    public Stock() {
        
    }
    
    public void addProduct(Product product){
        this.products.add(product);
    }
    
    public int size() {
        return this.products.size();
    }
}
