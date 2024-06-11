package business.models;

public enum Status {
    SCHEDULED("scheduled"),
    CANCELED("canceled"),
    COMPLETED("completed"),
    RESCHEDULED("rescheduled"),
    CONFIRMED("confirmed"),
    UNCONFIRMED("unconfirmed");

    private final String statusString;

    Status(String statusString) {
        this.statusString = statusString;
    }

    // Metodă pentru a returna stringul asociat statusului
    public String getStatusString() {
        return statusString;
    }

    // Metodă pentru a returna stringul asociat statusului
    @Override
    public String toString() {
        return statusString;
    }
}
