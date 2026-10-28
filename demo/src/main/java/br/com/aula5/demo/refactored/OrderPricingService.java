package br.com.aula5.demo.refactored;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class OrderPricingService {

    public PriceBreakdown calculate(OrderRequest request) {
        BigDecimal discount = calculateDiscount(request.customerType(), request.subtotal(), request.couponCode());
        BigDecimal discountedSubtotal = request.subtotal().subtract(discount);
        BigDecimal shipping = calculateShipping(discountedSubtotal, request.expedited());
        BigDecimal tax = discountedSubtotal.multiply(new BigDecimal("0.08"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = discountedSubtotal.add(shipping).add(tax).setScale(2, RoundingMode.HALF_UP);
        return new PriceBreakdown(discount.setScale(2, RoundingMode.HALF_UP), shipping, tax, total);
    }

    private BigDecimal calculateDiscount(String customerType, BigDecimal subtotal, String couponCode) {
        BigDecimal discount = BigDecimal.ZERO;
        if ("VIP".equalsIgnoreCase(customerType)) {
            discount = subtotal.multiply(new BigDecimal("0.10"));
        } else if ("MEMBER".equalsIgnoreCase(customerType)) {
            discount = subtotal.multiply(new BigDecimal("0.05"));
        }
        if ("WELCOME10".equalsIgnoreCase(couponCode)) {
            discount = discount.add(subtotal.multiply(new BigDecimal("0.10")));
        }
        return discount.compareTo(subtotal) > 0 ? subtotal : discount;
    }

    private BigDecimal calculateShipping(BigDecimal discountedSubtotal, boolean expedited) {
        if (discountedSubtotal.compareTo(new BigDecimal("100.00")) >= 0) {
            return new BigDecimal("0.00");
        }
        return expedited ? new BigDecimal("25.00") : new BigDecimal("10.00");
    }
}
