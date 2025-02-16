import java.time.LocalDateTime;

/**
 * Represents an Advisor who can be assigned tasks based on availability.
 * Implements Comparable to allow sorting based on the next available time.
 */
class Advisor implements Comparable<Advisor> {
    private final int id; // Unique identifier for the advisor
    private String name; // Advisor's name
    private LocalDateTime nextAvailableTime; // The next time the advisor is available for a task

    /**
     * Constructor to initialize an advisor with an ID and name.
     * The advisor is initially available immediately.
     *
     * @param id   Unique identifier for the advisor
     * @param name Name of the advisor
     */
    public Advisor(int id, String name) {
        this.id = id;
        this.name = name;
        this.nextAvailableTime = LocalDateTime.now(); // Sets the current time as the initial availability
    }

    /**
     * Gets the unique ID of the advisor.
     *
     * @return Advisor's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the advisor.
     *
     * @return Advisor's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the next available time of the advisor.
     *
     * @return Next available time as LocalDateTime
     */
    public LocalDateTime getNextAvailableTime() {
        return nextAvailableTime;
    }

    /**
     * Updates the next available time for the advisor.
     *
     * @param nextAvailableTime The new availability time to be set
     */
    public void setNextAvailableTime(LocalDateTime nextAvailableTime) {
        this.nextAvailableTime = nextAvailableTime;
    }

    /**
     * Compares advisors based on their next available time.
     * This is used in priority queues to get the earliest available advisor.
     *
     * @param other Another advisor to compare with
     * @return Negative if this advisor is available earlier, positive if later, zero if equal
     */
    @Override
    public int compareTo(Advisor other) {
        return this.nextAvailableTime.compareTo(other.nextAvailableTime);
    }
}
