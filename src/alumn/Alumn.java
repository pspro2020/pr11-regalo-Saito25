package alumn;

import cousin.Cousin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Alumn implements Runnable {

    private final Cousin cousin;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Alumn(Cousin cousin) {
        this.cousin = cousin;
    }

    @Override
    public void run() {
        try {
            gettingMoney();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: chavales, no he conseguido el dinero ha tiempo, ¡Lo siento!\n",
                    LocalTime.now().format(dateTimeFormatter),
                    Thread.currentThread().getName());
            return;
        }
        cousin.accumulate(new Random().nextInt(4) + 2);
    }

    private void gettingMoney() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(8) + 3);
    }
}
