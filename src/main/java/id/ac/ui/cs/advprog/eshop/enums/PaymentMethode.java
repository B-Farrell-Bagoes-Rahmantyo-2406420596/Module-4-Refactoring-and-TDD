package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethode {
    VOUCHER("Voucher"),
    TRANSFER("Transfer"),
    COD("COD");
    private final String value;

    PaymentMethode(String value) {
        this.value = value;
    }
    public static boolean contains(String param) {
        for (PaymentMethode paymentMethode: PaymentMethode.values()) {
            if (paymentMethode.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
