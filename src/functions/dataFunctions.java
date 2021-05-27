
package functions;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dataFunctions {
    
    public void csvReader() {
     
            String path = "./test/data.csv";
            String line = "";
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            while((line = br.readLine()) != null) {
                
                String[] data = line.split(",");
            }
            
    } catch(FileNotFoundException e) {
        e.printStackTrace();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    
}
