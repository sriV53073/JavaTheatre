//Skv180001 Sri Vemugunta
import java.io.*;
import java.util.Scanner;
import java.text.*;
// auditorium class
public class Auditorium {
    // sets thsese variables in order to traverse and set the linked list
    Node<Seat> head;
    Node<Seat> tail;
    Node<Seat> MultipleHeads[];
    //constructor, gets array, col_num, and row_num
    public Auditorium(char arr[][],int col_num, int row_num )
    {
        // for loops traverses the 1st row array
        for(int x = 0; x< col_num; x++)
        {
            // if it is 0 first pointer with head
            if(x == 0)
            {
                //creates Seat
                Seat b = new Seat(0,(char)(x+65),arr[0][x]);
                //with Seat sets the Head node
                head = new Node(b);
                // also for first one sets tail as head
                tail = head;
            }
            // or does this
            else
            {
                // creates seat object
                Seat l = new Seat(0,(char)(x+65),arr[0][x]);
                // New Node with seat as payload
                Node<Seat> holder2 = new Node<Seat>(l);
                // tail to right is holder2
                tail.right = holder2;
                // sets the holder left as tail
                holder2.left = tail;
                // now puts tail as holder2
                tail = holder2;

            }

        }
        // after first row sets a head for the next line
        MultipleHeads = new Node[row_num+1];
        // traverses the array
        for(int y = 1; y<row_num;y++)
        {
            
            // if its the 1st one
            if(y==1)
            {
                // sets the seat object
                Seat s = new Seat(y,'A',arr[y][0]);
                // the head for 1 set as payload s
                MultipleHeads[y] = new Node<Seat>(s);
                // sets head down as that head
                head.down = MultipleHeads[y];
                // sets the multiple head up as head
                MultipleHeads[y].up = head;
                // traverses the array
                for(int x = 1; x< col_num; x++)
                {
                    // if its the 1st one sets next right as multiple head
                    if(x == 1)
                    {
                        // new b
                        Seat b = new Seat(y,(char)(x+65),arr[y][x]);
                        // node with payload b
                        Node<Seat> holder = new Node<Seat>(b);
                        // right holder
                        MultipleHeads[y].right = holder;
                        // left heads
                        holder.left = MultipleHeads[y];
                        tail = holder;
                    }
                    // if not
                    else
                    {
                        // creates seat
                        Seat l = new Seat(y,(char)(x+65),arr[y][x]);
                        // new node with seat as payload
                        Node<Seat> holder2 = new Node<Seat>(l);
                        // right, and left is set
                        tail.right = holder2;
                        holder2.left = tail;
                        // moves tail
                        tail = holder2;

                    }

                }
            }
            // if not 1
            else
            {
                // creates seat
                Seat a = new Seat(y,'A',arr[y][0]);
                // sets the multiple head
                MultipleHeads[y] = new Node<Seat>(a);
                // sets the down and up
                MultipleHeads[y-1].down = MultipleHeads[y];
                MultipleHeads[y].up = MultipleHeads[y-1];
                // for loop to traverse array
                for(int x = 1; x< col_num; x++)
                {
                    // if 1st one
                    if(x == 1)
                    {
                        // creates seat
                        Seat b = new Seat(y,(char)(x+65),arr[y][x]);
                        // creates node, with seat payload
                        Node<Seat> holder = new Node<Seat>(b);
                        // sets the multiple heads
                        MultipleHeads[y].right = holder;
                        // the pointers
                        holder.left = MultipleHeads[y];
                        // new tail
                        tail = holder;
                    }
                    // if not 1
                    else
                    {
                        // new seat sets pointers and moves tail
                        Seat l = new Seat(y,(char)(x+65),arr[y][x]);
                        Node<Seat> holder2 = new Node<Seat>(l);
                        tail.right = holder2;
                        holder2.left = tail;
                        tail = holder2;

                    }

                }
            }
        }
    }
    // prints Auditorium
    public void printAudi()
    {
        // pretty much creates node, and travereses the pointers to print the auditorium
        Node<Seat> hold;
        hold = this.head;
        while(hold != null)
        {
            System.out.print(hold.payload.tickType);
            hold = hold.right;
        }
        System.out.println();
        for(int x = 1; x< this.MultipleHeads.length;x++)
        {
            hold = this.MultipleHeads[x];
            while(hold != null)
            {
                System.out.print(hold.payload.tickType);
                hold = hold.right;
            }
            System.out.println();
        }
    }
    // checks seat avaliable and if avaliable books it, if not returns false
    public boolean SeatsAvaliable(int startingRow, char startingCol, int numOfAdult, int numOfChild, int numOfSenior, int total)
    {
        // temp for traversing node
        Node<Seat> temp;
        // if the row in question is the first row
        if(startingRow == 0)
        {
            // sets temp as head
            temp = head;
            // loop to check if it works
            boolean loop = true;
            //runs loop
            while(loop)
            {
                // runs try loop just incase it's null, so theyr'es none left
                try{
                    // checks where the seat in question is
                    if(temp.payload.seat == startingCol)
                    {
                        loop = false;
                    }
                    // moves it
                    else
                    {
                        temp = temp.right;
                    }
                }
                // catches, if does not exist that it returns false
                catch (Exception e)
                {
                    return false;
                }

            }
            // new temp with where the temp is
            Node<Seat> temp3 = temp;
            // for loop to check if it works to book
            for(int x = 0; x<total;x++)
            {
                // if not avlaible false
                if(temp3.payload.tickType != '.')
                {
                    return false;
                }
                // moves
                temp3 = temp3.right;
            }
            // so if your here that means it works, and here are the parts where you book the tickets
            for(int x = 0; x<numOfAdult;x++)
            {
                temp.payload.changeTicketType('A');
                temp.payload.tickType = 'A';
                temp = temp.right;
            }
            for(int x = 0; x<numOfChild;x++)
            {
                temp.payload.changeTicketType('C');
                temp.payload.tickType = 'C';
                temp = temp.right;
            }
            for(int x = 0; x<numOfSenior;x++)
            {
                temp.payload.changeTicketType('S');
                temp.payload.tickType = 'S';
                temp = temp.right;
            }
        }
        // if not 0 it runs the same thing pretty much with one of the heads in the multiple heads
        if(startingRow != 0)
        {
            System.out.println(startingRow);
            temp = this.MultipleHeads[startingRow];
            boolean loop = true;
            while(loop)
            {
                if(temp.payload.seat == startingCol)
                {
                    loop = false;
                }
                else
                {
                    temp = temp.right;
                }
            }
            Node<Seat> temp2 = temp;
            for(int x = 0; x<total;x++)
            {
                if(temp2.payload.tickType != '.')
                {
                    return false;
                }
                temp2 = temp2.right;
            }
            for(int x = 0; x<numOfAdult;x++)
            {
                temp.payload.changeTicketType('A');
                temp.payload.tickType = 'A';
                temp = temp.right;
            }
            for(int x = 0; x<numOfChild;x++)
            {
                temp.payload.changeTicketType('C');
                temp.payload.tickType = 'C';
                temp = temp.right;
            }
            for(int x = 0; x<numOfSenior;x++)
            {
                temp.payload.changeTicketType('S');
                temp.payload.tickType = 'S';
                temp = temp.right;
            }

        }

// returns true  that means it booked
        return true;
    }
    // writes to file in here
    public void write() throws FileNotFoundException {

        // sets the file output stream
        FileOutputStream fileStream = new FileOutputStream("A1.txt");
        // the writer is created
        PrintWriter sender = new PrintWriter(fileStream);
        // to traverse
        Node<Seat> hold;
        // sets
        hold = this.head;
        // prints
        while(hold != null)
        {
            sender.print(hold.payload.tickType);
            hold = hold.right;
        }
        sender.println();
        // prints with rest for for loop
        for(int x = 1; x< this.MultipleHeads.length;x++)
        {
            hold = this.MultipleHeads[x];
            while(hold != null)
            {
                sender.print(hold.payload.tickType);
                hold = hold.right;
            }
            if(x+1<this.MultipleHeads.length)
            {
                sender.println();
            }
        }

        sender.close();// close it

    }
    // here is the table printing at end
    public void createTable(int row, int col)
    {
        // counters for end
        int adCounter=0;
        int cdCounter=0;
        int seCounter=0;
        // to traverse
        Node<Seat> hold;
        hold = this.head;
        // traverses top row, and adds counters
        while(hold != null)
        {
           if(hold.payload.tickType == 'A')
           {
               adCounter++;
           }
           if(hold.payload.tickType == 'C')
            {
                cdCounter++;
            }
           if(hold.payload.tickType == 'S')
            {
                seCounter++;
            }
            hold = hold.right;
        }
        // traverses rest of auditorium, and adds counters
        for(int x = 1; x< this.MultipleHeads.length;x++)
        {
            hold = this.MultipleHeads[x];
            while(hold != null)
            {
                if(hold.payload.tickType == 'A')
                {
                    adCounter++;
                }
                if(hold.payload.tickType == 'C')
                {
                    cdCounter++;
                }
                if(hold.payload.tickType == 'S')
                {
                    seCounter++;
                }
                hold = hold.right;
            }
        }
        // creates price
        double tot_price = (adCounter * 10) + (cdCounter * 5) + (seCounter * 7.5);
        // and this is the report printing out
        String price = String.format("%.2f", tot_price);
        System.out.println("Total Seats:    " + ((row) * (col)));
        System.out.println("Total Tickets:  " + (adCounter + cdCounter + seCounter));
        System.out.println("Adult Tickets:  " + adCounter);
        System.out.println("Child Tickets:  " + cdCounter);
        System.out.println("Senior Tickets: " + seCounter);
        System.out.println("Total Sales:	$" + price);
    }
    // Best Avaliable, and prints if Y is created
    public void BestAvaliable(int numOfAdult, int numOfChild, int numOfSenior, int total,double MidAX, double MidAY,Scanner scnr)
    {
        // smallest distance var
        double smallestDistance = 200.0;
        // for cur distance
        double currentDistance;
        // sets row num, col for when smalles distance
        int RowNum = 0;
        char ColLet = 'Z';
        // to traverse
        Node<Seat> hold;
        hold = this.head;
        // letterer for what letter
        int letterer = 0;
        // while not null, traverses
        while(hold != null)
        {
            // sets char
            char x = ((char) ((letterer+ 65)));
            // gets cur distance if work
            currentDistance = this.DistanceCalcultor(0,x,numOfAdult,numOfChild,numOfSenior,total,MidAX,MidAY);
            // changes smallest distance
            if(currentDistance < smallestDistance)
            {
                // sets it
                smallestDistance = currentDistance;
                RowNum = 0;
                ColLet = x;
            }
            //moves everything
            hold = hold.right;
            letterer++;
        }
        // reset
        letterer = 0;
        // does this for multuple heads
        for(int x = 1; x< this.MultipleHeads.length;x++)
        {
            // traverses
            hold = this.MultipleHeads[x];
            while(hold != null)
            {
                // sets let
                char y = ((char) ((letterer+ 65)));
                // sets current Distance
                currentDistance = this.DistanceCalcultor(x,y,numOfAdult,numOfChild,numOfSenior,total,MidAX,MidAY);
               //formats the decimal for similar distances
                DecimalFormat df = new DecimalFormat("#.####");
                currentDistance = Double.parseDouble(df.format(currentDistance));
                // if less
                if(currentDistance < smallestDistance)
                {
                    smallestDistance = currentDistance;
                    RowNum = x;
                    ColLet = y;
                }
                // if equal
                else if(currentDistance == smallestDistance)
                {
                   // if less
                    if(Math.abs(MidAY -(x+1)) < Math.abs(MidAY-(RowNum+1) ))
                    {
                        RowNum = x;
                        ColLet = y;
                    }
                    // if equal
                    else if(Math.abs(MidAY -x+1) == Math.abs(MidAY-RowNum+1 ))
                    {
                       // if less
                        if(x+1 < RowNum+1)
                        {
                            RowNum = x;
                            ColLet = y;
                        }
                        // else leave same
                        else
                        {
                            RowNum = RowNum;
                            ColLet = ColLet;
                        }
                    }
                }
                // moves
                hold = hold.right;
                letterer++;
            }
            // reset
            letterer = 0;
        }
       // if exists
        if(smallestDistance!=200)
        {
            // vars for user input
            String r;
            char b;
            // gets col int
            int ColInt = ((int) ColLet) - 64;
            char newChar = (char) (ColInt + (63 + total));
            System.out.println("These Seats Avaliable");
            // gives best avliable
            if(total >1)
            {
                System.out.print(++RowNum);
                System.out.println((ColLet) + " - " + (RowNum) + newChar );
                System.out.println("Would You Like To Book The seats");
            }
            else
            {
                System.out.print(++RowNum);
                System.out.println((ColLet));
            }
            // gets y/n
            System.out.println("Y/N");
            r = scnr.next();
            b = r.charAt(0);
            // if y sets it
            if(b == 'Y')
            {
                boolean h = this.SeatsAvaliable(--RowNum,ColLet,numOfAdult,numOfChild,numOfSenior,total);
            }
            else
            {
                System.out.println("Ok");
            }

        }

    }
    // gets distance
    public double DistanceCalcultor(int startingRow, char startingCol, int numOfAdult, int numOfChild, int numOfSenior, int total, double midAX, double midAY)
    {
        // to traverse
        Node<Seat> temp;
        Node<Seat> temp3;
        // if first row
        if(startingRow == 0)
        {
            // sets as head
            temp = head;
            // loops to where
            boolean loop = true;
            while(loop)
            {
                if(temp.payload.seat == startingCol)
                {
                    loop = false;
                }
                else
                {
                    temp = temp.right;
                }
            }
            // stes as point
           try{
               temp3 = temp;
           }
           // if null, returns 200
           catch (Exception e)
           {
               return 200;
           }
           // traverse
            for(int x = 0; x<total;x++)
            {
                //if illegal
                try{
                    if(temp3.payload.tickType != '.')
                    {
                        return 200;
                    }
                    temp3 = temp3.right;
                }
                // catches and returns if null
                catch (Exception e)
                {
                    return 200;
                }
            }
            // checks if legal
            for(int x = 0; x<numOfAdult;x++)
            {
                temp = temp.right;
            }
            for(int x = 0; x<numOfChild;x++)
            {
                temp = temp.right;
            }
            for(int x = 0; x<numOfSenior;x++)
            {
                temp = temp.right;
            }
        }
        // same thing as above but for below 1
        if(startingRow != 0)
        {
            temp = this.MultipleHeads[startingRow];
            boolean loop = true;
            while(loop)
            {
                try{
                    if(temp.payload.seat == startingCol)
                    {
                        loop = false;
                    }
                    else
                    {
                        temp = temp.right;
                    }
                }
                catch (Exception e)
                {
                    return 200;
                }

            }
            Node<Seat> temp2 = temp;
            for(int x = 0; x<total;x++)
            {
                try{
                    if(temp2.payload.tickType != '.')
                    {
                        return 200;
                    }
                    temp2 = temp2.right;
                }
                catch (Exception e)
                {
                    return 200;
                }

            }
            for(int x = 0; x<numOfAdult;x++)
            {
                temp = temp.right;
            }
            for(int x = 0; x<numOfChild;x++)
            {
                temp = temp.right;
            }
            for(int x = 0; x<numOfSenior;x++)
            {
                temp = temp.right;
            }

        }
        // all the math for middle, of seat
        int charNum = (int)(startingCol) - 64;
        double middle = charNum + (total - 1)*.5;
       // math for distance
        double xPow = Math.pow(midAX - middle,2);
        startingRow = startingRow +1;
        double yPow = Math.pow(midAY-(startingRow),2);
       double distance =  Math.sqrt(xPow + yPow);
       // returns distance if get here
        return distance;

    }



}
