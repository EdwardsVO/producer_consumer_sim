/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;

import gui.Interfaz;
import functions.dataFunctions;
import gui.main;
/**
 *
 * @author Edward Vergel
 */
public class Producer_consumer_sim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        main main = new main();
        Interfaz interfaz = new Interfaz();
//        main.readData();
//        System.out.println(Integer.parseInt(main.array[15]));
              
        main.main(interfaz);
        
        
    }
    
}
