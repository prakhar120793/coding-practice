package company.model;

public abstract class EmployeeBase {
    private final String id;
    private final int rating;

    public int getRating() {
        return rating;
    }

    protected EmployeeBase(String id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }
}
