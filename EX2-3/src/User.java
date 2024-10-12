import java.util.List;
import java.util.Optional;

public class User {
    private String name;
    private Optional<List<Order>> orders;

    public User(String name, Optional<List<Order>> orders) {
        this.name = name;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(Optional<List<Order>> orders) {
        this.orders = orders;
    }
}
