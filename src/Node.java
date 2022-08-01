//Skv180001 Sri Vemugunta
// Generic class node
public class Node <G> {
    // these are the node the pointers
    Node<G> up;
    Node<G> down;
    Node<G> left;
    Node<G> right;
    //the generic payload
    G payload;
    // The Constrictor sets everything
    Node(G val)
    {
        payload = val;
        up = null;
        down = null;
        left = null;
        right = null;
    }


}
