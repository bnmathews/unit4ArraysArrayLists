import java.util.ArrayList;

public class ArrayListRunner
{
   public static void main(String[] args)
   {
       ArrayList<String> names = new ArrayList<String>();
       System.out.println(names);
       
       // add names to the array list
       
       
       
       names.add("Alice");
       names.add("Bob");
       names.add("Connie");
       names.add("David");
       names.add("Edward");
       names.add("Fran");
       names.add("Gomez");
       names.add("Harry");
       System.out.println(names);
       System.out.println();
       
       // get the first and last
       System.out.println(names.get(0) + " - " + names.get(names.size() -1));
       System.out.println();
       
       // print size
       System.out.println(names.size());
       System.out.println();
       
       // print last name
       names.get(names.size() -1);
       System.out.println();
       
       // change Alice
       names.set(0, "Alice B. Toklas" );
       System.out.println(names);
       System.out.println();
       
       // add Doug
       names.add(4,"Doug");
       System.out.println(names);
       System.out.println();
       
       // print all names, one per line
       for( String val : names )
       {
           System.out.println(val);
       }
       System.out.println();
       
       // make another arraylist based on the first one
       ArrayList<String> names2 = new ArrayList<String>(names);
       System.out.println(names2);
       System.out.println();
       
       // remove the first element from names ONLY
       names.remove(0);
       System.out.println(names);
       System.out.println(names2);
   }
} 