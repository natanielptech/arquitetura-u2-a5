package br.com.aula5.demo.refactored;

import java.math.BigDecimal;

public record OrderRequest(
        String orderId,
        String customerName,
        String customerType,
        BigDecimal subtotal,
        String couponCode,
        boolean expedited,
        String paymentMethod) {
}
