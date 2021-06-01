
package functions;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dataFunctions {
    
    public String csvReader() {
        String content = "";
     
            String csvFile = "./test/data.csv";
            String line = " ";
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            
            while((line = br.readLine()) != null) {
                
                String[] data = line.split(",");
                content += line + "\n";
            }
    } catch(FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return content;
    }
}
