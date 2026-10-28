package br.com.aula5.demo.order;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditListener {

    @EventListener
    public void onStatusChanged(OrderStatusChangedEvent event) {
        System.out.printf("Auditoria: pedido %s, %s -> %s%n",
                event.orderId(), event.previousStatus(), event.newStatus());
    }
}
