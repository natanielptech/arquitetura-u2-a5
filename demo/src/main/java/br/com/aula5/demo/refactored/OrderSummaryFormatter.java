package br.com.aula5.demo.refactored;

import org.springframework.stereotype.Component;

@Component
public class OrderSummaryFormatter {

    public String format(OrderRequest request) {
        String paymentDescription;
        if ("CARD".equalsIgnoreCase(request.paymentMethod())) {
            paymentDescription = "Pagamento com cartão";
        } else if ("PIX".equalsIgnoreCase(request.paymentMethod())) {
            paymentDescription = "Pagamento com Pix";
        } else {
            paymentDescription = "Pagamento pendente";
        }
        return "Pedido " + request.orderId() + " de " + request.customerName() + ": " + paymentDescription;
    }
}
