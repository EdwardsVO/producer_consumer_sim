package functions;

import administration.Almacen;
import administration.Boss;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Time extends Thread {

    private boolean start;
    public static volatile boolean passed = false;
    public Semaphore mutexAdmin;
    Boss boss;
    javax.swing.JTextPane hours;
    javax.swing.JTextPane days;
    javax.swing.JTextPane daysLeft;
    

    public Time(javax.swing.JTextPane hours, javax.swing.JTextPane days, javax.swing.JTextPane daysLeft, Semaphore mutexAdmin) {
        this.hours = hours;
        this.days = days;
        this.daysLeft = daysLeft;
        this.start = true;
        this.boss = boss;
        this.mutexAdmin = mutexAdmin;
    }

    public void run() {
        while (start) {
            while (Almacen.daysLeft > 0) {
                while (Almacen.hoursPassed < 24) {
                    try {
                        this.passed = false;
                        Thread.sleep(Almacen.dayEquiv / 24);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Almacen.hoursPassed++;

                    this.hours.setText(String.valueOf(Almacen.hoursPassed));
                }
                Almacen.daysPassed++;
                this.days.setText(String.valueOf(Almacen.daysPassed));
                Almacen.hoursPassed = 0;
                 this.passed = true;
            }
            
        }
    }

    public void showDays(javax.swing.JTextPane console1) { //AQUI
        this.days = console1;
    }

    public void showHours(javax.swing.JTextPane console1) { //AQUI
        this.hours = console1;
    }

    public void kill() {
        this.start = false;
    }

    public void init() {
        this.start = true;
    }
}
