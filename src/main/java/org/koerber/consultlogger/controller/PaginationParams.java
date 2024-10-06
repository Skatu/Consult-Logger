package org.koerber.consultlogger.controller;

public record PaginationParams(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String name,
                               Integer age) {
    public PaginationParams(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String name, Integer age) {
        if (name != null && age != null) {
            throw new IllegalArgumentException("Filter must be either by name or age, not both");
        }
        var sortByLowerCase = sortBy.toLowerCase();
        if (!"name".equals(sortByLowerCase) && !"age".equals(sortByLowerCase)) {
            throw new IllegalArgumentException("Sort must be either by name or age");
        }

        var sortOrderLowerCase = sortOrder.toLowerCase();
        if (!"asc".equals(sortOrderLowerCase) && !"desc".equals(sortOrderLowerCase)) {
            throw new IllegalArgumentException("SortOrder must be either asc or desc");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = sortByLowerCase;
        this.sortOrder = sortOrderLowerCase;
        this.name = name;
        this.age = age;
    }

    public boolean isSortOrderAsc() {
        return this.sortOrder.equals("asc");
    }
}
