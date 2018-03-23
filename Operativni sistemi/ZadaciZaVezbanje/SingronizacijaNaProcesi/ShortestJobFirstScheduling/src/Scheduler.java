import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Scheduler extends Thread {
    public static Random random = new Random();
    static List<Process> scheduled = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // TODO: kreirajte 100 Process thread-ovi i registrirajte gi
        for(int i=0;i<100;i++){
            register(new Process());
        }

        // TODO: kreirajte Scheduler i startuvajte go negovoto pozadinsko izvrsuvanje
        Scheduler sh = new Scheduler();
        sh.start();

        // TODO: Cekajte 20000ms za Scheduler-ot da zavrsi
        Thread.sleep(20000);
        boolean test = sh.isAlive();
            sh.interrupt();

        // TODO: ispisete go statusot od izvrsuvanjeto
        System.out.println(test? "Terminated scheduling":"Finished scheduling");
    }

    public static void register(Process process) {
        scheduled.add(process);
    }

    public Process next() {
        if (!scheduled.isEmpty()) {
            return scheduled.remove(0);
        }
        return null;
    }

    public void run() {
        try {
            while (!scheduled.isEmpty()) {
                Thread.sleep(100);
                System.out.print(".");

                // TODO: zemete go naredniot proces

                Process p = next();

                // TODO: povikajte go negoviot execute() metod

                p.start();

                // TODO: cekajte dodeka ne zavrsi negovoto pozadinsko izvrsuvanje

                p.join();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done scheduling!");
    }
}


class Process extends Thread{

    public Integer duration;

    public Process() throws InterruptedException {
        this.duration = Scheduler.random.nextInt(1000);
    }


    @Override
    public void run() {
        try {
            execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void execute() throws InterruptedException {
        System.out.println("Executing[" + this + "]: " + duration);
        // TODO: startuvajte go pozadinskoto izvrsuvanje
        Thread.sleep(this.duration);
    }
}