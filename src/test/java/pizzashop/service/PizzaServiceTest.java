package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryMock;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    PaymentRepositoryMock paymentRepositoryMock;
    PizzaService service;
    @BeforeEach
    void setUp() {
        paymentRepositoryMock = new PaymentRepositoryMock();
        service = new PizzaService(new MenuRepository(), paymentRepositoryMock);
    }

    @Test
    void addPayment_valid1() {
        //Arrange test
        int table = 2;
        PaymentType type = PaymentType.Card;
        double amount = 200;

        //Act test
        service.addPayment(table, type, amount);

        //Assert test
        Assertions.assertEquals(1, paymentRepositoryMock.getAll().size());
    }

    @Test
    void addPayment_valid2() {
        //Arrange test
        int table = 2;
        PaymentType type = PaymentType.Card;
        double amount = 200;

        //Act test
        service.addPayment(table, type, amount);

        //Assert test
        Assertions.assertEquals(1, paymentRepositoryMock.getAll().size());
    }

    @Test
    void addPayment_invalid1() {
        //Arrange test
        int table = 2;
        PaymentType type = PaymentType.Card;
        double amount = 200;

        //Act test
        Executable test = () -> service.addPayment(table, type, amount);
        //Assert test
        Assertions.assertThrows(RuntimeException.class, test, "");
    }

    @Test
    void addPayment_invalid2() {
        //Arrange test
        int table = 2;
        PaymentType type = PaymentType.Card;
        double amount = 200;

        //Act test
        Executable test = () -> service.addPayment(table, type, amount);
        //Assert test
        Assertions.assertThrows(RuntimeException.class, test, "");
    }
}