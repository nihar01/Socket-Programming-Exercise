import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Nihar_MT2020009_udp_client_4b {

    public static void main(String args[]) throws Exception
    {
        DatagramSocket ds=new DatagramSocket();  //create a socket for carrying the data
        String input_string="";
        String sorted_string="";
        InetAddress ip=InetAddress.getLocalHost();
        byte buf1[];

   while((!input_string.equals("stop")))
   {
    System.out.println("Enter the String to sort (Enter 'stop': to exit)");
    Scanner scanner = new Scanner(System.in);       //fetching input from user
    input_string = scanner.nextLine();
    buf1 = input_string.getBytes();

    DatagramPacket dp1 = new DatagramPacket(buf1, buf1.length, ip, 9997);
    ds.send(dp1);                    //send the input_string via datagram packet to server in udp
    System.out.println("Input string sent to the server");

       if(input_string.equals("stop")) {             //if "stop" is given as input,terminate the program.
           System.out.println("Connection terminated");
           break;
       }

    byte buf2[] = new byte[1024];
    DatagramPacket dp2 = new DatagramPacket(buf2, buf2.length);
    ds.receive(dp2);                          //receive the sorted string from server via datagram packet
    System.out.println("Sorted string received from server to Client");
    sorted_string = new String(dp2.getData(), 0, dp2.getLength());
    System.out.println("The sorted string is :" + sorted_string);
  }
    }
}
