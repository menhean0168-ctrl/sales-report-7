package test.java.edu.itc.salesreport.render;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportRendererTest {

    @ParameterizedTest
    @ValueSource(strings = { "txt", "html", "csv" })
    void containsAllBranchesAndChainTotal(String ext) {
        String out = new String(ReportRenderer.forExtension(ext)
                .render(TestReports.september()), StandardCharsets.UTF_8);
        for (String s : List.of("PNH", "REP", "BTB", "2300.50"))
            assertTrue(out.contains(s), ext + " missing " + s);
    }

    @Test
    void csvExactLine() {
        String out = new String(new CsvReportRenderer()
                .render(TestReports.september()), StandardCharsets.UTF_8);
        assertTrue(out.contains("2026-09,REP,800.50,0.00,50,16.01"));
    }

    @Test
    void unknownExtensionThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> ReportRenderer.forExtension("pdf"));
    }

    @Test
    void exhaustiveSwitchWithoutDefault() {
        ReportRenderer r = ReportRenderer.forExtension("csv");
        String name = switch (r) {
            case TextReportRenderer t -> "text";
            case HtmlReportRenderer h -> "html";
            case CsvReportRenderer c -> "csv";
        };
        assertEquals("csv", name);
    }
}