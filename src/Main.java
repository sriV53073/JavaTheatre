//Skv180001 Sri Vemugunta
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        char[][] arr = new char[10][26];
        int count_of_row;
        int count_of_col = 0;
        int counter1 = 0;
        double middleOfAudiX;
        double middleOfAudiY;
        String place_h;
        System.out.println("What is FIle Name");
        Scanner scn = new Scanner(System.in);
        String fileName = scn.next();
        Scanner fopener = new Scanner(new File(fileName));
        while (fopener.hasNext()) {
            place_h = fopener.nextLine();
            for (int x = 0; x < place_h.length(); x++) {
                arr[counter1][x] = place_h.charAt(x);
                count_of_col++;
            }
            counter1 = counter1 + 1;

        }
        //these are all the variables for the user inputs  and also the counters for reports at the end
        count_of_row = counter1;
        count_of_col = count_of_col / count_of_row;
        // gets mid for odd and even numbers
        if(count_of_col %2 == 0)
        {
            middleOfAudiX = (count_of_col/2 + .5);
        }
        else
        {
            middleOfAudiX = (count_of_col/2.0 + .5);

        }
        if (count_of_row %2 == 0)
        {
            middleOfAudiY = (count_of_row/2 + .5);
        }
        else
        {
            middleOfAudiY = (count_of_row/2.0 + .5);
        }
        // sets auditorium
        Auditorium audi = new Auditorium(arr,count_of_col,count_of_row);
        // prints auditorium
        audi.printAudi();
        // loop for options
        boolean loop = true;
        // variables for counters and input
        int choice = 0;
        int row = 0;
        String col = "";
        int col_num = 0;
        int total_num_of_adult = 0;
        int total_num_of_child = 0;
        int total_num_of_senior = 0;
        int num_Cd = 0;
        int num_Ad = 0;
        int num_SE = 0;
        // while loop works
        while(loop)
        {
            // menu true
            boolean menu = true;
            // gets menu with try catch as input handling
            while(menu)
            {
                try
                {
                    System.out.println("1. Reserve Seats");
                    System.out.println("2. Exit");
                    choice = Integer.parseInt(scn.next());
                    System.out.println("Correct Choice");
                    menu = false;
                }
                catch (Exception e)
                {
                    System.out.println("Please renter");
                }
            }
            // if second prints audi, writes, table, and sends audi to file
            if (choice == 2)
            {
                loop = false;
                audi.printAudi();
                audi.write();
                audi.createTable(count_of_row,count_of_col);
            }
            // if 1
            if(choice == 1)
            {
                boolean rowBool = true;
                // try catch for row
                while (rowBool)
                {
                    try
                    {
                        System.out.println("Please Enter Row Number");// first is row
                        row = Integer.parseInt(scn.next());
                        row = row-1;
                        rowBool = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong Input try again");
                    }
                }
               boolean colBool = true;
                // try catch for col
                while (colBool)
                {
                    try
                    {
                        System.out.println("Please Enter Column Letter");//second we get column letter and turn it into an number
                        col = scn.next();
                        col_num = ((int) col.charAt(0)) - 64;
                        col_num=col_num-1;
                        colBool = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong Input try again");
                    }

                }
                boolean numOfAdultsBool = true;
                // try catch for num adults
                while (numOfAdultsBool)
                {
                    try
                    {
                        System.out.println("Please Enter Number of adult tickets");// next number of adult tickets
                        num_Ad = Integer.parseInt(scn.next());
                        numOfAdultsBool = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong Input try again");
                    }
                }
                boolean numOfChildBool = true;
                // try catch for num Child
                while (numOfChildBool)
                {
                    try
                    {
                        System.out.println("Please Enter Number of child tickets");// next number of child tickets
                        num_Cd = Integer.parseInt(scn.next());
                        numOfChildBool = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong Input try again");
                    }
                }
                boolean numOfSenBool = true;
                // try catch for num sen
                while (numOfSenBool)
                {
                    try
                    {
                        System.out.println("Please Enter Number of senior tickets");// next number of senior tickets
                        num_SE = Integer.parseInt(scn.next());
                        numOfSenBool = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong Input try again");
                    }
                }
                // total
                int holder = num_SE + num_Cd + num_Ad;// holder for total tickets
                boolean is_seats_avaliable = audi.SeatsAvaliable(row,col.charAt(0),num_Ad,num_Cd,num_SE,holder);
                // if works
                if(is_seats_avaliable)
                {
                    System.out.println("Succes");
                }
                // gives options
                else
                {
                    audi.BestAvaliable(num_Ad,num_Cd,num_SE,holder,middleOfAudiX,middleOfAudiY,scn);
                }
            }
        }



    }
}