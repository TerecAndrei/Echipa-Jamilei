package pizzashop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.IPaymentRepository;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class PizzaServiceTestMockito {

    @Mock
    private PaymentRepository mockedPaymentRepo;

    @Mock
    private MenuRepository mockedMenuRepo;

    @InjectMocks
    private PizzaService pizzaService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPayments(){
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, PaymentType.Cash, 10.0));
        paymentList.add(new Payment(2, PaymentType.Card, 20.0));
        Mockito.when(mockedPaymentRepo.getAll()).thenReturn(paymentList);

        List<Payment> allPayments=pizzaService.getPayments();
        assertEquals(allPayments.size(),paymentList.size());
        assertEquals(allPayments.get(0).getTableNumber(),1);

        Mockito.verify(mockedPaymentRepo, times(1)).getAll();
    }

    @Test
    public void testAddPayment(){
        //Payment payment=new Payment(1, PaymentType.Cash, 10.0);
        int tableNumber=1;
        PaymentType type=PaymentType.Cash;
        double amount=10.0;
        Payment mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(tableNumber);
        when(mockPayment.getType()).thenReturn(type);
        when(mockPayment.getAmount()).thenReturn(amount);

        Mockito.doNothing().when(mockedPaymentRepo).add(mockPayment);
        Mockito.when(mockedPaymentRepo.getAll()).thenReturn(Arrays.asList(mockPayment));

        pizzaService.addPayment(tableNumber,type,amount);

        assertEquals(pizzaService.getPayments().size(),1);
        assertEquals(pizzaService.getPayments().get(0).getTableNumber(),tableNumber);

        Mockito.verify(mockedPaymentRepo, times(2)).getAll();

        // test cases for when the function throws exceptions
        Executable test = () -> pizzaService.addPayment(0, PaymentType.Card, 2.5);
        Assertions.assertThrows(RuntimeException.class,test,"Invalid table");

        test = () -> pizzaService.addPayment(1000, PaymentType.Cash, 0);
        Assertions.assertThrows(RuntimeException.class,test,"Invalid amount");

        test = () -> pizzaService.addPayment(1001, PaymentType.Cash, 0);
        Assertions.assertThrows(RuntimeException.class,test,"Invalid table");
    }

}
