import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private int maxFilosopher;
    private Fork[] forks;
    private Philosopher[] philosophers;
    private CountDownLatch cdl;

    public Table(int maxFilosopher, Fork[] forks, Philosopher[] philosophers) {
        this.maxFilosopher = maxFilosopher;
        this.forks = forks;
        this.philosophers = philosophers;
        cdl = new CountDownLatch(maxFilosopher);
    }

    @Override
    public void run() {
        System.out.println("Start eating: ");
        try {
            thinking();
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The end");
    }


    public synchronized boolean getForks(int leftFork, int rightFork) {
        if (!forks[leftFork].isUsing() && !forks[rightFork].isUsing()) {
            forks[leftFork].setUsing(true);
            forks[rightFork].setUsing(true);
            return true;
        }
        return false;
    }

    public void putForks(int leftFork, int rightFork) {
        forks[leftFork].setUsing(false);
        forks[rightFork].setUsing(false);
    }

    private void thinking() {
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }

    public CountDownLatch getCdl() {
        return cdl;
    }
}
