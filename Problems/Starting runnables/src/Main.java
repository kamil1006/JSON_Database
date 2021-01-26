import java.util.TreeMap;

class Starter {

    public static void startRunnables(Runnable[] runnables) {
        // implement the method

    for(Runnable r:runnables){
        Thread t= new Thread(r);
        t.start();
    }




    }
}