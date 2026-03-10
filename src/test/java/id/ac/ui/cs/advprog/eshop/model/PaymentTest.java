package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Payment.CodPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.Payment;
import id.ac.ui.cs.advprog.eshop.model.Payment.TransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void createVoucherPaymentSuccess() {
        String id = "PAY123";
        String methode = "Voucher";
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new VoucherPayment(id, methode, paymentData);
        assertEquals(id, payment.getId());
        assertEquals(methode, payment.getMethode());
        assertEquals("ESHOP1234ABC5678",
                payment.getPaymentData().get("voucherCode"));
        payment.setStatus("voucherCode");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void createVoucherPaymentFailedIfWrongValue() {
        String id = "PAY123";
        String methode = "Voucher";
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123ABCDEFGH");
        Payment payment = new VoucherPayment(id, methode, paymentData);
        payment.setStatus("voucherCode");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void createVoucherPaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new VoucherPayment("PAY123", "MEOW"
                    ,new HashMap<String, String>());
        });
    }

    @Test
    void createTransferPaymentSuccess() {
        String id = "PAY123";
        String methode = "Transfer";
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("BNI", "ESHOP-BNI-011");
        Payment payment = new TransferPayment(id, methode, paymentData);
        assertEquals(id, payment.getId());
        assertEquals(methode, payment.getMethode());
        assertEquals("ESHOP-BNI-011",
                payment.getPaymentData().get("BNI"));
        payment.setStatus("BNI");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void createTransferPaymentFailedIfWrongValue() {
        String id = "PAY123";
        String methode = "Transfer";
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("BNI", "");
        Payment payment = new TransferPayment(id, methode, paymentData);
        payment.setStatus("BNI");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void createTransferPaymentFailedIfNoKey() {
        String id = "PAY123";
        String methode = "Transfer";
        Map<String,String> paymentData = new HashMap<>();
        Payment payment = new TransferPayment(id, methode, paymentData);
        payment.setStatus("BNI");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void createTransferPaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new TransferPayment("PAY123", "Voucher"
                    ,new HashMap<String, String>());
        });
    }

    @Test
    void createCodPaymentSuccess() {
        String id = "PAY123";
        String methode = "COD";
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("Jln Akmal", "20000");
        Payment payment = new CodPayment(id, methode, paymentData);
        assertEquals(id, payment.getId());
        assertEquals(methode, payment.getMethode());
        assertEquals("20000",
                payment.getPaymentData().get("Jln Akmal"));
        payment.setStatus("Jln Akmal");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void createCodPaymentFailedIfWrongValue() {
        String id = "PAY123";
        String methode = "COD";
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("Jln Peter", "");
        Payment payment = new CodPayment(id, methode, paymentData);
        payment.setStatus("Jln Peter");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void createCodPaymentFailedIfNoKey() {
        String id = "PAY123";
        String methode = "COD";
        Map<String,String> paymentData = new HashMap<>();
        Payment payment = new CodPayment(id, methode, paymentData);
        payment.setStatus("Jln Akmal");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void createCodPaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new CodPayment("PAY123", "Voucher"
                    ,new HashMap<String, String>());
        });
    }

}
