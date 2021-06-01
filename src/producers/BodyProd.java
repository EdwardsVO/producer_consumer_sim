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
public class BodyProd extends Thread {

    private String name;
    private main main;
    private boolean start;
    private Almacen almacen;
    private Semaphore mutex;
    private Semaphore semBodyProd;
    private Semaphore semBodyCons;
    private int BodyPerDay = 4;
    private int cantProduc = 0;
    private Time time;
    private String produced = "";
    private javax.swing.JTextPane console4;
    private javax.swing.JTextPane bodyQuantity;

    public BodyProd(Semaphore semBodyProd, Semaphore semBodyCons, Semaphore mutex, String name) {
        this.semBodyCons = semBodyCons;
        this.semBodyProd = semBodyProd;
        this.mutex = mutex;
        this.name = name;
        this.start = true;
    }

    public void run() {
        while (start) {
                while (Almacen.daysPassed % 2 != 0 && this.cantProduc != 1) {
                    try {
                        this.semBodyProd.acquire();
                        this.mutex.acquire();
                        Almacen.contBody++;
                        this.cantProduc++;
                        this.console4.setText("Productor " + this.name + " ha fabricado un cuerpo central");
                        this.bodyQuantity.setText(String.valueOf(Almacen.contBody));
                        
                                                System.out.println("\n Cantidad de Cuerpos en el almacen: " + Almacen.contBody + "\n");

                        
                        Thread.sleep(Almacen.dayEquiv * 3);

                        this.mutex.release();
                        this.semBodyCons.release();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BodyProd.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Time.passed == true) {
                    this.cantProduc = 0;
                }   
        }

    }

    public void showProduced(javax.swing.JTextPane console4) { //AQUI
        this.console4 = console4;
    }

    public void bodyQuantity(javax.swing.JTextPane bodyQuantity) { //AQUI
        this.bodyQuantity = bodyQuantity;
    }

    public void init() {
        this.start = true;
    }

    public int getBodyPerDay() {
        return BodyPerDay;
    }

    public void setBodyPerDay(int BodyPerDay) {
        this.BodyPerDay = BodyPerDay;
    }

}
