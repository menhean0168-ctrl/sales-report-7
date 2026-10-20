package edu.itc.salesreport.ingest;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import edu.itc.salesreport.model.SaleTransaction;

// MonthLoader.java: the subject
public final class MonthLoader {

    private final TransactionParser parser;
    private final List<LoadListener> listeners =
            new CopyOnWriteArrayList<>();
    private final List<String> rejectedRows = new ArrayList<>();

    public MonthLoader(TransactionParser parser) {
        this.parser = parser;
    }

    /** Registers a listener; the returned handle unsubscribes it. */
    public Runnable subscribe(LoadListener listener) {
        // TODO: add the listener, return () -> listeners.remove(listener)
        throw new UnsupportedOperationException("TODO");
    }

    public List<String> rejectedRows() {
        return List.copyOf(rejectedRows);
    }

    public List<SaleTransaction> load(YearMonth month, Path dataDir) {
        // TODO: list dataDir/month/*.csv sorted by name (Files.list)
        // TODO: for each file: read lines (UTF-8), skip index 0,
        //       parse, count rejected, record "FILE:LINE message",
        //       publish(new LoadEvent.FileLoaded(...))
        // TODO: publish(new LoadEvent.LoadFinished(...)) with the time taken
        throw new UnsupportedOperationException("TODO");
    }

    private void publish(LoadEvent event) {
        listeners.forEach(l -> l.onEvent(event));
    }
}
