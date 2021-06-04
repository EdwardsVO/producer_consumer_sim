package administration;
import gui.main;
import functions.dataFunctions;

public class Almacen {
    
    main main;
    dataFunctions df = new dataFunctions();
    
    public String[] array = readData();
    
    public String[] readData() {
        String data = df.csvReader();
        this.array = data.split(",");
        return this.array; 
    }
    
    public static volatile int contButtons = 0;
    public static volatile int contLegs = 0;
    public static volatile int contArms = 0;
    public static volatile int contBody = 0;
    public static volatile int hoursPassed = 0;
    public static volatile int daysPassed = 0;
    public static volatile int dayEquiv =  4000; //Miliseconds
    public static volatile int daysLeft = 20;
    public static volatile int panasBuilt = 0;
    public static volatile int panasDistributed = 0;
    public static volatile int tandas = 0;
}
