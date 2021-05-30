/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;


 

import producers.ButtonsProd;
import producers.ArmsProd;
import producers.LegsProd;
import java.util.concurrent.Semaphore;
import producers.BodyProd;


/**
 *
 * @author sebastian
 */
public class Employee extends Thread {
    
    public ButtonsProd hireProdEmployee(Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex, String name) {
        
        ButtonsProd bp = new ButtonsProd(semButtonProd,  semButtonCons, mutex, name);
        
        return bp;
    }
    
    public ArmsProd hireArmsProdEmloyee(Semaphore semArmProd, Semaphore semArmCons, Semaphore mutex, String name) {
        ArmsProd ap = new ArmsProd(semArmProd, semArmCons, mutex, name);
        return ap;
    }
    
    public LegsProd hireLegsProdEmloyee(Semaphore semLegsProd, Semaphore semLegsCons, Semaphore mutex, String name) {
        LegsProd lp = new LegsProd(semLegsProd, semLegsCons, mutex, name);
        return lp;
    }
    
     public BodyProd hireBodyProdEmloyee(Semaphore semBodyProd, Semaphore semBodyCons, Semaphore mutex, String name) {
        BodyProd bodp = new BodyProd(semBodyProd, semBodyCons, mutex, name);
        return bodp;
    }
     
     public Assembler hireAssembler(Semaphore semConsButton, Semaphore semProdButton, Semaphore semProdArms, Semaphore semConsArms, Semaphore semProdLegs, Semaphore semConsLegs, Semaphore semProdBody, Semaphore semConsBody, String name, Semaphore mutex){
        Assembler a = new Assembler(semConsButton, semProdButton, semProdArms, semConsArms,semProdLegs, semConsLegs, semProdBody,semConsBody,name, mutex);
        return a;
     }
    
}
