import java.util.Arrays;
import java.util.List;

// Main application to test the event scheduler
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
        scheduler.addQuery(new CustomerQuery("Issue 4"));
        scheduler.addQuery(new CustomerQuery("Issue 5"));

        // Allow the scheduler to process tasks for 10 seconds before shutting down
        try {
            Thread.sleep(10000); // Simulate runtime duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Gracefully shut down the event scheduler
        scheduler.shutdown();
    }
}
