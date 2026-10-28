package br.com.aula5.demo.refactored;

import java.math.BigDecimal;

public record PriceBreakdown(BigDecimal discount, BigDecimal shipping, BigDecimal tax, BigDecimal total) {
}
