package carfind;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author SURAJIT
 */
public class Carfind {

    private static List<String> carlist= new ArrayList<String>(); 
    private final static String decimalFormat = "([0-9]*)\\.([0-9]*)"; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        carlist = carList("cars_input1.txt");   //List of all the car contains in the .txt file     
        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide the values of N and O: ");
        int noofcar = scan.nextInt();
        String origin = String.valueOf(scan.next());
        double avgpower = avgHorsepower(origin);    //Getting avegare horepower for a selected origin of car.    
        //System.out.println(avgpower);     //Print the average horspower for a particular origin of car.        
        result(noofcar,origin, avgpower);   //Print the desired result.
    }
    private static List<String> carList(String filename){   //This method read the car_input1.txt file and obtain each car details in a List. Parameter @filename => The full path of the soure txt file.
        List<String> carList = Collections.emptyList();
        try{ 
            carList = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            //carlist.remove(0);
        }catch (IOException e){ 
            System.out.println(e.toString()); 
        }                
        return carList;
    }
    private static HashMap<String, List<String>> originWiseGroup(){     //This method group the car origin wise
        HashMap<String, List<String>> carlistgroup = new HashMap<>();
        Iterator iterator = carlist.iterator();
        while (iterator.hasNext()){
            String element = String.valueOf(iterator.next());
            String origin = element.split(",").length>1?element.split(",")[1]:"others";
            //System.out.println(origin);
            if(!carlistgroup.keySet().contains(origin)){
                List<String> maplist = new ArrayList<>();
                maplist.add(element);
                carlistgroup.put(origin, maplist);
            }
            else{
                carlistgroup.get(origin).add(element);
            }
        }
        return carlistgroup;
    }
    private static List<String> getSelectedCarOrigin(String origin){    //This method return the carlist of the given origin. parameter @origin => Origin of the car.
        return originWiseGroup().get(origin);
    }
    private static double avgHorsepower(String origin){     //This method return the avegare horsepower for a particular origin. parameter @origin => Origin of the car.
        double avgpower=0.0, totalpower=0.0;
            List<String> selectedOriginCar = getSelectedCarOrigin(origin);
            Iterator carItr = selectedOriginCar.iterator();
            while(carItr.hasNext()){
                String element = String.valueOf(carItr.next());
                double horsepower = element.split(",").length>2?Pattern.matches(decimalFormat, element.split(",")[2])?Double.parseDouble(element.split(",")[2]):0.0:0.0; 
                totalpower +=horsepower;
            }
            avgpower = totalpower/selectedOriginCar.size();
        return avgpower;
    }
    
    public static void result(int numberofcar, String origin, double avgpower){     //This method display the desired car list. Parameter @numberofcar => Number of car want to display, @origin => Origin of the car, @avgpower => Average horsepower of the cars for the given origin.
        int counter = 0;
        List<String> selectedOriginCar = getSelectedCarOrigin(origin);
        Iterator carItr = selectedOriginCar.iterator();
        while(carItr.hasNext()){
            String element = String.valueOf(carItr.next());   
            double horsepower = element.split(",").length>2?Pattern.matches(decimalFormat, element.split(",")[2])?Double.parseDouble(element.split(",")[2]):0.0:0.0;     
            if(horsepower>avgpower){
                counter++;
                System.out.println(element);
            }
            if(counter==numberofcar)
                break;
        }
    }
    
}
