import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
   //Exercise 2: Grouping and Summing Using Streams
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200, 2),
                new Product("Smartphone", "Electronics", 800, 5),
                new Product("TV", "Electronics", 1500, 1),
                new Product("Bread", "Groceries", 2.5, 10),
                new Product("Milk", "Groceries", 1.2, 20),
                new Product("Eggs", "Groceries", 3, 12),
                new Product("T-shirt", "Clothing", 25, 7),
                new Product("Jeans", "Clothing", 40, 3)
        );

        Map<String, Double> totalPrice=products.stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.summingDouble(Product::getTotalPrice)
                        )
                );
        totalPrice.forEach((category,price)-> System.out.println("Category: " + category + ", Total Price: " + price));

//__________________________________________________________________________________________________________________________________________//
    //Exercise 3: Stream Operations with Optional and FlatMap
        List<User> users = Arrays.asList(
                new User("HabibaAlaa", Optional.of(List.of(
                        new Order(1, List.of(
                                new Product("Ice Cream", "Desserts", 5, 2),
                                new Product("Chocolate Cake", "Desserts", 15, 1)
                        )),
                        new Order(2, List.of(
                                new Product("Apple Pie", "Desserts", 10, 1),
                                new Product("Cheesecake", "Desserts", 20, 1)
                        ))
                ))),
                new User("HabibaFathi", Optional.empty()),
                new User("MohamedSamir", Optional.empty()),
                new User("YoussefZaka", Optional.of(List.of(
                        new Order(3, List.of(
                                new Product("Bread", "Bakery", 2.5, 5),
                                new Product("Croissant", "Bakery", 3, 3)
                        ))
                )))
        );
        List<Integer> orderIds = users.stream()
                .map(User::getOrders)
                .flatMap(orders -> orders
                        .map(List::stream)
                        .orElseGet(Stream::empty))
                .map(Order::getId)
                .toList();

        System.out.println("Order IDs: " + orderIds);

    }
}