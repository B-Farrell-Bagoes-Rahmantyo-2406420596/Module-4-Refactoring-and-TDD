package id.ac.ui.cs.advprog.eshop.model.Payment;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class CodPayment extends Payment {
    public CodPayment(String id, String methode, Map<String, String> paymentData) {
        super(id, methode, paymentData);
        setMethode(methode);
    }

    @Override
    public void setMethode(String methode){
        if (methode.equals("COD")){
            this.methode = methode;
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setStatus(String key) {
        String value = paymentData.get(key);
        if (value != null && !value.isEmpty()){
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }
}
