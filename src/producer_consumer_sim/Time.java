/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edward Vergel
 */
public class Time extends Thread {

    public void run() {
        while (true) {
            while (Almacen.hoursPassed != 24) {
                try {
                    Thread.sleep(Almacen.dayEquiv / 24);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
                }
                Almacen.hoursPassed++;
            }
            Almacen.daysPassed++;
            Almacen.hoursPassed = 0;

            System.out.println("Han transcurrido " + Almacen.daysPassed + " dias");
        }
    }
}
