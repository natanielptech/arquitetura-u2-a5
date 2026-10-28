package br.com.aula5.demo.refactored;

import org.springframework.stereotype.Service;

@Service
public class RefactoredOrderService {

    private final OrderPricingService pricingService;
    private final OrderSummaryFormatter summaryFormatter;

    public RefactoredOrderService(
            OrderPricingService pricingService,
            OrderSummaryFormatter summaryFormatter) {
        this.pricingService = pricingService;
        this.summaryFormatter = summaryFormatter;
    }

    public LegacyResult process(OrderRequest request) {
        PriceBreakdown price = pricingService.calculate(request);
        return new LegacyResult(
                request.orderId(),
                price.discount(),
                price.shipping(),
                price.tax(),
                price.total(),
                summaryFormatter.format(request));
    }

    public record LegacyResult(
            String orderId,
            java.math.BigDecimal discount,
            java.math.BigDecimal shipping,
            java.math.BigDecimal tax,
            java.math.BigDecimal total,
            String summary) {
    }
}
