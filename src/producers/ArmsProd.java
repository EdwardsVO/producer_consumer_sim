package producers;

import administration.Almacen;
import functions.Time;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArmsProd extends Thread {

    private String name;
    private Almacen almacen;
    private Semaphore mutex;
    private Semaphore semArmProd;
    private Semaphore semArmCons;
    private int ArmsPerDay = 1;
    private int cantArmsProduc = 0;
    private Time time;
    private String produced = "";
    private boolean start;
    private javax.swing.JTextPane console2;
    private javax.swing.JTextPane armsQuantity;

    public ArmsProd(Semaphore semArmProd, Semaphore semArmCons, Semaphore mutex, String name) {
        this.semArmProd = semArmProd;
        this.semArmCons = semArmCons;
        this.mutex = mutex;
        this.name = name;
    }

    public void run() {
        while (true) {
                if (this.cantArmsProduc != 1) {
                    try {
                        this.semArmProd.acquire();
                        this.mutex.acquire();
                        Almacen.contArms++;
                        this.cantArmsProduc++;
                        this.console2.setText("Productor de brazos " + this.name + " ha fabricado 1 brazo");
                        this.armsQuantity.setText(String.valueOf(Almacen.contArms));
                        Thread.sleep(Almacen.dayEquiv / this.ArmsPerDay);
                        this.mutex.release();
                        this.semArmCons.release();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ArmsProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Time.passed == true) {
                    this.cantArmsProduc = 0;
                }   
        }
    }

    public void showProduced(javax.swing.JTextPane console2) {
        this.console2 = console2;
    }

    public void armsQuantity(javax.swing.JTextPane armsQuantity) { //AQUI
        this.armsQuantity = armsQuantity;
    }

    public int getArmsPerDay() {
        return ArmsPerDay;
    }

    public void setArmsPerDay(int ArmsPerDay) {
        this.ArmsPerDay = ArmsPerDay;
    }

    public void init() {
        this.start = true;
    }

}
