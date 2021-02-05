import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int POOL_SIZE = 5;
        ExecutorService executor =Executors.newFixedThreadPool(POOL_SIZE); ; // assign an object to it

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            // create and submit tasks
            executor.submit(() -> {
               PrintIfPrimeTask t = new PrintIfPrimeTask(number);
               t.run();

            });


        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        // write code of task here
        boolean isPrime=true;
        int temp;
        for(int i=2;i<=number/2;i++)
        {
            temp=number%i;
            if(temp==0)
            {
                isPrime=false;
                break;
            }
        }
        if(isPrime && number>1) System.out.println(number);


    }
}