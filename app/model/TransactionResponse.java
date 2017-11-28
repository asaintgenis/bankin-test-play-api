package model;

import java.util.List;

public class TransactionResponse {
    private List<Transaction> resources;
    private Pagination pagination;

    public TransactionResponse() {
    }

    public TransactionResponse(List<Transaction> resources, Pagination pagination) {
        this.resources = resources;
        this.pagination = pagination;
    }

    public void setResources(List<Transaction> resources) {
        this.resources = resources;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Transaction> getResources() {
        return resources;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
