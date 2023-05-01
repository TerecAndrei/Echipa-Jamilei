package pizzashop.repository;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @Before
    public void setUp() {
        try {
            File file = new File("data/test.txt");
            file.createNewFile();
            System.out.println("File: " + file);
        } catch(Exception e) {
            e.printStackTrace();
        }
        paymentRepository = new PaymentRepository("data/test.txt");
    }

    @Test
    public void testAdd() {
        //Payment payment = new Payment(1, PaymentType.Cash, 10.0);
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(1);
        when(mockPayment.getType()).thenReturn(PaymentType.Cash);
        when(mockPayment.getAmount()).thenReturn(10.0);
        paymentRepository.add(mockPayment);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(),1);

        assertEquals(mockPayment.getTableNumber(), paymentList.get(0).getTableNumber());
    }

    @Test
    public void testGetAllPayments()  {
        List<Payment> returnedPayments = paymentRepository.getAll();
        assertEquals(returnedPayments.size(), 0);
    }

    @After
    public void tearDown() {
        File myObj = new File("data/test.txt");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }


}
