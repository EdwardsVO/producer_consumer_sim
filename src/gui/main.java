package gui;

import administration.Almacen;
import administration.Assembler;
import administration.Boss;
import administration.Employee;
import functions.Time;
import functions.dataFunctions;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import producers.ButtonsProd;
import producers.ArmsProd;
import producers.LegsProd;
import producers.BodyProd;
import administration.Manager;

public class main {

    boolean onSim = false;
    
    boolean running = false;

    Almacen almacen = new Almacen(); //VARIABLES VOLATILES
    Employee emp = new Employee(); //FUNCIONES PARA CONTRATAR Y DESPEDIR
    dataFunctions df = new dataFunctions();
    Interfaz interfaz;
    Time currentTime;
    Boss currentBoss;

    
    
    public String[] array = this.readData();

    ArrayList<ButtonsProd> buttonsProdEmp = new ArrayList<ButtonsProd>(); //PRODUCTORES DE BOTONES MAXIMOS
    ArrayList<ArmsProd> armsProdEmp = new ArrayList<>(); //PRODUCTORES DE BRAZOS MAXIMOS
    ArrayList<LegsProd> legsProdEmp = new ArrayList<>(); //PRODUCTORES DE PIERNAS MAXIMOS
    ArrayList<BodyProd> bodyProdEmp = new ArrayList<>(); //PRODUCTORES DE CUERPO CENTRAL MAXIMOS
    ArrayList<Assembler> assemEmp = new ArrayList<>();

    Semaphore mutexButtons = new Semaphore(1);
    Semaphore mutexArms = new Semaphore(1);
    Semaphore mutexLegs = new Semaphore(1);
    Semaphore mutexBody = new Semaphore(1);
    Semaphore mutexAssem = new Semaphore (1);
    Semaphore mutexAdmin = new Semaphore(1);
    
    //PROD - CONS ----> BOTONES
    Semaphore semButtonProd = new Semaphore(60); //TOTAL DE BOTONES
    Semaphore semButtonCons = new Semaphore(0); //CANTIDAD EN CONSUMO   

    //PROD - CONS ----> BRAZOS
    Semaphore semArmsProd = new Semaphore(40);
    Semaphore semArmsCons = new Semaphore(0);

    //PROD - CONS ----> PIERNAS
    Semaphore semLegsProd = new Semaphore(36);
    Semaphore semLegsCons = new Semaphore(0);

    //PROD - CONS ----> CUERPO CENTRAL
    Semaphore semBodyProd = new Semaphore(15);
    Semaphore semBodyCons = new Semaphore(0);
    
    //
    javax.swing.JTextPane buttonsProdu;
    javax.swing.JTextPane armsProdu;
    javax.swing.JTextPane legsProdu;
    javax.swing.JTextPane bodyProdu;
    javax.swing.JTextPane assemQ;
    

    public void main(Interfaz interfaz){
        this.interfaz = interfaz;
        this.interfaz.setVisible(true);
    }

    public void startAdministration(javax.swing.JTextPane panasDistributed, javax.swing.JTextPane panasBuilt, javax.swing.JTextPane tandas, javax.swing.JTextPane daysLeft){
        Manager manager = new Manager(mutexAdmin);
        Boss boss = new Boss(mutexAdmin, daysLeft, this.currentTime);
        this.currentBoss = boss;
        
        manager.showDistributed(panasDistributed, panasBuilt, tandas);
        manager.start(); 
        boss.start();
    }
    
