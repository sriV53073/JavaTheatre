//Skv180001 Sri Vemugunta
// what we put into payload
public class Seat {
    //int variable
    public int row;
    // seat
    public char seat;
    // tick type
    public char tickType;
    // Seat Constructor
    Seat(int x, char y, char c)
    {
        // sets all the variables
        row = x;
        seat = y;
        tickType = c;
    }
    // Sets the Ticket
    public void changeTicketType(char c)
    {
        tickType = c;
    }
    // Gets tick
    public char getTicket()
    {
        return tickType;
    }
}
