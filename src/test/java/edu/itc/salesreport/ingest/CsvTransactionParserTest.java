package edu.itc.salesreport.ingest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CsvTransactionParserTest {
    private final TransactionParser parser = new CsvTransactionParser();

    @Test
    void parsesValidRowAndCalculatesRevenue() throws InvalidRowException {
        var t = parser.parse("PNH,2026-09-01,PNH-000123,SKU-1001,Jasmine Rice 5kg,Grocery,2,6.50,0.00,KHQR");
        assertEquals("PNH", t.branch());
        assertEquals(LocalDate.parse("2026-09-01"), t.date());
        assertEquals("Jasmine Rice 5kg", t.productName());
        assertEquals(2, t.quantity());
        assertEquals(new BigDecimal("6.50"), t.unitPrice());
        assertEquals("KHQR", t.paymentMethod().name());
        assertEquals(new BigDecimal("13.00"), t.revenue());
    }

    @Test
    void subtractsDiscount() throws InvalidRowException {
        var t = parser.parse("PNH,2026-09-01,PNH-000124,SKU-1001,Rice,Grocery,2,6.50,1.00,CASH");
        assertEquals(new BigDecimal("12.00"), t.revenue());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Grocery,2,6.50,0.00",
        "XYZ,2026-09-01,PNH-1,SKU-1,Rice,Grocery,2,6.50,0.00,CASH",
        "PNH,2026-09-31,PNH-1,SKU-1,Rice,Grocery,2,6.50,0.00,CASH",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Unknown,2,6.50,0.00,CASH",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Grocery,two,6.50,0.00,CASH",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Grocery,0,6.50,0.00,CASH",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Grocery,2,6.50,-1.00,CASH",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Grocery,2,6.50,14.00,CASH",
        "PNH,2026-09-01,PNH-1,SKU-1,Rice,Grocery,2,6.50,0.00,PAYPAL"
    })
    void rejectsInvalidRows(String row) {
        assertThrows(InvalidRowException.class, () -> parser.parse(row));
    }
}
