/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import functions.Time;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class Boss extends Thread {

    private Semaphore mutexAdmin;
    javax.swing.JTextPane daysLeft;
    Time time;

    private boolean start = true;

    public Boss(Semaphore mutexAdmin, javax.swing.JTextPane daysLeft, Time time) {
        this.mutexAdmin = mutexAdmin;
        this.daysLeft = daysLeft;
        this.time = time;
    }

    public void run() {
        while (start) {
            try {
                //Thread.sleep(2*(Almacen.dayEquiv/3));
                Thread.sleep(2*(Almacen.dayEquiv/3));
                mutexAdmin.acquire(); //BOSS WORKING
                Thread.sleep(Almacen.dayEquiv / 3);
                Almacen.daysLeft--;
                mutexAdmin.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.daysLeft.setText(String.valueOf(Almacen.daysLeft));
            System.out.println(Almacen.daysLeft);
        }

    }
}
