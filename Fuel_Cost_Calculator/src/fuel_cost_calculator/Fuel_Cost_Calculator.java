
package fuel_cost_calculator;

//import statement
import java.util.Scanner;


public class Fuel_Cost_Calculator {


    public static void main(String[] args) {
    
     //create a scanner object
     Scanner scanner = new Scanner (System.in);
     
     //Prompt user for the total distance of the trip
        System.out.println("Please enter the total distance of the trip in kilometers");
        int km = scanner.nextInt();
     
     //Prompt user for the consumption
        System.out.println("Please enter the fuel efficiency of your car in km per liter");
        double kmL = scanner.nextDouble();
     
     //Prompt user for the price of fuel per litre
        System.out.println("Please enter the price of fuel per litre");
        double ZAR = scanner.nextDouble();
        
     //Calculating the total fuel needed
      final double L = km/kmL ;
      
     //Calculating the total cost of fuel
      final double R = L * ZAR;
              
     //Displaying the total amount of money the user will spend on fuel
        System.out.println("The total amount of money which you will have to spend on fuel is" + R);
        
        
        
      //close the scanner
      scanner.close();
     

    }
    
}
