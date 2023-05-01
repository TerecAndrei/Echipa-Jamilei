package pizzashop.service;

import org.junit.After;
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
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PizzaServiceTestIntegrationMockE {

    private PaymentRepository repoPayments;

    private MenuRepository repoMenu;

    private PizzaService pizzaService;

    @Mock
    private Payment mockPayment;

    @Before
    public void setUp() {
        try {
            File file = new File("data/test.txt");
            file.createNewFile();
            System.out.println("File: " + file);
        } catch(Exception e) {
            e.printStackTrace();
        }
        repoMenu=new MenuRepository();
        repoPayments=new PaymentRepository("data/test.txt");
        pizzaService=new PizzaService(repoMenu,repoPayments);

    }

    @Test
    public void testAddPayment(){
        int tableNumber=1;
        PaymentType type=PaymentType.Cash;
        double amount=10.0;
        mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(tableNumber);
        when(mockPayment.getType()).thenReturn(type);
        when(mockPayment.getAmount()).thenReturn(amount);
        pizzaService.addPayment(tableNumber,type,amount);

        assertEquals(pizzaService.getPayments().size(),1);
        assertEquals(pizzaService.getPayments().get(0).getTableNumber(),tableNumber);

        // test cases for when the function throws exceptions
        Executable test = () -> pizzaService.addPayment(0, PaymentType.Card, 2.5);
        Assertions.assertThrows(RuntimeException.class,test,"Invalid table");

        test = () -> pizzaService.addPayment(1000, PaymentType.Cash, 0);
        Assertions.assertThrows(RuntimeException.class,test,"Invalid amount");

        test = () -> pizzaService.addPayment(1001, PaymentType.Cash, 0);
        Assertions.assertThrows(RuntimeException.class,test,"Invalid table");
    }

    @Test
    public void testGetAllPayments(){
        assertEquals(pizzaService.getPayments().size(),0);
        int tableNumber=1;
        PaymentType type=PaymentType.Cash;
        double amount=10.0;
        mockPayment = mock(Payment.class);
        when(mockPayment.getTableNumber()).thenReturn(tableNumber);
        when(mockPayment.getType()).thenReturn(type);
        when(mockPayment.getAmount()).thenReturn(amount);
        pizzaService.addPayment(tableNumber,type,amount);
        pizzaService.addPayment(tableNumber,type,amount);

        assertEquals(pizzaService.getPayments().size(),2);
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
