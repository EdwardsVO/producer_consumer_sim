package producers;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import administration.Almacen;
import functions.Time;
import gui.Interfaz;
import gui.main;

public class LegsProd extends Thread {

    private String name;
    private main main;
    private boolean start;
    private Almacen almacen;
    private Semaphore mutex;
    private Semaphore semLegsProd;
    private Semaphore semLegsCons;
    private int LegsPerDay = 1;
    private int cantProduc = 0;
    private Time time;
    private String produced = "";
    private javax.swing.JTextPane console3;
    private javax.swing.JTextPane legsQuantity;

    public LegsProd(Semaphore semLegsProd, Semaphore semLegsCons, Semaphore mutex, String name) {
        this.semLegsCons = semLegsCons;
        this.semLegsProd = semLegsProd;
        this.mutex = mutex;
        this.name = name;
        this.start = true;
    }

    public void run() {
        while (start) {
                if (Almacen.daysPassed % 2 == 0 && this.cantProduc < 1) {
                    try {
                        this.semLegsProd.acquire();
                        this.mutex.acquire();
                        Almacen.contLegs++;
                        this.cantProduc++;
                        this.console3.setText("Productor " + this.name + " ha fabricado una pierna nueva");
                        this.legsQuantity.setText(String.valueOf(Almacen.contLegs));
                        Thread.sleep(2 * (Almacen.dayEquiv) / this.LegsPerDay);
                        this.mutex.release();
                        this.semLegsCons.release();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LegsProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Time.passed == true) {
                    this.cantProduc = 0;
                }
            
        }
    
    }

    public void showProduced(javax.swing.JTextPane console3) {
        this.console3 = console3;
    }

    public void legsQuantity(javax.swing.JTextPane legsQuantity) {
        this.legsQuantity = legsQuantity;
    }

    public void init() {
        this.start = true;
    }

    public int getLegsPerDay() {
        return LegsPerDay;
    }

    public void setLegsPerDay(int LegsPerDay) {
        this.LegsPerDay = LegsPerDay;
    }

}
