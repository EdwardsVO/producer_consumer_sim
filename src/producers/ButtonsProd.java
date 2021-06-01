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
public class ButtonsProd extends Thread {

    private String name;
    private main main;
    private boolean start;
    private Almacen almacen;
    private Semaphore mutex;
    private Semaphore semButtonProd;
    private Semaphore semButtonCons;
    private int ButtonsPerDay = 4;
    private int cantProduc = 0;
    private Time time;
    private String produced = "";
    private javax.swing.JTextPane console1;
    private javax.swing.JTextPane buttonQuantity;
    private boolean exit;

    public ButtonsProd(Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex, String name) {
        this.mutex = mutex;
        this.semButtonProd = semButtonProd;
        this.semButtonCons = semButtonCons;
        this.name = name;
        this.start = true;
    }

    public void run() {
        while(start){
                while (this.cantProduc < 4) {
                    try {
                        this.semButtonProd.acquire();
                        this.mutex.acquire();
                        Almacen.contButtons++;
                        this.cantProduc++;
                        this.console1.setText("Productor " + this.name + " ha fabricado 1 boton");
                        this.buttonQuantity.setText(String.valueOf(Almacen.contButtons));
                        
//                        Thread.State state = Thread.currentThread().getState();
//                        System.out.println(state);
                        
                                                System.out.println("\n Cantidad de botones en el almacen: " + Almacen.contButtons + "\n");

                        Thread.sleep(Almacen.dayEquiv / this.ButtonsPerDay);
                        
                        this.mutex.release();
                        this.semButtonCons.release();

                    } catch (InterruptedException ex) {
                        Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Time.passed == true) {
                    this.cantProduc = 0;
//                    
                }
                
            } 
        
    }

    public void showProduced(javax.swing.JTextPane console1) { //AQUI
        this.console1 = console1;
    }

    public void buttonQuantity(javax.swing.JTextPane buttonQuantity) { //AQUI
        this.buttonQuantity = buttonQuantity;
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
