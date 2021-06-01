/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Edward Vergel
 */
public class main {

    boolean onSim = false;

    Almacen almacen = new Almacen(); //VARIABLES VOLATILES
    Employee emp = new Employee(); //FUNCIONES PARA CONTRATAR Y DESPEDIR
    

    dataFunctions df = new dataFunctions();
    
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
    Semaphore mutexPanas = new Semaphore(1);
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
    

    

    public void startAdministration(javax.swing.JTextPane panasDistributed,javax.swing.JTextPane panasBuilt){
        Manager manager = new Manager(mutexAdmin);
        Boss boss = new Boss(mutexAdmin);
        
        manager.showDistributed(panasDistributed, panasBuilt);
        manager.start();    
//        boss.start();
    }
    
    public void hireAssembler(javax.swing.JTextPane panasBuilt, javax.swing.JTextPane console){
        if(assemEmp.size() < Integer.valueOf(this.array[15])){
            Assembler assem = emp.hireAssembler(semButtonCons, semButtonProd, semArmsProd, semArmsCons, semLegsProd, semLegsCons, semBodyCons, semBodyProd, String.valueOf(assemEmp.size() + 1), mutexAssem );
            assem.showWork(console);
            assem.showPanas(panasBuilt);
            assemEmp.add(assem);
            for (int i = 0; i < assemEmp.size(); i++) {
                    assemEmp.get(i).setPanasPerDay(1 * assemEmp.size());
                }
        }else {
            JOptionPane.showMessageDialog(null, "Capacidad de productores de ensambladores alcanzada");
        }
    }
    
