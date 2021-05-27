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

/**
 *
 * @author sebastian
 */
public class ButtonsProd extends Thread {

    private String name;
    private boolean start;
    private Almacen almacen;
    private Semaphore mutex;
    private Semaphore semButtonProd;
    private Semaphore semButtonCons;
    private int ButtonsPerDay = 4;
    private int cantProduc = 0;
    private Time time;

    public ButtonsProd(Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex, String name) {
        this.mutex = mutex;
        this.semButtonProd = semButtonProd;
        this.semButtonCons = semButtonCons;
        this.name = name;
    }

    public void run() {
        while (this.start) {
            while (this.cantProduc != 4) {
                try {
                    this.semButtonProd.acquire();
                    this.mutex.acquire();
                    Almacen.contButtons++;
                    this.cantProduc++;
                    System.out.println("Productor " + this.name + " ha fabricado 1 boton, ahora hay " + Almacen.contButtons + " botones en el almacen.");
                    Thread.sleep(Almacen.dayEquiv / this.ButtonsPerDay);
                    this.mutex.release();
                    this.semButtonCons.release();

                } catch (InterruptedException ex) {
                    Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(Time.passed == true) {
                this.cantProduc = 0;
            }
            
        }
    }

    public void kill() {
        this.start = false;
    }

    public void init() {
        this.start = true;
    }

    public int getButtonsPerDay() {
        return ButtonsPerDay;
    }

    public void setButtonsPerDay(int ButtonsPerDay) {
        this.ButtonsPerDay = ButtonsPerDay;
    }

    public int getCantProduc() {
        return cantProduc;
    }

    public void setCantProduc(int cantProduc) {
        this.cantProduc = cantProduc;
    }

}
