package edu.itc.salesreport.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SaleTransactionTest {
    @Test
    void roundsRevenueHalfUp() {
        var t = new SaleTransaction("PNH", LocalDate.parse("2026-09-01"), "R1",
                "SKU-1", "Item", "Grocery", 3, new BigDecimal("0.335"),
                new BigDecimal("0.00"), PaymentMethod.CASH);
        assertEquals(new BigDecimal("1.01"), t.revenue());
    }

    @Test
    void branchSummaryProductsAreUnmodifiable() {
        var summary = new BranchSummary("PNH", new BigDecimal("10.00"),
                new BigDecimal("0.00"), 1, Map.of(),
                List.of(new ProductTotal("SKU-1", "Item", new BigDecimal("10.00"), 1)),
                Map.of());
        assertThrows(UnsupportedOperationException.class,
                () -> summary.topProducts().add(new ProductTotal("SKU-2", "Other", BigDecimal.ONE, 1)));
    }

    @Test
    void averageBasketIsZeroWhenThereAreNoReceipts() {
        var summary = new BranchSummary("PNH", new BigDecimal("0.00"),
                new BigDecimal("0.00"), 0, Map.of(), List.of(), Map.of());
        assertEquals(new BigDecimal("0.00"), summary.averageBasket());
    }
}
