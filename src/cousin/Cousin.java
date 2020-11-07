package cousin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class Cousin implements Runnable {

    private final int moneyNeeded;
    private final CountDownLatch countDownLatch;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Cousin(int moneyNeeded) {
        this.moneyNeeded = moneyNeeded;
        this.countDownLatch = new CountDownLatch(moneyNeeded);
    }

    @Override
    public void run() {
        System.out.printf("%s -> Chavales, cuando hayáis recaudado %d €, voy a comprar el regalo\n",
                LocalTime.now().format(dateTimeFormatter),
                moneyNeeded);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.printf("%s -> Chavales, que me tengo que ir, no voy a poder comprar el regalo\n",
                    LocalTime.now().format(dateTimeFormatter));
        }

        System.out.printf("%s -> ¡Ya tengo el dinero, voy a comprar el regalo!\n",
                LocalTime.now().format(dateTimeFormatter));
    }

    public void accumulate(int money) {
        System.out.printf("%s -> %s, ha aportado %d\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                money);
        for (int i = 0; i < money; i++) {
            countDownLatch.countDown();
        }
    }
}
