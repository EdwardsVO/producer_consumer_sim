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

/**
 *
 * @author Edward Vergel
 */
public class main {
    boolean onSim = false;

    Almacen almacen = new Almacen(); //VARIABLES VOLATILES
    Employee emp = new Employee(); //FUNCIONES PARA CONTRATAR Y DESPEDIR
    Time time = new Time(); //TIEMPO EN SIMULACION
    
    dataFunctions df = new dataFunctions();

    ArrayList<ButtonsProd> buttonsProdEmp = new ArrayList<ButtonsProd>(); //PRODUCTORES DE BOTONES MAXIMOS

    Semaphore mutex = new Semaphore(1);
    //PROD - CONS ----> BOTONES
    Semaphore semButtonProd = new Semaphore(60); //TOTAL DE BOTONES
    Semaphore semButtonCons = new Semaphore(0); //CANTIDAD EN CONSUMO
    
    //PROD - CONS ----> BRAZOS
    
    //ENSAMBLADORES
    
    
     public void hireProdButton() {                                               
        try {
            if(buttonsProdEmp.size() < 4){ //LIMITE DE PRODUCTORES 
            ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutex, String.valueOf(buttonsProdEmp.size()));
            buttonsProdEmp.add(buttonProd);
                for (int i = 0; i < buttonsProdEmp.size(); i++) {
                    buttonsProdEmp.get(i).setButtonsPerDay(4 * buttonsProdEmp.size());
                }
            System.out.println("Se ha agregado con exito");
            }else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores alcanzada");
            }
            
        } catch (Error e) {
            System.out.println(e);
        
       }

    }                                              

    public void initSimulation() {                                               
            this.onSim = true;
            df.csvReader();
            time.start(); //INICIALIZA EL TIEMPO
            time.init();
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
                buttonsProdEmp.get(i).init();
            
        }
    }                                              

    public void stopSimulation() {                                               
        System.out.println(this.onSim);
        if(this.onSim == true){
            time.kill();
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).kill(); // SE DETIENEN TODOS LOS PRODUCTORES
            }
            this.onSim = false;
        }else {
            JOptionPane.showMessageDialog(null, "Inicialice simulacion", "ERROR", 0);
        }
    }               
}