    public void hireProdButton(javax.swing.JTextPane console1, javax.swing.JTextPane buttonQuantity) {
        try {
            if (buttonsProdEmp.size() < Integer.valueOf(this.array[10])) { //LIMITE DE PRODUCTORES 
                ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutexButtons, String.valueOf(buttonsProdEmp.size() + 1));
                buttonProd.showProduced(console1);
                buttonProd.buttonQuantity(buttonQuantity);
                buttonsProdEmp.add(buttonProd);
                for (int i = 0; i < buttonsProdEmp.size(); i++) {
                    buttonsProdEmp.get(i).setButtonsPerDay(4 * buttonsProdEmp.size());
                }
                System.out.println("Se ha agregado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores de botones alcanzada");
            }

        } catch (Error e) {
            System.out.println(e);

        }

    }

    public void hireProdArms(javax.swing.JTextPane console2, javax.swing.JTextPane armsQuantity) {
        try {
            if (armsProdEmp.size() < Integer.valueOf(this.array[11])) {
                ArmsProd armsProd = emp.hireArmsProdEmloyee(semArmsProd, semArmsCons, mutexArms, String.valueOf(armsProdEmp.size() + 1));
                armsProd.showProduced(console2);
                armsProd.armsQuantity(armsQuantity);
                armsProdEmp.add(armsProd);
                for (int i = 0; i < armsProdEmp.size(); i++) {
                    armsProdEmp.get(i).setArmsPerDay(1 * armsProdEmp.size());
                }
                System.out.println("Se ha agregado un productor de brazos con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores de brazos alcanzada");
            }
        } catch (Error e) {
            System.out.println(e);
        }
    }

    public void hireProdLegs(javax.swing.JTextPane console3, javax.swing.JTextPane legsQuantity) {
        try {
            if (legsProdEmp.size() < Integer.valueOf(this.array[12])) {
                LegsProd legsProd = emp.hireLegsProdEmloyee(semLegsProd, semLegsCons, mutexLegs, String.valueOf(legsProdEmp.size() + 1));
                legsProd.showProduced(console3);
                legsProd.legsQuantity(legsQuantity);
                legsProdEmp.add(legsProd);
                for (int i = 0; i < legsProdEmp.size(); i++) {
                    legsProdEmp.get(i).setLegsPerDay(1 * legsProdEmp.size());
                }
                System.out.println("Se ha agregado un productor de piernas con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores de piernas alcanzada");
            }
        } catch (Error e) {
            System.out.println(e);
        }
    }

    public void hireProdBody(javax.swing.JTextPane console4, javax.swing.JTextPane bodyQuantity) {
        try {
            if (bodyProdEmp.size() < Integer.valueOf(this.array[13])) {
                BodyProd bodyProd = emp.hireBodyProdEmloyee(semBodyProd, semBodyCons, mutexBody, String.valueOf(bodyProdEmp.size() + 1));
                bodyProd.showProduced(console4);
                bodyProd.bodyQuantity(bodyQuantity);
                bodyProdEmp.add(bodyProd);
                for (int i = 0; i < bodyProdEmp.size(); i++) {
                    bodyProdEmp.get(i).setBodyPerDay((1 * bodyProdEmp.size()));
                    System.out.println(bodyProdEmp.get(i).getState());
                }
                System.out.println("Se ha agregado un productor de cuerpo central con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores de cuerpo central alcanzada");
            }
        } catch (Error e) {
            System.out.println(e);
        }
    }
    
    public void delAssembler(){
        if(assemEmp.size() > 1){
            assemEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de ensambladores mínimo alcanzado", "ERROR", 0);  
        }
    }
    
    public void deleteButtonProd(){
        if(buttonsProdEmp.size() > 1){
            buttonsProdEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);       
        }
    }
    public void deleteArmsProd(){
        if(armsProdEmp.size() > 1){
            armsProdEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }
    }
    public void deleteLegsProd(){
        if(legsProdEmp.size() > 1){
            legsProdEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }
    }
    public void deleteBodyProd(){
        if(bodyProdEmp.size() > 1){
            bodyProdEmp.remove(0);
        }else {
            JOptionPane.showMessageDialog(null, "Valor de productores mínimo alcanzado", "ERROR", 0);        }
    }
    
    

    public void initSimulation(javax.swing.JTextPane hours, javax.swing.JTextPane days, javax.swing.JTextPane daysLeft, javax.swing.JTextPane panasDistributed, javax.swing.JTextPane panasBuilt) {
        if (this.onSim == false) {
            this.onSim = true;
            df.csvReader();
            this.startAdministration(panasDistributed, panasBuilt); //FALTA EL BOSS
            this.createTime(hours, days, daysLeft);
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
            }
            for (int i = 0; i < armsProdEmp.size(); i++) {
                armsProdEmp.get(i).start();
            }

            for (int i = 0; i < legsProdEmp.size(); i++) {
                legsProdEmp.get(i).start();
            }
            for (int i = 0; i < bodyProdEmp.size(); i++) {
                bodyProdEmp.get(i).start();
            }
            for (int i = 0; i < assemEmp.size(); i++) {
                assemEmp.get(i).start();
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
                    System.out.println("Se ha detenido el productor de botones.");
                    buttonsProdEmp.get(i).stop(); // SE DETIENEN TODOS LOS PRODUCTORES
                } catch(Error e) {
                    System.out.println(e);
                }
            }
            
            for(int i = 0; i < armsProdEmp.size(); i++) {
                try{
                    System.out.println("Se ha detenido el productor de brazos.");
                    armsProdEmp.get(i).stop();
                } catch(Error e) {
                    System.out.println(e);
                }
            }
            
            for (int i = 0; i < legsProdEmp.size(); i++) {
                try{
                    System.out.println("Se ha detenido el productor de piernas.");
                    legsProdEmp.get(i).stop();
                } catch (Error e){
                    System.out.println(e);
                }
                
            }
            
            for (int i = 0; i < bodyProdEmp.size(); i++) {
                try{
                    System.out.println("Se ha detenido el productor de cuerpos.");
                    bodyProdEmp.get(i).stop();
                } catch(Error e ) {
                    System.out.println(e);
                }
                
            }
            
            for (int i = 0; i < assemEmp.size(); i++) {
                try{
                    System.out.println("Se ha detenido el ensamblador.");
                    assemEmp.get(i).stop();
                } catch(Error e) {
                    System.out.println(e);
                }
                
                
                
            }
            } catch(Error e){
                System.out.println(e);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Inicialice simulacion", "ERROR", 0);
        }
    }
    
    public void createTime(javax.swing.JTextPane hours, javax.swing.JTextPane days, javax.swing.JTextPane daysLeft) {
        Time time = new Time(hours, days, daysLeft);
        time.start();
        time.init();
    }
    
    public String[] readData() {
        String data = df.csvReader();
        this.array = data.split(",");
        return this.array; 
    }

}
