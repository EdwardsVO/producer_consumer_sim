/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import administration.Almacen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edward Vergel
 */
public class Time extends Thread {

    private boolean start;
    public static volatile boolean passed = false;
    javax.swing.JTextPane hours;
    javax.swing.JTextPane days;
    javax.swing.JTextPane daysLeft;

    public Time(javax.swing.JTextPane hours, javax.swing.JTextPane days, javax.swing.JTextPane daysLeft) {
        this.hours = hours;
        this.days = days;
        this.daysLeft = daysLeft;
        this.start = true;
    }

    public void run() {
        while (start) {
            while (Almacen.daysLeft > 0) {
                while (Almacen.hoursPassed != 24) {
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
                Almacen.daysLeft--;
                this.days.setText(String.valueOf(Almacen.daysPassed));
                this.daysLeft.setText(String.valueOf(Almacen.daysLeft));
                Almacen.hoursPassed = 0;
                System.out.println("Han transcurrido " + Almacen.daysPassed + " dias");
                this.passed = true;
            }
            //
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