    public void hireAssembler(javax.swing.JTextPane panasBuilt, javax.swing.JTextPane console){
        try{
        
            if(this.running) {
               if(assemEmp.size() < Integer.valueOf(this.array[15])){
                    Assembler assem = emp.hireAssembler(semButtonCons, semButtonProd, semArmsProd, semArmsCons, semLegsProd, semLegsCons, semBodyCons, semBodyProd, String.valueOf(assemEmp.size() + 1), mutexAssem, mutexButtons, mutexArms, mutexLegs, mutexBody);
                    assem.showWork(console);
                    assem.showPanas(panasBuilt);
                    assem.start();
                    assemEmp.add(assem);
                    
               } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de ensambladores alcanzada");
               }
                
            } else {
            
            
                if(assemEmp.size() < Integer.valueOf(this.array[15])){
                    Assembler assem = emp.hireAssembler(semButtonCons, semButtonProd, semArmsProd, semArmsCons, semLegsProd, semLegsCons, semBodyCons, semBodyProd, String.valueOf(assemEmp.size() + 1), mutexAssem, mutexButtons, mutexArms, mutexLegs, mutexBody);
                    assem.showWork(console);
                    assem.showPanas(panasBuilt);
                    assemEmp.add(assem);
                    for (int i = 0; i < assemEmp.size(); i++) {
                            assemEmp.get(i).setPanasPerDay(1 * assemEmp.size());
                        }
                }else {
                    JOptionPane.showMessageDialog(null, "Capacidad de ensambladores alcanzada");
                }
            }
        } catch(Error e) {
            System.out.println(e);
        }
    }
    
    public void hireProdButton(javax.swing.JTextPane console1, javax.swing.JTextPane buttonQuantity) {
        try {
            if(this.running) {
                if(buttonsProdEmp.size() < Integer.valueOf(this.array[10])) {
                    ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutexButtons, String.valueOf(buttonsProdEmp.size() +1));
                    buttonProd.showProduced(console1);
                    buttonProd.buttonQuantity(buttonQuantity);
                    buttonProd.start();
                    buttonsProdEmp.add(buttonProd);
                } else {
                     JOptionPane.showMessageDialog(null, "Capacidad de productores de botones alcanzada");
                }
                
            } else {
                if (buttonsProdEmp.size() < Integer.valueOf(this.array[10])) { //LIMITE DE PRODUCTORES 
                    ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutexButtons, String.valueOf(buttonsProdEmp.size() + 1));
                    buttonProd.showProduced(console1);
                    buttonProd.buttonQuantity(buttonQuantity);
                    buttonsProdEmp.add(buttonProd);
                    for (int i = 0; i < buttonsProdEmp.size(); i++) {
                        buttonsProdEmp.get(i).setButtonsPerDay(4 * buttonsProdEmp.size());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de botones alcanzada");
                }
            }

            } catch (Error e) {
                System.out.println(e);

        }

    }

    public void hireProdArms(javax.swing.JTextPane console2, javax.swing.JTextPane armsQuantity) {
        try {
            
            if(this.running) {
                if (armsProdEmp.size() < Integer.valueOf(this.array[11])) {
                    ArmsProd armsProd = emp.hireArmsProdEmloyee(semArmsProd, semArmsCons, mutexArms, String.valueOf(armsProdEmp.size() + 1));
                    armsProd.showProduced(console2);
                    armsProd.armsQuantity(armsQuantity);
                    armsProd.start();
                    armsProdEmp.add(armsProd);
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de brazos alcanzada");
                }

                } else {
            
                if (armsProdEmp.size() < Integer.valueOf(this.array[11])) {
                    ArmsProd armsProd = emp.hireArmsProdEmloyee(semArmsProd, semArmsCons, mutexArms, String.valueOf(armsProdEmp.size() + 1));
                    armsProd.showProduced(console2);
                    armsProd.armsQuantity(armsQuantity);
                    armsProdEmp.add(armsProd);
                    for (int i = 0; i < armsProdEmp.size(); i++) {
                        armsProdEmp.get(i).setArmsPerDay(1 * armsProdEmp.size());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de brazos alcanzada");
                }
            }
        
        } catch (Error e) {
            System.out.println(e);
        }
    }

    public void hireProdLegs(javax.swing.JTextPane console3, javax.swing.JTextPane legsQuantity) {
        try {
            if(this.running){
                if (legsProdEmp.size() < Integer.valueOf(this.array[12])) {
                    LegsProd legsProd = emp.hireLegsProdEmloyee(semLegsProd, semLegsCons, mutexLegs, String.valueOf(legsProdEmp.size() + 1));
                    legsProd.showProduced(console3);
                    legsProd.legsQuantity(legsQuantity);
                    legsProd.start();
                    legsProdEmp.add(legsProd);
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de piernas alcanzada.");
                }
                
                
            } else {
            
            
                if (legsProdEmp.size() < Integer.valueOf(this.array[12])) {
                    LegsProd legsProd = emp.hireLegsProdEmloyee(semLegsProd, semLegsCons, mutexLegs, String.valueOf(legsProdEmp.size() + 1));
                    legsProd.showProduced(console3);
                    legsProd.legsQuantity(legsQuantity);
                    legsProdEmp.add(legsProd);
                    for (int i = 0; i < legsProdEmp.size(); i++) {
                        legsProdEmp.get(i).setLegsPerDay(1 * legsProdEmp.size());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de piernas alcanzada");
                }
            }
        } catch (Error e) {
            System.out.println(e);
        }
    }

    public void hireProdBody(javax.swing.JTextPane console4, javax.swing.JTextPane bodyQuantity) {
        try {
            
            if(this.running) {
                if (bodyProdEmp.size() < Integer.valueOf(this.array[13])) {
                BodyProd bodyProd = emp.hireBodyProdEmloyee(semBodyProd, semBodyCons, mutexBody, String.valueOf(bodyProdEmp.size() + 1));
                bodyProd.showProduced(console4);
                bodyProd.bodyQuantity(bodyQuantity);
                bodyProd.start();
                bodyProdEmp.add(bodyProd);
                
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de cuerpo central alcanzada");
                }
                
                
            } else {
            
            
                if (bodyProdEmp.size() < Integer.valueOf(this.array[13])) {
                    BodyProd bodyProd = emp.hireBodyProdEmloyee(semBodyProd, semBodyCons, mutexBody, String.valueOf(bodyProdEmp.size() + 1));
                    bodyProd.showProduced(console4);
                    bodyProd.bodyQuantity(bodyQuantity);
                    bodyProdEmp.add(bodyProd);
                    for (int i = 0; i < bodyProdEmp.size(); i++) {
                        bodyProdEmp.get(i).setBodyPerDay((1 * bodyProdEmp.size()));
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de productores de cuerpo central alcanzada");
                }
            }
        } catch (Error e) {
            System.out.println(e);
        }
    }
    
    public void delAssembler(){
        
        if(this.running) {
            if(assemEmp.size() > Integer.parseInt(this.array[14])) {
                int out = assemEmp.size()-1;
                assemEmp.get(out).stop();
                assemEmp.remove(out);
            } else {
                JOptionPane.showMessageDialog(null, "Valor de ensambladores mínimo alcanzado", "ERROR", 0);  

            }
        } else {
            if(assemEmp.size() > Integer.parseInt(this.array[14])){
                assemEmp.remove(0);
            }else {
                JOptionPane.showMessageDialog(null, "Valor de ensambladores mínimo alcanzado", "ERROR", 0);  
            }
            }
    }
    
    public void deleteButtonProd(){
        
        if(this.running) {
            if(buttonsProdEmp.size() > Integer.parseInt(this.array[6])) {
                int out = buttonsProdEmp.size()-1;
                buttonsProdEmp.get(out).stop();
                buttonsProdEmp.remove(out);
            } else {
             JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);       

            }
        } else {
        
        if(buttonsProdEmp.size() > Integer.parseInt(this.array[6])){
            buttonsProdEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);       
        }
        }
    }
    public void deleteArmsProd(){
        if(this.running) {
            if(armsProdEmp.size() > Integer.parseInt(this.array[7])) {
                int out = armsProdEmp.size()-1;
                armsProdEmp.get(out).stop();
                armsProdEmp.remove(out);
            } else {
                JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }

            } else {        
        
        if(armsProdEmp.size() > Integer.parseInt(this.array[7])){
            armsProdEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }
        }
    }
    
    public void deleteLegsProd(){
        if(this.running) {
            if(legsProdEmp.size() > Integer.parseInt(this.array[8])) {
                int out = legsProdEmp.size()-1;
                legsProdEmp.get(out).stop();
                legsProdEmp.remove(out);
            } else {
                JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }

            } else {
        
        
        
            if(legsProdEmp.size() > Integer.parseInt(this.array[8])){
                legsProdEmp.remove(0);
            }else {
                JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }
        }
        }
    
    public void deleteBodyProd(){
        
        if(this.running) {
            if(bodyProdEmp.size() > Integer.parseInt(this.array[9])) {
                int out = bodyProdEmp.size()-1;
                bodyProdEmp.get(out).stop();
                bodyProdEmp.remove(out);
            } else {
                JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        
            }
            
            } else {
            if(bodyProdEmp.size() > Integer.parseInt(this.array[9])){
                bodyProdEmp.remove(0);
            }else {
                JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }
        }
    }
    
    

    public void initSimulation(javax.swing.JTextPane hours, javax.swing.JTextPane days, javax.swing.JTextPane daysLeft, javax.swing.JTextPane panasDistributed, javax.swing.JTextPane panasBuilt, javax.swing.JTextPane tandas, javax.swing.JTextPane console1, javax.swing.JTextPane buttonQuantity, javax.swing.JTextPane console2, javax.swing.JTextPane armsQuantity, javax.swing.JTextPane console3, javax.swing.JTextPane legsQuantity, javax.swing.JTextPane console4, javax.swing.JTextPane bodyQuantity, javax.swing.JTextPane console, javax.swing.JTextPane buttonsProdu, javax.swing.JTextPane armsProdu, javax.swing.JTextPane legsProdu, javax.swing.JTextPane bodyProdu, javax.swing.JTextPane assemQ) {
        this.armsProdu = armsProdu; 
        this.legsProdu = legsProdu;
        this.bodyProdu = bodyProdu;
        this.buttonsProdu = buttonsProdu;
        this.assemQ = assemQ;
        this.running = true;
        
        if (this.onSim == false) {
            this.onSim = true;
            this.startAdministration(panasDistributed, panasBuilt, tandas, daysLeft); //FALTA EL BOSS
            this.createTime(hours, days, daysLeft);
            
            if(buttonsProdEmp.size() < Integer.parseInt(this.array[6])) {
                buttonsProdEmp.clear();
                for(int i = 0; i < Integer.parseInt(this.array[6]); i++){

                    ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutexButtons, String.valueOf(buttonsProdEmp.size() + 1));
                    buttonProd.showProduced(console1);
                    buttonProd.buttonQuantity(buttonQuantity);
                    this.buttonsProdu.setText(String.valueOf(i + 1));
                    buttonsProdEmp.add(buttonProd);  
                    
                }
                
                for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
            }
                
            } else {
            
                for (int i = 0; i < buttonsProdEmp.size(); i++) {
                    buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
                }
            }
            
            if(armsProdEmp.size() < Integer.parseInt(this.array[7])) {
                armsProdEmp.clear();
                
                for (int i = 0; i < Integer.parseInt(this.array[7]); i++) {
                    ArmsProd armsProd = emp.hireArmsProdEmloyee(semArmsProd, semArmsCons, mutexArms, String.valueOf(armsProdEmp.size() + 1));
                    armsProd.showProduced(console2);
                    armsProd.armsQuantity(armsQuantity);
                    this.armsProdu.setText(String.valueOf(i +1));
                    armsProdEmp.add(armsProd);
                }
                for (int i = 0; i < armsProdEmp.size(); i++) {
                    armsProdEmp.get(i).start();
                }
                
            } else {
                for (int i = 0; i < armsProdEmp.size(); i++) {
                    armsProdEmp.get(i).start();
                }
            }
            
            if(legsProdEmp.size() < Integer.parseInt(this.array[8])) {
                legsProdEmp.clear();
                
                for (int i = 0; i < Integer.parseInt(this.array[8]); i++) {
                    LegsProd legsProd = emp.hireLegsProdEmloyee(semLegsProd, semLegsCons, mutexLegs, String.valueOf(legsProdEmp.size() + 1));
                    legsProd.showProduced(console3);
                    legsProd.legsQuantity(legsQuantity);
                    this.legsProdu.setText(String.valueOf(i +1));
                    legsProdEmp.add(legsProd);
                }
                
                for (int i = 0; i < legsProdEmp.size(); i++) {
                    legsProdEmp.get(i).start();
                }
            } else {
                for (int i = 0; i < legsProdEmp.size(); i++) {
                    legsProdEmp.get(i).start();
                }
            }
            
            if(bodyProdEmp.size() < Integer.parseInt(this.array[9])) {
                bodyProdEmp.clear();
            
            
            for (int i = 0; i < Integer.parseInt(this.array[9]); i++) {
                BodyProd bodyProd = emp.hireBodyProdEmloyee(semBodyProd, semBodyCons, mutexBody, String.valueOf(bodyProdEmp.size() + 1));
                bodyProd.showProduced(console4);
                bodyProd.bodyQuantity(bodyQuantity);
                this.bodyProdu.setText(String.valueOf(i +1));
                bodyProdEmp.add(bodyProd);
                
            }
            
                for(int i = 0; i < bodyProdEmp.size(); i++) {
                    bodyProdEmp.get(i).start();
                }
            
            } else {
            
                for (int i = 0; i < bodyProdEmp.size(); i++) {
                    bodyProdEmp.get(i).start();
                }
            }
            
            if(assemEmp.size() < Integer.parseInt(this.array[14])) {
                assemEmp.clear();
                
                for (int i = 0; i < Integer.parseInt(this.array[14]); i++) {
                    Assembler assem = emp.hireAssembler(semButtonCons, semButtonProd, semArmsProd, semArmsCons, semLegsProd, semLegsCons, semBodyCons, semBodyProd, String.valueOf(assemEmp.size() + 1), mutexAssem, mutexButtons, mutexArms, mutexLegs, mutexBody);
                    assem.showWork(console);
                    assem.showPanas(panasBuilt);
                    this.assemQ.setText(String.valueOf(i + 1));
                    assemEmp.add(assem);
                    
                }
                
                for (int i = 0; i < assemEmp.size(); i++) {
                    assemEmp.get(i).start();
                    
                }
                
            } else {
                for (int i = 0; i < assemEmp.size(); i++) {
                    assemEmp.get(i).start();
                }
            }
            
        }else {
            JOptionPane.showMessageDialog(null, "Simulacion Inicializada", "ERROR", 0);

        }
        
    }
    

    public void stopSimulation() { //ARREGLAR
        if (this.onSim == true) {
            
            try{
            Almacen.daysLeft = 0; 
            this.onSim = false;
            
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                try{
                    buttonsProdEmp.get(i).stop(); // SE DETIENEN TODOS LOS PRODUCTORES
                } catch(Error e) {
                    System.out.println(e);
                }
            }
            
            for(int i = 0; i < armsProdEmp.size(); i++) {
                try{
                    armsProdEmp.get(i).stop();
                } catch(Error e) {
                    System.out.println(e);
                }
            }
            
            for (int i = 0; i < legsProdEmp.size(); i++) {
                try{
                    legsProdEmp.get(i).stop();
                } catch (Error e){
                    System.out.println(e);
                }
                
            }
            
            for (int i = 0; i < bodyProdEmp.size(); i++) {
                try{
                    
                    bodyProdEmp.get(i).stop();
                } catch(Error e ) {
                    System.out.println(e);
                }
                
            }
            
            for (int i = 0; i < assemEmp.size(); i++) {
                try{
                    
                    assemEmp.get(i).stop();
                } catch(Error e) {
                    System.out.println(e);
                }
            }
            
            this.currentTime.stop();
            this.currentBoss.stop();
            
            } catch(Error e){
                System.out.println(e);
            }
           
         
            
        } else {
            JOptionPane.showMessageDialog(null, "Inicialice simulacion", "ERROR", 0);
        }
    }
    
    public void createTime(javax.swing.JTextPane hours, javax.swing.JTextPane days, javax.swing.JTextPane daysLeft) {
        Time time = new Time(hours, days, daysLeft, mutexAdmin);
        this.currentTime = time;
        this.currentTime.start();
    }
    
    public String[] readData() {
        String data = df.csvReader();
        this.array = data.split(",");
        return this.array; 
    }

}
