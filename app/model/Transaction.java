package model;

public class Transaction {

    private String id;
    private String description;
    private String raw_description;
    private double amount;
    private String date;
    private String updated_at;
    private boolean is_deleted;
    private Category category;
    private Account account;
    private String ressource_uri;
    private String ressource_type;

    public Transaction() {}
    public Transaction(String id, String description, String raw_description, double amount, String date, String updated_at, boolean is_deleted, Category category, Account account, String ressource_uri, String ressource_type) {
        this.id = id;
        this.description = description;
        this.raw_description = raw_description;
        this.amount = amount;
        this.date = date;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
        this.category = category;
        this.account = account;
        this.ressource_uri = ressource_uri;
        this.ressource_type = ressource_type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRaw_description(String raw_description) {
        this.raw_description = raw_description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setRessource_uri(String ressource_uri) {
        this.ressource_uri = ressource_uri;
    }

    public void setRessource_type(String ressource_type) {
        this.ressource_type = ressource_type;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRaw_description() {
        return raw_description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public Category getCategory() {
        return category;
    }

    public Account getAccount() {
        return account;
    }

    public String getRessource_uri() {
        return ressource_uri;
    }

    public String getRessource_type() {
        return ressource_type;
    }
}