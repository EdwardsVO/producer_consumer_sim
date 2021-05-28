/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producers;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import administration.Almacen;
import functions.Time;
import gui.Interfaz;
import gui.main;

/**
 *
 * @author sebastian
 */
public class LegsProd extends Thread{
    
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
    
    public LegsProd(Semaphore semLegsProd, Semaphore semLegsCons, Semaphore mutex, String name){
        this.semLegsCons = semLegsCons;
        this.semLegsProd = semLegsProd;
        this.mutex = mutex;
        this.name = name;
    }
    
    public void run() {
        while(this.start){
            while(this.cantProduc < 36) {
                try{
                    this.semLegsProd.acquire();
                    this.mutex.acquire();
                    
                    Thread.sleep(Almacen.dayEquiv * 2);
                    
                    Almacen.contLegs++;
                    this.cantProduc++;
                    
                    this.console3.setText("Productor " + this.name + " ha fabricado una pierna nueva, ahora hay " + Almacen.contLegs + " piernas en el almacen.");
                    
                    
                    
                    this.mutex.release();
                    this.semLegsCons.release();
                    
                } catch (InterruptedException ex){
                    Logger.getLogger(LegsProd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if(Time.passed == true){
//                this.cantProduc = 0;
//            }
        }
    }
    
    public void showProduced(javax.swing.JTextPane console3) {
        this.console3 = console3;
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
