import java.util.*;
import java.util.concurrent.*;

/**
 * The EventScheduler class is responsible for scheduling customer queries
 * to advisors based on their availability.
 */
class EventScheduler {
    // PriorityQueue to store advisors, sorted by their next available time
    private final PriorityQueue<Advisor> advisorQueue = new PriorityQueue<>();

    // BlockingQueue to store customer queries in FIFO order
    private final BlockingQueue<CustomerQuery> queryQueue = new LinkedBlockingQueue<>();

    // ExecutorService to manage query processing in a separate thread
    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();

    /**
     * Constructor to initialize the EventScheduler with a list of advisors.
     * It starts a background process to continuously assign queries to advisors.
     *
     * @param advisors List of available advisors to handle queries.
     */
    public EventScheduler(List<Advisor> advisors) {
        advisorQueue.addAll(advisors); // Add advisors to the priority queue
        scheduler.submit(this::processQueries); // Start processing queries in a separate thread
    }

    /**
     * Adds a new customer query to the queue for scheduling.
     *
     * @param query The customer query to be scheduled.
     */
    public void addQuery(CustomerQuery query) {
        queryQueue.add(query); // Add the query to the blocking queue
    }

    /**
     * Continuously processes customer queries by assigning them to the next available advisor.
     * If no advisor is available, the query is retried after a short delay.
     */
    private void processQueries() {
        while (true) {
            try {
                // Take the next query from the queue (blocks if empty)
                CustomerQuery query = queryQueue.take();

                // Get the next available advisor
                Advisor advisor = advisorQueue.poll();

                if (advisor != null) {
                    System.out.println("Assigning " + query + " to Advisor: " + advisor.getName());

                    // Simulate processing time by updating the advisor's availability
                    LocalDateTime newAvailableTime = advisor.getNextAvailableTime().plusMinutes(30);
                    advisor.setNextAvailableTime(newAvailableTime);

                    // Reinsert advisor back to queue with updated availability
                    advisorQueue.add(advisor);
                } else {
                    // If no advisor is available, wait and retry the query
                    System.out.println("No available advisor. Retrying...");
                    Thread.sleep(1000);
                    queryQueue.add(query); // Re-add query for future processing
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted state
                break; // Exit loop if the thread is interrupted
            }
        }
    }

    /**
     * Shuts down the scheduler and stops processing queries.
     */
    public void shutdown() {
        scheduler.shutdown(); // Gracefully shutdown the executor
    }
}
