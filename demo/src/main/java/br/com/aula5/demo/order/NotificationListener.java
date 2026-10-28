package br.com.aula5.demo.order;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener
    public void onStatusChanged(OrderStatusChangedEvent event) {
        System.out.printf("Notificação: pedido %s mudou de %s para %s%n",
                event.orderId(), event.previousStatus(), event.newStatus());
    }
}
