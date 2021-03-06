import java.util.ArrayList;
import java.util.Scanner;

public class StoreRound2
{
    private ArrayList<String> customers = new ArrayList<String>();
    
    private ArrayList<Double> sales = new ArrayList<Double>();
    
    public void addSale(String customerName, double amount)
    {
        customers.add(customerName);
        sales.add(amount);
    }

    public ArrayList<String> nameOfBestCustomer(int topN)
    {
        String bestCustomer = "";
        
        ArrayList<String> trueBest = new ArrayList<String>();
        double lastSale = 0;
        double bestSale = 0;
        
        int remove = 0;
        
        //int top = topN;
        if (customers.size() > topN)
        {
            for (int i = 0; i < topN; i++)
            {
                for (int j = 0; j < customers.size(); j++)
                {
                    lastSale = sales.get(j);
                    if (lastSale > bestSale)
                    {
                        bestSale = lastSale;
                        bestCustomer = customers.get(j);
                        remove = sales.indexOf(bestSale);
                    }
                }
                customers.remove(remove);
                sales.remove(remove);
                trueBest.add(bestCustomer);   
                bestSale = 0;
            }
        }
        else
        {
            trueBest = customers;
        }
        
        return trueBest;
    }
    
    public void printStuff()
    {
        System.out.println(customers);
        System.out.println(sales);
    }
    
    public static void main(String[] args)
    {
        StoreRound2 store = new StoreRound2();
        
        String name = "";
        double sale = 0;
        int salesMade = 0;
        int topN = 0;
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("How many sales were made today? ");
        salesMade = s.nextInt();
        
        System.out.print("How many top customers do you want to display? ");
        topN = s.nextInt();
        
        for (int i = 0; i < salesMade; i++)
        {
            System.out.print("Enter the customer's name: ");
            name = s.next();
            
            System.out.print("Enter the sale's amount: ");
            sale = s.nextDouble();
            
            store.addSale(name,sale);
        }
        
        ArrayList<String> bestCust = store.nameOfBestCustomer(topN);
        
        System.out.print("\nToday's best customers are: ");
        for (int i = bestCust.size() - 1; i >= 0; i--)
        {
           if (i > 0)
           {
               System.out.print(bestCust.get(i) + ", ");
           }
           else
           {
               System.out.print(bestCust.get(i) + " ");
           }
        }
    }
    
}
