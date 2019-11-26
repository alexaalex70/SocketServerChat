import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class TopicMessage<Message> {
 
    private ArrayList<Message> topicQueue = new ArrayList<Message>();
 
    public synchronized void enqueue(Message msg) {
        System.out.println("Enqueued the message in topicQueue");
        this.topicQueue.add(msg);
        notify();
    }
    
    public int getSize() {
    	return topicQueue.size();
    }
    
 
    public synchronized Message dequeue() {
        while (this.topicQueue.isEmpty()) {
            try {
                System.out.println("Inside Dequeue -- Waiting");
                wait();
            } catch (Exception ex) {
                System.out.println("Exception occured in Dequeue");
            }
        }
        System.out.println("Dequeue -- Completed");
        return this.topicQueue.remove(0);
    }
}