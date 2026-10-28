package br.com.aula5.demo.legacy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class LegacyOrderServiceTest {

    private final LegacyOrderService service = new LegacyOrderService();

    @Test
    void appliesVipDiscountAndFreeShippingAtThreshold() {
        LegacyOrderService.OrderSummary result = service.processOrder(
                "o-1", "Ana", "VIP", new BigDecimal("120.00"), "", false, "CARD");

        assertEquals(new BigDecimal("12.00"), result.discount());
        assertEquals(new BigDecimal("0.00"), result.shipping());
        assertEquals(new BigDecimal("8.64"), result.tax());
        assertEquals(new BigDecimal("116.64"), result.total());
        assertEquals("Pedido o-1 de Ana: Pagamento com cartão", result.summary());
    }

    @Test
    void combinesMembershipAndCouponDiscountsAndRecognizesPix() {
        LegacyOrderService.OrderSummary result = service.processOrder(
                "o-2", "Bia", "MEMBER", new BigDecimal("80.00"), "WELCOME10", true, "PIX");

        assertEquals(new BigDecimal("12.00"), result.discount());
        assertEquals(new BigDecimal("25.00"), result.shipping());
        assertEquals(new BigDecimal("5.44"), result.tax());
        assertEquals(new BigDecimal("98.44"), result.total());
        assertEquals("Pedido o-2 de Bia: Pagamento com Pix", result.summary());
    }

    @Test
    void calculatesDiscountAndPendingForUnrecognizedPaymentMethod() {
        LegacyOrderService.OrderSummary result = service.processOrder(
                "o-3", "Caio", "VIP", new BigDecimal("10.00"), "WELCOME10", false, "OTHER");

        assertEquals(new BigDecimal("2.00"), result.discount());
        assertEquals(new BigDecimal("0.64"), result.tax());
        assertEquals(new BigDecimal("18.64"), result.total());
        assertEquals("Pedido o-3 de Caio: Pagamento pendente", result.summary());
    }
}
