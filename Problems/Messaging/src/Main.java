import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/* Do not change this class */
class Message {
    final String text;
    final String from;
    final String to;

    Message(String from, String to, String text) {
        this.text = text;
        this.from = from;
        this.to = to;
    }
}

/* Do not change this interface */
interface AsyncMessageSender {
    void sendMessages(Message[] messages);
    void stop();
}

class AsyncMessageSenderImpl implements AsyncMessageSender {

    private static final int POOL_SIZE = 5;
    private ExecutorService executor =   Executors.newFixedThreadPool(POOL_SIZE); // TODO initialize the executor


    private final int repeatFactor;

    public AsyncMessageSenderImpl(int repeatFactor) {
        this.repeatFactor = repeatFactor;
    }

    @Override
    public void sendMessages(Message[] messages) {
        for (Message msg : messages) {
            // TODO repeat messages
        for (int i=0;i<repeatFactor;i++) {

            executor.submit(() -> {
                System.out.printf("(%s>%s): %s\n", msg.from, msg.to, msg.text); // do not change it
            });
        }
        }
    }

    @Override
    public void stop() {
        // TODO stop the executor and wait for it
        executor.shutdown();

    }
}