import java.util.Arrays;
import java.util.List;

/**
 * The EventSchedulerApp class serves as the entry point for the Event Scheduling system.
 * It initializes advisors, submits customer queries, and manages the event scheduler lifecycle.
 */
public class EventSchedulerApp {
    public static void main(String[] args) {
        // Create a list of advisors who will handle customer queries
        List<Advisor> advisors = Arrays.asList(
                new Advisor(1, "Alice"),
                new Advisor(2, "Bob"),
                new Advisor(3, "Charlie")
        );

        // Initialize the event scheduler with the list of advisors
        EventScheduler scheduler = new EventScheduler(advisors);

        // Add customer queries to the scheduler for processing
        scheduler.addQuery(new CustomerQuery("Issue 1"));
        scheduler.addQuery(new CustomerQuery("Issue 2"));
        scheduler.addQuery(new CustomerQuery("Issue 3"));

        // Allow the scheduler to process tasks for 5 seconds before shutting down
        try {
            Thread.sleep(5000); // Simulate runtime duration
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print stack trace if interrupted
        }

        // Gracefully shut down the event scheduler
        scheduler.shutdown();
    }
}
