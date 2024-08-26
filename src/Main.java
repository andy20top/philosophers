
public class Main {
    public static void main(String[] args) {
        int maxPholosopher = 5;

        Philosopher[] philosophers = new Philosopher[maxPholosopher];
        Fork[] forks = new Fork[maxPholosopher];

        Table table = new Table(maxPholosopher, forks, philosophers);

        for (int i = 0; i < maxPholosopher; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < maxPholosopher; i++) {
            philosophers[i] = new Philosopher("Философ " + i, i, (i+1)%maxPholosopher, table.getCdl(), table);
        }

        table.start();



    }
}