import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

// Manages the scheduling of customer queries to advisors
class EventScheduler {
    private final PriorityQueue<Advisor> advisorQueue = new PriorityQueue<>(); // Manages available advisors
    private final BlockingQueue<CustomerQuery> queryQueue = new LinkedBlockingQueue<>(); // Stores pending queries
    private final ExecutorService scheduler = Executors.newSingleThreadExecutor(); // Background processing

    public EventScheduler(List<Advisor> advisors) {
        advisorQueue.addAll(advisors); // Initialize the advisor queue
        scheduler.submit(this::processQueries); // Start processing queries in the background
    }

    // Adds a customer query to the queue
    public void addQuery(CustomerQuery query) {
        queryQueue.add(query);
    }

    // Continuously processes queries from the queue
    private void processQueries() {
        while (true) {
            try {
                CustomerQuery query = queryQueue.take(); // Fetch next query
                Advisor advisor = advisorQueue.poll(); // Get next available advisor

                if (advisor != null && advisor.isAvailable()) {
                    System.out.println("Assigning " + query + " to Advisor: " + advisor.getName());

                    // Assign the task with a variable processing time
                    advisor.assignTask(query.getProcessingTime());

                    // Reinsert the advisor back to the queue after updating availability
                    advisorQueue.add(advisor);
                } else {
                    // If no advisor is available, retry later
                    if (advisor != null) {
                        advisorQueue.add(advisor);
                    }
                    System.out.println("No available advisor. Retrying...");
                    Thread.sleep(1000);
                    queryQueue.add(query); // Requeue the query for processing later
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Shuts down the event scheduler gracefully
    public void shutdown() {
        scheduler.shutdown();
    }
}