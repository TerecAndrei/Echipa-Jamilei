package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.IPaymentRepository;
import pizzashop.repository.MenuRepository;

import java.util.List;

public class PizzaService {

    private final MenuRepository menuRepo;
    private final IPaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, IPaymentRepository payRepo) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
    }

    public List<MenuDataModel> getMenuData() {
        return menuRepo.getMenu();
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) {
        if (table <= 0 || table > 1000) throw new RuntimeException("Table number out of range 1-1000");
        if (amount <= 0) throw new RuntimeException("Amount should be positive");
        Payment payment = new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> l = getPayments();
        if (l == null) {
            return total;
        }
        if (l.isEmpty()) {
            return total;
        }
        for (Payment p : l) {
            if (p.getType().equals(type)) {
                total += p.getAmount();
            }
        }
        return total;
    }

}
