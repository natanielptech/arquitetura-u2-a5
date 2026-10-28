package br.com.aula5.demo.order;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private final ConcurrentMap<String, Order> orders = new ConcurrentHashMap<>();

    public OrderRepository() {
        orders.put("order-1", new Order("order-1", OrderStatus.CREATED));
    }

    public Order findById(String id) {
        return orders.get(id);
    }

    public void save(Order order) {
        orders.put(order.id(), order);
    }
}
