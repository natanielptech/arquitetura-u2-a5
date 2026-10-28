package br.com.aula5.demo.legacy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class LegacyOrderService {

    public OrderSummary processOrder(
            String orderId,
            String customerName,
            String customerType,
            BigDecimal subtotal,
            String couponCode,
            boolean expedited,
            String paymentMethod) {
        BigDecimal discount = BigDecimal.ZERO;
        if ("VIP".equalsIgnoreCase(customerType)) {
            discount = subtotal.multiply(new BigDecimal("0.10"));
        } else if ("MEMBER".equalsIgnoreCase(customerType)) {
            discount = subtotal.multiply(new BigDecimal("0.05"));
        }
        if ("WELCOME10".equalsIgnoreCase(couponCode)) {
            discount = discount.add(subtotal.multiply(new BigDecimal("0.10")));
        }
        if (discount.compareTo(subtotal) > 0) {
            discount = subtotal;
        }

        BigDecimal discountedSubtotal = subtotal.subtract(discount);
        BigDecimal shipping = expedited ? new BigDecimal("25.00") : new BigDecimal("10.00");
        if (discountedSubtotal.compareTo(new BigDecimal("100.00")) >= 0) {
            shipping = new BigDecimal("0.00");
        }
        BigDecimal tax = discountedSubtotal.multiply(new BigDecimal("0.08"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = discountedSubtotal.add(shipping).add(tax).setScale(2, RoundingMode.HALF_UP);

        String paymentDescription;
        if ("CARD".equalsIgnoreCase(paymentMethod)) {
            paymentDescription = "Pagamento com cartão";
        } else if ("PIX".equalsIgnoreCase(paymentMethod)) {
            paymentDescription = "Pagamento com Pix";
        } else {
            paymentDescription = "Pagamento pendente";
        }
        String summary = "Pedido " + orderId + " de " + customerName + ": " + paymentDescription;
        return new OrderSummary(orderId, discount.setScale(2, RoundingMode.HALF_UP), shipping, tax, total, summary);
    }

    public record OrderSummary(
            String orderId,
            BigDecimal discount,
            BigDecimal shipping,
            BigDecimal tax,
            BigDecimal total,
            String summary) {
    }
}
