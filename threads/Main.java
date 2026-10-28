package threads;

public class Main {

    public static void main(String[] args) {
        Thread worker1 = new Thread(new IOTask(), "Worker-Runnable-1");
        Thread worker2 = new Thread(new IOTask(), "Worker-Runnable-2");
        IOThread worker3 = new IOThread("Worker-Thread-3");

        worker1.start();
        worker2.start();
        worker3.start();

        worker2.interrupt();

        try {
            worker1.join();
            worker2.join();
            worker3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads finished processing.");
    }
}
