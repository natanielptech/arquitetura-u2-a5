package br.com.aula5.demo.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

class OrderServiceTest {

    private OrderRepository repository;
    private ApplicationEventPublisher eventPublisher;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = new OrderRepository();
        eventPublisher = org.mockito.Mockito.mock(ApplicationEventPublisher.class);
        service = new OrderService(repository, eventPublisher);
    }

    @Test
    void changesStatusAndPublishesEventForConsumers() {
        Order updated = service.changeStatus("order-1", OrderStatus.PAID);

        assertEquals(OrderStatus.PAID, updated.status());
        assertEquals(updated, repository.findById("order-1"));
        verify(eventPublisher).publishEvent(
                new OrderStatusChangedEvent("order-1", OrderStatus.CREATED, OrderStatus.PAID));
    }

    @Test
    void doesNotPublishEventWhenStatusIsUnchanged() {
        Order unchanged = service.changeStatus("order-1", OrderStatus.CREATED);

        assertEquals(OrderStatus.CREATED, unchanged.status());
        verify(eventPublisher, never()).publishEvent(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void rejectsUnknownOrder() {
        assertThrows(OrderNotFoundException.class,
                () -> service.changeStatus("missing", OrderStatus.PAID));
        verify(eventPublisher, never()).publishEvent(org.mockito.ArgumentMatchers.any());
    }
}
