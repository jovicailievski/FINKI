import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class CountThree {

    public static int NUM_RUNS = 100;
    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na elementot 3
     */
    int count = 0;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    Semaphore s;

    public void init() {
        s = new Semaphore(1);
    }

    class Counter extends Thread {
        int fragmet;
        public void count(int[] data) throws InterruptedException {
            // da se implementira
            int lCounter = 0;
            for(int a:data){
                if(a==3)
                    lCounter++;
            }
            s.acquire();
            count += lCounter;
            s.release();
        }
        private int[] data;

        public Counter(int[] data) {
            this.data = data;
            fragmet = NUM_RUNS/data.length;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountThree environment = new CountThree();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        Scanner s = new Scanner(System.in);
        int total=s.nextInt();

        Random r = new Random();

        for (int i = 0; i < NUM_RUNS; i++) {
            int[] data = new int[total];
            for (int j = 0; j < total; j++) {
                data[j] = r.nextInt(10);
            }
            Counter c = new Counter(data);
            threads.add(c);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(count);

    }
}
