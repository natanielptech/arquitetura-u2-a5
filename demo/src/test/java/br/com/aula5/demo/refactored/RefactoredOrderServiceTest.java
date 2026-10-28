package br.com.aula5.demo.refactored;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.aula5.demo.legacy.LegacyOrderService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class RefactoredOrderServiceTest {

    private final LegacyOrderService legacy = new LegacyOrderService();
    private final RefactoredOrderService refactored =
            new RefactoredOrderService(new OrderPricingService(), new OrderSummaryFormatter());

    @Test
    void preservesVipCardOrderBehavior() {
        OrderRequest request = new OrderRequest(
                "o-1", "Ana", "VIP", new BigDecimal("120.00"), "", false, "CARD");

        assertSameOutcome(
                legacy.processOrder("o-1", "Ana", "VIP", new BigDecimal("120.00"), "", false, "CARD"),
                refactored.process(request));
    }

    @Test
    void preservesMemberCouponAndPixBehavior() {
        OrderRequest request = new OrderRequest(
                "o-2", "Bia", "MEMBER", new BigDecimal("80.00"), "WELCOME10", true, "PIX");

        assertSameOutcome(
                legacy.processOrder("o-2", "Bia", "MEMBER", new BigDecimal("80.00"), "WELCOME10", true, "PIX"),
                refactored.process(request));
    }

    @Test
    void preservesFallbackPaymentBehavior() {
        OrderRequest request = new OrderRequest(
                "o-3", "Caio", "VIP", new BigDecimal("10.00"), "WELCOME10", false, "OTHER");

        assertSameOutcome(
                legacy.processOrder("o-3", "Caio", "VIP", new BigDecimal("10.00"), "WELCOME10", false, "OTHER"),
                refactored.process(request));
    }

    private void assertSameOutcome(
            LegacyOrderService.OrderSummary before,
            RefactoredOrderService.LegacyResult after) {
        assertEquals(before.orderId(), after.orderId());
        assertEquals(before.discount(), after.discount());
        assertEquals(before.shipping(), after.shipping());
        assertEquals(before.tax(), after.tax());
        assertEquals(before.total(), after.total());
        assertEquals(before.summary(), after.summary());
    }
}
