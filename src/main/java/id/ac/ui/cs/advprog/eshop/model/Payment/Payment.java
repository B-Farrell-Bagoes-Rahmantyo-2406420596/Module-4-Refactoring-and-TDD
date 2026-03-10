package id.ac.ui.cs.advprog.eshop.model.Payment;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public abstract class Payment {
    String id;
    String methode;
    String status;
    Map<String,String> paymentData;

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
