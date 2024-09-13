package ui;

import java.util.*;

public class BurgerTown {
    public static Scanner in=new Scanner(System.in);

    public static ArrayList<String> nameDish = new ArrayList<>();
    public static ArrayList<Double> price = new ArrayList<>();
    public static ArrayList<Integer> unitSold = new ArrayList<>();
    //Static variables to be accessed from anywhere within the code.

    public static void main(String []args){
        System.out.println("Welcome to BurgerTown!");
        int select=0;
        while(select!=6){
            System.out.println("\nMain Menu:");
            System.out.println("1. Request prices ($) and quantities of each dish sold during the day");
            System.out.println("2. Calculate the total number of dishes sold during the day");
            System.out.println("3. Calculate the average price of the dishes sold during the day");
            System.out.println("4. Calculate the total sales (money collected) during the day");
            System.out.println("5. Calculate how many dishes beat a specified threshold of sales.");
            System.out.println("6. Exit");
            System.out.println("\nEnter your choice");
            select=in.nextInt();
            in.nextLine(); //Clears buffer

            switch (select){ //Analizes user input and sends to corresponding method
                case 1:
                    System.out.println(defineData());
                    break;
                case 2:
                    System.out.println("\nThe total amount of dishes sold during the day was: "+totalDishes());
                    break;
                case 3:
                    System.out.println("\nThe average price of plates sold during the day was: "+avgPriceDay());
                    break;
                case 4:
                    System.out.println("\nTotal sales today were: "+totalSales());
                    break;
                case 5:
                    System.out.println("\nPlease input the minimum threshold ($) to analize:");
                    double limit = in.nextDouble();
                    in.nextLine(); //Clears buffer
                    System.out.println("\nOut of "+price.size()+" refrences sold during the day, "+dishOverLimit(limit)+" beat the minimum threshold of "+limit);
                    break;
                case 6:
                    System.out.println("\nThank you for visitng BurgerTown");
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nSorry, that option isn't available, please try again.");
                    break;

            }
        }
    }
    /**
     * Description : This method takes a user input and compares if each product sold more than the requested amount. It says the amount of products to beat that threshold.
     * @param double limit : A price sent by the user.
     * @return int : A sum that represents the amount of products that surpassed the sales point.
     */
    public static int dishOverLimit(double limit){
        double totalPerDish=0;
        int result=0;
        for (int p=0; p<price.size(); p++){
            totalPerDish=price.get(p)*unitSold.get(p);
            
            if(totalPerDish>=limit){ //If the total money per dish exceed the amount, one is added to the counter then cycle is repeated., if not, cycle is repeated
                result++;
            }
        }
        return result;

    }
    /**
     * Description: Method will grab each item and multiply amount of units sold per individual price. It will do this for each item.
     * @return double total : After multiplying the corresponding values, they will be added to a total that adds everything.
     */
    public static double totalSales(){

        double total=0;
        for(int q=0; q<unitSold.size(); q++){
            total+=price.get(q)*unitSold.get(q); //Gets respective values of price and units sold for each item stored
    
        }
        return total;
    }
    /**
     * Description: Method that has a loop that sums each value along the length of the ArrayList and averages it out
     * @return double avg : Average that is calculated via the method
     */
    public static double avgPriceDay(){
        int counter=0;
        double sum=0;

        for (int k=0; k<price.size(); k++){
            sum+=price.get(k);
            counter++; //Tracks number of things added to then divide by
        }
        double avg=0;
        if(counter==0){
            avg=0; //in case all prices are 0, this prevents a division by 0
        } else{
            avg=sum/counter;
        }
        return avg;
    }
    /**
     * Description: Method that will add all total number of dishes sold
     * @return int total : Returns the added sum of units sold for all dishes in the restaurant.
     */
    public static int totalDishes(){
        int total=0;
        for(int j=0; j<unitSold.size(); j++){
            total+=unitSold.get(j); //Adds total of units sold for all dishes
        }
        return total;
    }
    /**
     * Description: This method is intended to set amount of dises, prices, and units sold of each item
     * @return string definition : A confirmation message is returned to the user
     */
    public static String defineData(){
        System.out.println("\nPlease input the amount of dishes you want to register:");
        int counter=in.nextInt();
        in.nextLine(); //Clear buffer

        for (int i=0; i<counter; i++){
            System.out.println("\nPlease enter the name of your dish #"+(i+1)+":");
            String dish = in.nextLine();

            System.out.println("\nPlease enter the price of "+dish+".");
            double value = in.nextDouble();
            while (value<=0){ //While that is cycled in case a negative or 0 is placed
                System.out.println("Sorry, that price is invalid, please try again:");
                value=in.nextDouble();
            }
            in.nextLine();//Clear buffer

            System.out.println("\nPlease enter the amount of "+dish+" sold.");
            int units = in.nextInt();
            while (units<0){ //While that is cycled in case that a negative number is placed
                System.out.println("Sorry, that amount is invalid, please try again:");
                units=in.nextInt();
            }
            in.nextLine(); //Clear buffer

            nameDish.add(dish);
            price.add(value);
            unitSold.add(units);
            //Saves the information in their respective Arrays
        }
        return "\nDishes successfully saved.";
    }

}