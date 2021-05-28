/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import administration.Almacen;
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

/**
 *
 * @author Edward Vergel
 */
public class main {
    boolean onSim = false;

    Almacen almacen = new Almacen(); //VARIABLES VOLATILES
    Employee emp = new Employee(); //FUNCIONES PARA CONTRATAR Y DESPEDIR
   
    
    
    dataFunctions df = new dataFunctions();

    ArrayList<ButtonsProd> buttonsProdEmp = new ArrayList<ButtonsProd>(); //PRODUCTORES DE BOTONES MAXIMOS
    ArrayList<ArmsProd> armsProdEmp = new ArrayList<>(); //PRODUCTORES DE BRAZOS MAXIMOS
    ArrayList<LegsProd> legsProdEmp = new ArrayList<>(); //PRODUCTORES DE PIERNAS MAXIMOS
    ArrayList<BodyProd> bodyProdEmp = new ArrayList<>(); //PRODUCTORES DE CUERPO CENTRAL MAXIMOS



    Semaphore mutex = new Semaphore(1);
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
    
    //ENSAMBLADORES
    
    
     public void hireProdButton(javax.swing.JTextPane console1) {                                               
        try {
            if(buttonsProdEmp.size() < 4){ //LIMITE DE PRODUCTORES 
            ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutex, String.valueOf(buttonsProdEmp.size()));
            buttonProd.showProduced(console1);
            buttonsProdEmp.add(buttonProd);
                for (int i = 0; i < buttonsProdEmp.size(); i++) {
                    buttonsProdEmp.get(i).setButtonsPerDay(4 * buttonsProdEmp.size());
                }
            System.out.println("Se ha agregado con exito");
            }else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores de botones alcanzada");
            }
            
        } catch (Error e) {
            System.out.println(e);
        
       }

    }
     
     public void hireProdArms(javax.swing.JTextPane console2) {
         try{
             if(armsProdEmp.size() < 5) {
                 ArmsProd armsProd = emp.hireArmsProdEmloyee(semArmsProd, semArmsCons, mutex, String.valueOf(armsProdEmp.size()));
                 armsProd.showProduced(console2);
                 armsProdEmp.add(armsProd);
                 for(int i = 0; i< armsProdEmp.size(); i++) {
                 armsProdEmp.get(i).setArmsPerDay(1 * armsProdEmp.size());
             }
                 System.out.println("Se ha agregado un productor de brazos con exito.");
             } else {
                 JOptionPane.showMessageDialog(null, "Se ha alcanzado la cantidad de productores de brazos maxima.");
             }
         } catch (Error e) {
             System.out.println(e);
         }
     }
     
     public void hireProdLegs(javax.swing.JTextPane console3) {
         try{
             if(legsProdEmp.size() < 4) {
                 LegsProd legsProd = emp.hireLegsProdEmloyee(semLegsProd, semLegsCons, mutex, String.valueOf(legsProdEmp.size()));
                 legsProd.showProduced(console3);
                 legsProdEmp.add(legsProd);
                 for(int i = 0; i< legsProdEmp.size(); i++) {
                 legsProdEmp.get(i).setLegsPerDay(1 * legsProdEmp.size());
             }
                 System.out.println("Se ha agregado un productor de piernas con exito.");
             } else {
                 JOptionPane.showMessageDialog(null, "Se ha alcanzado la cantidad de productores de piernas maxima.");
             }
         } catch (Error e) {
             System.out.println(e);
         }
     }
     
     
     public void hireProdBody(javax.swing.JTextPane console4) {
         try{
             if(bodyProdEmp.size() < 4) {
                 BodyProd bodyProd = emp.hireBodyProdEmloyee(semBodyProd, semBodyCons, mutex, String.valueOf(bodyProdEmp.size()));
                 bodyProd.showProduced(console4);
                 bodyProdEmp.add(bodyProd);
                 for (int i = 0; i < bodyProdEmp.size(); i++) {
                    bodyProdEmp.get(i).setBodyPerDay((1* bodyProdEmp.size()));
                 }
                 System.out.println("Se ha agregado un productor de cuerpo central con exito.");
             } else {
                 JOptionPane.showMessageDialog(null, "Se ha alcanzado la cantidad maxima de productores de parte central.");
             }
         } catch (Error e) {
             System.out.println(e);
         }
     }
     
     

    public void initSimulation(javax.swing.JTextPane hours, javax.swing.JTextPane days) {                                               
            this.onSim = true;
            df.csvReader();
            this.createTime(hours, days);
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
                buttonsProdEmp.get(i).init();
        }
            for(int i = 0; i<armsProdEmp.size(); i++){
                armsProdEmp.get(i).start();
                armsProdEmp.get(i).init();
            }
            
            for(int i = 0; i < legsProdEmp.size(); i++){
                legsProdEmp.get(i).start();
                legsProdEmp.get(i).init();
            }
            for(int i = 0; i < bodyProdEmp.size(); i++){
                    bodyProdEmp.get(i).start();
                    bodyProdEmp.get(i).init();
                }    
            
    }                                              

    public void stopSimulation() {                                               
        System.out.println(this.onSim);
        if(this.onSim == true){
        
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).kill(); // SE DETIENEN TODOS LOS PRODUCTORES
            }
            this.onSim = false;
        }else {
            JOptionPane.showMessageDialog(null, "Inicialice simulacion", "ERROR", 0);
        }
    }
    
    public void createTime(javax.swing.JTextPane hours, javax.swing.JTextPane days){
        Time time = new Time(hours, days);
        time.start();
        time.init();
}
    
    
     
     
}
