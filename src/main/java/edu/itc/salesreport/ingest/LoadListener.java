package edu.itc.salesreport.ingest;

// LoadListener.java
@FunctionalInterface
public interface LoadListener {
    void onEvent(LoadEvent event);
}
