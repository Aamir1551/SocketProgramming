import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*; 
  
class voteHandler extends Thread {

    private Socket s = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    static volatile AtomicInteger blue;
    static volatile AtomicInteger green;

    public voteHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String v = null;;
        try {
            this.dos.writeUTF("Vote: ");
            v = this.dis.readUTF();
            System.out.println(v);
            this.dos.close();
            this.dis.close();
            this.s.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        if(blue == null) {
            blue = new AtomicInteger(0);
        }
        if(green==null) {
            green = new AtomicInteger(0);
        }
        int b=0;
        int g = 0;
        if(v.equals("blue")) {
            b = blue.incrementAndGet();
            g = green.get();
        } else if(v.equals("green")) {
            g = green.incrementAndGet();
            b = blue.get();
        }
        System.out.println( Integer.toString(b) + Integer.toString(g));
    }
}

public class Server { 
    public static void main(String args[]) 
    { 
        try {
            Socket ss = null;
            ServerSocket server = new ServerSocket(5000);
            while(true){
                
                ss = server.accept();
                DataInputStream in = new DataInputStream(new BufferedInputStream(ss.getInputStream()));
                DataOutputStream out = new DataOutputStream(ss.getOutputStream());

                Thread t = new voteHandler(ss, in, out);
                t.start();
            } 
        }
        catch (Exception e) {
        }
    }
} 
 