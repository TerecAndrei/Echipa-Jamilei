package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepositoryMock;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class PizzaServiceTest {

    PaymentRepositoryMock paymentRepositoryMock;
    PizzaService service;

    @BeforeEach
    void setUp() {
        paymentRepositoryMock = new PaymentRepositoryMock();
        service = new PizzaService(new MenuRepository(), paymentRepositoryMock);
    }

    @ParameterizedTest
    @MethodSource("validPayments")
    @DisplayName("whenPaymentValidThenAddPayment")
    @Tag("PizzaService")
    @Order(1)
    void addPayment_valid(int table, PaymentType type, double amount) {
        //Arrange test
        //This is done in the constructor
        //Act test
        service.addPayment(table, type, amount);

        //Assert test
        Assertions.assertEquals(1, paymentRepositoryMock.getAll().size());
    }

    @ParameterizedTest
    @MethodSource("invalidPayments")
    @Tag("PizzaService")
    @Order(2)
    void addPayment_invalid(int table, PaymentType type, double amount, String expectedErrorMessage) {
        //Arrange test
        //This is done in the constructor

        //Act test
        Executable test = () -> service.addPayment(table, type, amount);
        //Assert test
        Assertions.assertThrows(RuntimeException.class, test, expectedErrorMessage);
    }

    private static Stream<Arguments> validPayments() {
        //int table, PaymentType type, double amount
        return Stream.of(arguments(1, PaymentType.Cash, 1), //ECP
                arguments(2, PaymentType.Cash, 10), // BVA la table=2
                arguments(1000, PaymentType.Cash, 10) // BVA la table=1000
        );
    }

    private static Stream<Arguments> invalidPayments() {
        //int table, PaymentType type, double amount, String expectedErrorMessage
        return Stream.of(arguments(0, PaymentType.Card, 2.5, "Invalid table"), //ECP
                arguments(1000, PaymentType.Cash, 0, "Invalid amount"), //ECP si BVA la table=999
                arguments(1001, PaymentType.Card, 10.35, "Invalid table") //ECP si BVA la table=1001
        );
    }
}