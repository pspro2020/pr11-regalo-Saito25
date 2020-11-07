package main;

import alumn.Alumn;
import cousin.Cousin;

public class Main {

    private final Cousin cousin = new Cousin(10);

    public Main() {
        new Thread(cousin, "Cousin").start();

        for(int i = 0; i < 5; i++) {
            new Thread(new Alumn(cousin), "Alumno " + (i + 1)).start();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
