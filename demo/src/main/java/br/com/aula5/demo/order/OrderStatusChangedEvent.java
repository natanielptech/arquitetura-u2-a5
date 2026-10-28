package br.com.aula5.demo.order;

public record OrderStatusChangedEvent(String orderId, OrderStatus previousStatus, OrderStatus newStatus) {
}
