package com.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment.CodPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.Payment;
import id.ac.ui.cs.advprog.eshop.model.Payment.TransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepositoryImpl();
        Payment payment1 = new VoucherPayment("PAY-1", "Voucher",new HashMap<>());
        Payment payment2 = new TransferPayment("PAY-2", "Transfer",new HashMap<>());

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
    }

    @Test
    void testSaveAndFindByIdFound() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Jln Akmal", "100");
        paymentRepository.save(new CodPayment("PAY-3", "COD", paymentData));
        Payment savedPayment = paymentRepository.findById("PAY-3");
        assertNotNull(savedPayment);
        assertEquals("PAY-3", savedPayment.getId());
        assertEquals("Voucher", savedPayment.getMethode());
        assertEquals("100", savedPayment.getPaymentData().get("Jln Akmal"));
    }

    @Test
    void testFindByIdNotFound() {
        Payment savedPayment = paymentRepository.findById("PAY-3");
        assertNull(savedPayment);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertNotNull(allPayments);
        assertEquals(2, allPayments.size());
    }

}