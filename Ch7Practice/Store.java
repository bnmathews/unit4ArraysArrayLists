import java.util.ArrayList;
import java.util.Scanner;

public class Store
{
    private ArrayList<String> customers = new ArrayList<String>();
    private ArrayList<Double> sales = new ArrayList<Double>();
    
    public void addSale(String customerName, double amount)
    {
        customers.add(customerName);
        sales.add(amount);
    }

    public String nameOfBestCustomer()
    {
        String bestCustomer = "";
        double lastSale = 0;
        double bestSale = 0;
        for (int i = 0; i < customers.size(); i++)
        {
            lastSale = sales.get(i);
            if (lastSale > bestSale)
            {
                bestSale = lastSale;
                bestCustomer = customers.get(i);
            }
        }
        
        return bestCustomer;
    }
    
    public void printStuff()
    {
        System.out.println(customers);
        System.out.println(sales);
    }
    
    public static void main(String[] args)
    {
        Store store = new Store();
        
        String name = "";
        double sale = 0;
        int salesMade = 0;
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("How many sales were made today? ");
        salesMade = s.nextInt();
        
        for (int i = 0; i < salesMade; i++)
        {
            System.out.print("Enter the customer's name: ");
            name = s.next();
            
            System.out.print("Enter the sale's amount: ");
            sale = s.nextDouble();
            
            store.addSale(name,sale);
        }
        
        //store.printStuff();
        System.out.println("Today's best customer is: " + store.nameOfBestCustomer());
    }
    
}
