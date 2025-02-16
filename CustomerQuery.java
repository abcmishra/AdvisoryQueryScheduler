import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * Represents a customer query posted on the portal.
 * Each query has a unique ID, issue description, and timestamp.
 */
class CustomerQuery {
    private static int counter = 0; // Static counter to generate unique query IDs
    private final int id; // Unique ID for the query
    private final String issue; // Description of the customer's issue
    private final LocalDateTime createdAt; // Timestamp when the query was created

    /**
     * Constructor to initialize a new customer query with an issue description.
     * The query is assigned a unique ID and timestamp at creation.
     *
     * @param issue The description of the issue submitted by the customer
     */
    public CustomerQuery(String issue) {
        this.id = ++counter; // Assigns a unique incremental ID to each query
        this.issue = issue;
        this.createdAt = LocalDateTime.now(); // Sets the current timestamp when the query is created
    }

    /**
     * Gets the unique ID of the query.
     *
     * @return Query ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the issue description of the query.
     *
     * @return The issue as a string
     */
    public String getIssue() {
        return issue;
    }

    /**
     * Gets the timestamp when the query was created.
     *
     * @return Created time as LocalDateTime
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Converts the query details to a string representation.
     *
     * @return String format of the query object
     */
    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", issue='" + issue + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
