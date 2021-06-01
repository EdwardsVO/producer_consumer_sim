package administration;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assembler extends Thread {

    private Semaphore semConsButton;
    private Semaphore semProdButton;
    private Semaphore semProdArms;
    private Semaphore semConsArms;
    private Semaphore semProdLegs;
    private Semaphore semConsLegs;
    private Semaphore semProdBody;
    private Semaphore semConsBody;
    private Semaphore mutex;
    private Semaphore mutexButtonProd;
    private Semaphore mutexArmsProd;
    private Semaphore mutexLegsProd;
    private Semaphore mutexBodyProd;
    
    

    private String name;
    private Almacen almacen;
    private boolean start;
    private int panasPerDay = 1;
    private int cantPanasProduc = 0;
    javax.swing.JTextPane panasBuilt;
    javax.swing.JTextPane console;

    public Assembler(Semaphore semConsButton, Semaphore semProdButton, Semaphore semProdArms, Semaphore semConsArms, Semaphore semProdLegs, Semaphore semConsLegs, Semaphore semProdBody, Semaphore semConsBody, String name, Semaphore mutex, Semaphore mutexButtonProd, Semaphore mutexArmsProd, Semaphore mutexLegsProd, Semaphore mutexBodyProd) {
        this.semProdButton = semProdButton;
        this.semConsButton = semConsButton;
        this.semProdArms = semProdArms;
        this.semConsArms = semConsArms;
        this.semProdLegs = semProdLegs;
        this.semConsLegs = semConsLegs;
        this.semProdBody = semProdBody;
        this.semConsBody = semConsBody;
        this.mutexButtonProd = mutexButtonProd;
        this.mutexArmsProd = mutexArmsProd;
        this.mutexLegsProd = mutexLegsProd;
        this.mutexBodyProd = mutexBodyProd;
        this.mutex = mutex;
        this.name = name;
        this.start = true;
    }

    public void run() {
        while (true) {
             if (Almacen.contButtons >= 8 && Almacen.contArms >= 2 && Almacen.contLegs >= 2 && Almacen.contBody >=1) {
                
                    
                 try {
                     mutex.acquire();
                     semConsButton.acquire(8);
                     semConsArms.acquire(2);
                     semConsLegs.acquire(2);
                     semConsBody.acquire(1);
                     mutexButtonProd.acquire();
                     mutexArmsProd.acquire();
                     mutexLegsProd.acquire();
                     mutexBodyProd.acquire();
                     
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Assembler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                Almacen.contArms -= 2;
                Almacen.contLegs -= 2;
                Almacen.contButtons -= 8;
                Almacen.contBody -= 1;
                Almacen.panasBuilt++;
                this.cantPanasProduc++;
                this.panasBuilt.setText(String.valueOf(Almacen.panasBuilt));
                this.console.setText("El ensamblador " + name + " ha armado un PANA");
                try {
                    Thread.sleep(Almacen.dayEquiv / this.panasPerDay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Assembler.class.getName()).log(Level.SEVERE, null, ex);
                }
                mutexBodyProd.release();
                mutexLegsProd.release();
                mutexArmsProd.release();
                mutexButtonProd.release();
                mutex.release();
                semProdButton.release(8);
                semProdArms.release(2);
                semProdLegs.release(2);
                semProdBody.release(1);
        }
        }
        
    }

    public void showPanas(javax.swing.JTextPane panasBuilt) {
        this.panasBuilt = panasBuilt;
    }

    public void showWork(javax.swing.JTextPane console) {
        this.console = console;
    }

    public int getPanasPerDay() {
        return panasPerDay;
    }

    public void setPanasPerDay(int panasPerDay) {
        this.panasPerDay = panasPerDay;
    }

}
