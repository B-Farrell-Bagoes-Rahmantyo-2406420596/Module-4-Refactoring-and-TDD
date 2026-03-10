package id.ac.ui.cs.advprog.eshop.model.Payment;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class VoucherPayment extends Payment {
    public VoucherPayment(String id, String methode, Map<String, String> paymentData) {
        super(id, methode, paymentData);
        setMethode(methode);
    }

    @Override
    protected void setMethode(String methode){
        if (methode.equals("Voucher")){
            this.methode = methode;
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setStatus(String key) {
        String value = paymentData.get(key);
        if (value != null && value.substring(0,5).equals("ESHOP") &&
        value.length() == 16 && countNumericChar(value) == 8){
            this.status = PaymentStatus.SUCCESS.getValue();
        } else{
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private int countNumericChar(String input){
        int count = 0;
        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                count++;
            }
        }
        return count;
    }
}
