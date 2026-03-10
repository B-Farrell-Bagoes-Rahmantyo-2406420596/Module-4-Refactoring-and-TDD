package id.ac.ui.cs.advprog.eshop.model.Payment;

import id.ac.ui.cs.advprog.eshop.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public abstract class Payment {
    String id;
    String methode;
    String status;
    Map<String,String> paymentData;
    @Setter
    Order order;

    public Payment(String id){
        this.id = id;
    }

    public Payment(String id, String methode, Map<String, String> paymentData){
        this(id);
        this.paymentData = paymentData;
    }

    protected abstract void setMethode(String methode);
    public abstract void setStatus(String key);
}
