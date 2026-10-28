package br.com.aula5.demo.order;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String orderId) {
        super("Pedido não encontrado: " + orderId);
    }
}
