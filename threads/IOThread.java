package threads;

public class IOThread extends Thread {

    public IOThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(this.getName() + " finished");
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " interrupted");
        }
    }
}
