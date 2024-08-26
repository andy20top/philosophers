import java.util.Random;
import java.util.concurrent.CountDownLatch;

class Philosopher extends Thread {

    private int eatCounter;
    private final String name;
    private int leftFork;
    private int rightFork;
    private Random random;
    private CountDownLatch cdl;
    private Table table;


    public Philosopher(String name, int leftFork, int rightFork, CountDownLatch cdl, Table table) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cdl = cdl;
        this.table = table;
        eatCounter = 0;
        random = new Random();
    }

    @Override
    public void run() {

        while (eatCounter <= 2) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + "завершил прием пищи");
        cdl.countDown();
    }

    private void think() throws InterruptedException {
        sleep(random.nextLong(200, 1000));
    }


    private synchronized void eat() throws InterruptedException {
        if (table.getForks(leftFork, rightFork)) {
            eatCounter++;
            System.out.println(name + "покушал" + eatCounter + "раз");
            sleep(random.nextLong(2000, 4000));
            table.putForks(leftFork, rightFork);
            System.out.println(name + "положил вилки");
        }
    }
}