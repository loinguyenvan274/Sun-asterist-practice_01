package threads;

public class IOTask implements Runnable {

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        try {
            Thread.sleep(2000);
            System.out.println(ct.getName() + " finished");
        } catch (InterruptedException e) {
            System.out.println(ct.getName() + " interrupted");
        }
    }
}
