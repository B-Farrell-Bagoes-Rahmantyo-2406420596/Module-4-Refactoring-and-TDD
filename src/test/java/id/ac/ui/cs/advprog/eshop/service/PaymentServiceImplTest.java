package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment.Payment;
import id.ac.ui.cs.advprog.eshop.model.Payment.TransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;
    private Map<String, String> voucherDataValid;
    private Map<String, String> voucherDataInvalid;
    private Map<String, String> transferDataValid;
    private Map<String, String> transferDataInvalid;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product dummyProduct = mock(Product.class);
        products.add(dummyProduct);
        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", products, 1708560000L, "Bagoes");

        voucherDataValid = new HashMap<>();
        voucherDataValid.put("voucherCode", "ESHOP12345678NUM");

        voucherDataInvalid = new HashMap<>();
        voucherDataInvalid.put("voucherCode", "ESHOP12345");

        transferDataValid = new HashMap<>();
        transferDataValid.put("bankName", "BCA");

        transferDataInvalid = new HashMap<>();
        transferDataInvalid.put("bankName", "");
    }

    @Test
    void testAddPaymentVoucher() {
        when(paymentRepository.addPayment(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.addPayment(order, "Voucher", voucherDataValid);

        assertTrue(result instanceof VoucherPayment);
        assertEquals("Voucher", result.getMethode());
        assertEquals(voucherDataValid, result.getPaymentData());
        assertEquals(order, result.getOrder());
        verify(paymentRepository, times(1)).addPayment(any(Payment.class));
    }


    @Test
    void testSetStatusToSuccess_WithValidVoucher() {
        Payment payment = new VoucherPayment("payment-1", "Voucher", voucherDataValid);
        payment.setOrder(order);

        when(paymentRepository.addPayment(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, "voucherCode");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", result.getOrder().getStatus());
        verify(paymentRepository, times(1)).addPayment(payment);
    }

    @Test
    void testSetStatusToRejected_WithInvalidVoucher() {
        Payment payment = new VoucherPayment("payment-2", "Voucher", voucherDataInvalid);
        payment.setOrder(order);

        when(paymentRepository.addPayment(any(Payment.class))).thenReturn(payment);


        Payment result = paymentService.setStatus(payment, "voucherCode");

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", result.getOrder().getStatus());
        verify(paymentRepository, times(1)).addPayment(payment);
    }

    @Test
    void testSetStatusToSuccess_WithValidTransfer() {
        Payment payment = new TransferPayment("payment-3", "Transfer", transferDataValid);
        payment.setOrder(order);

        when(paymentRepository.addPayment(any(Payment.class))).thenReturn(payment);

        // Service menerima "bankName" sebagai key untuk TransferPayment
        Payment result = paymentService.setStatus(payment, "bankName");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", result.getOrder().getStatus());
        verify(paymentRepository, times(1)).addPayment(payment);
    }

    @Test
    void testSetStatusToRejected_WithInvalidTransfer() {
        Payment payment = new TransferPayment("payment-4", "Transfer", transferDataInvalid);
        payment.setOrder(order);

        when(paymentRepository.addPayment(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, "bankName");

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", result.getOrder().getStatus());
        verify(paymentRepository, times(1)).addPayment(payment);
    }

    @Test
    void testGetPaymentByIdSuccess() {
        Payment payment = new VoucherPayment("payment-1", "Voucher", voucherDataValid);
        when(paymentRepository.getPayment("payment-1")).thenReturn(payment);

        Payment result = paymentService.getPayment("payment-1");

        assertNotNull(result);
        assertEquals("payment-1", result.getId());
        assertEquals("Voucher", result.getMethode());
    }

    @Test
    void testGetAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new VoucherPayment("payment-1", "Voucher", voucherDataValid));
        when(paymentRepository.getAllPayments()).thenReturn(paymentList);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}