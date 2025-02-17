import java.time.LocalDateTime;
import java.util.Random;

// Represents a customer query
class CustomerQuery {
    private static int counter = 0; // Unique ID generator
    private final int id;
    private final String issue;
    private final LocalDateTime createdAt;
    private final int processingTime; // Time required to process this query

    public CustomerQuery(String issue) {
        this.id = ++counter;
        this.issue = issue;
        this.createdAt = LocalDateTime.now();
        this.processingTime = new Random().nextInt(20) + 10; // Random processing time (10-30 min)
    }

    public int getId() {
        return id;
    }

    public String getIssue() {
        return issue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public String toString() {
        return "Query{" + "id=" + id + ", issue='" + issue + '\'' +
                ", createdAt=" + createdAt + ", processingTime=" + processingTime + " mins}";
    }
}
