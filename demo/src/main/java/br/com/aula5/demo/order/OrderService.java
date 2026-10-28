package br.com.aula5.demo.order;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(OrderRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Order findById(String id) {
        Order order = repository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    /**
     * Atualiza o status do pedido e publica um evento quando o valor muda.
     * A publicação usa o mecanismo de eventos da aplicação e não garante persistência durável.
     *
     * @param id identificador do pedido existente
     * @param newStatus novo status desejado
     * @return pedido após a atualização
     */
    public Order changeStatus(String id, OrderStatus newStatus) {
        Order current = findById(id);
        if (current.status() == newStatus) {
            return current;
        }

        Order updated = new Order(id, newStatus);
        repository.save(updated);
        eventPublisher.publishEvent(new OrderStatusChangedEvent(id, current.status(), newStatus));
        return updated;
    }
}
