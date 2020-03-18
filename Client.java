import java.net.*; 
import java.io.*; 
  
public class Client 
{ 
    private Socket socket            = null; 
    private BufferedReader input = null;
    private DataOutputStream out     = null; 
    private DataInputStream in = null;
  
    public Client(String address, int port) 
    { 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            input = new BufferedReader(new InputStreamReader(System.in));
            out    = new DataOutputStream(socket.getOutputStream()); 
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
        try
        { 
            System.out.println(in.readUTF());
            out.writeUTF(input.readLine());
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("127.2.78.1", 5000); 
    } 
}