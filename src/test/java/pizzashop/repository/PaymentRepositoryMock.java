package pizzashop.repository;

import pizzashop.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryMock implements IPaymentRepository{
    List<Payment> payments = new ArrayList<>();
    @Override
    public void add(Payment payment) {
        payments.add(payment);
    }

    @Override
    public List<Payment> getAll() {
        return payments;
    }
}
