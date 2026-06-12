package com.example.javaspringboot3handonsdigihaul.dto.shipment;

import java.util.List;

public class PageResponse<T> {

    private final List<T> results;
    private final long totalCount;
    private final long filteredCount;

    public PageResponse(List<T> results, long totalCount, long filteredCount) {
        this.results = results;
        this.totalCount = totalCount;
        this.filteredCount = filteredCount;
    }

    public List<T> getResults() {
        return results;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getFilteredCount() {
        return filteredCount;
    }
}


