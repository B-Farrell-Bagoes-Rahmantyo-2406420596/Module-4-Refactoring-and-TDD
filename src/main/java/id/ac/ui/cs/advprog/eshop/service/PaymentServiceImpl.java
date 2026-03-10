package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment.CodPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.Payment;
import id.ac.ui.cs.advprog.eshop.model.Payment.TransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = null;

        if ("Voucher".equals(method)) {
            payment = new VoucherPayment(paymentId, method, paymentData);
        } else if ("Transfer".equals(method)) {
            payment = new TransferPayment(paymentId, method, paymentData);
        } else if ("COD".equals(method)) {
            payment = new CodPayment(paymentId, method, paymentData);
        } else {
            throw new IllegalArgumentException("Metode pembayaran tidak valid");
        }

        payment.setOrder(order);

        return paymentRepository.addPayment(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String key) {
        payment.setStatus(key);

        if ("SUCCESS".equals(payment.getStatus())) {
            payment.getOrder().setStatus("SUCCESS");
        } else if ("REJECTED".equals(payment.getStatus())) {
            payment.getOrder().setStatus("FAILED");
        }

        return paymentRepository.addPayment(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}