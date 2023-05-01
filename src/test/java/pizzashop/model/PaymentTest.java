package pizzashop.model;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTest{
    @Test
    public void testGetTableNumber() {
        Payment payment = new Payment(1, PaymentType.Cash, 10.0);
        assertEquals(1, payment.getTableNumber());
    }

    @Test
    public void testSetTableNumber() {
        Payment payment = new Payment(1, PaymentType.Cash, 10.0);
        payment.setTableNumber(2);
        assertEquals(2, payment.getTableNumber());
    }
    @Test
    public void testGetAmount() {
        Payment payment = new Payment(1, PaymentType.Cash, 10.0);
        assertEquals(10.0, payment.getAmount(), 0.0);
    }

    @Test
    public void testSetAmount() {
        Payment payment = new Payment(1, PaymentType.Cash, 10.0);
        payment.setAmount(20.0);
        assertEquals(20.0, payment.getAmount(), 0.0);
    }
}