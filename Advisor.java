import java.time.LocalDateTime;

/// Represents an Advisor who handles customer queries
class Advisor implements Comparable<Advisor> {
    private final int id;
    private final String name;
    private LocalDateTime nextAvailableTime; // The time when the advisor is free to take a new task

    public Advisor(int id, String name) {
        this.id = id;
        this.name = name;
        this.nextAvailableTime = null; // Initially, no tasks assigned
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getNextAvailableTime() {
        return nextAvailableTime;
    }

    // Checks if the advisor is free to take a new query
    public boolean isAvailable() {
        return nextAvailableTime == null || nextAvailableTime.isBefore(LocalDateTime.now());
    }

    // Assigns a task to the advisor and updates their availability
    public void assignTask(int processingTimeMinutes) {
        this.nextAvailableTime = LocalDateTime.now().plusMinutes(processingTimeMinutes);
    }

    // Compare advisors based on their availability time (used in PriorityQueue)
    @Override
    public int compareTo(Advisor other) {
        return this.nextAvailableTime == null ? -1 :
                other.nextAvailableTime == null ? 1 :
                        this.nextAvailableTime.compareTo(other.nextAvailableTime);
    }
}
