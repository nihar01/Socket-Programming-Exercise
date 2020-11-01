import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Nihar_MT2020009_udp_server_4b {

    public static String sortString(String inputString)      //method to sort the string
    {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }


    public static void main(String args[]) throws Exception
{
    System.out.println("Starting Server");
    DatagramSocket ds=new DatagramSocket(9997);   //create a socket for carrying the data
    System.out.println("Server is now running");
    String input_string="";
   byte[] buf1=new byte[1024];
    InetAddress ip=InetAddress.getLocalHost();
while((!input_string.equals("stop"))) {

    DatagramPacket dp1 = new DatagramPacket(buf1, buf1.length); //receive input string from client
    ds.receive(dp1);
    System.out.println("Input string received from Client to server");
    input_string = new String(dp1.getData(), 0, dp1.getLength());

    System.out.println("Data from Client: " + input_string);

    if(input_string.equals("stop")) {                     //if "stop" is given as input,terminate the program.
        System.out.println("Connection terminated");
        break;
    }

    String sorted_string = sortString(input_string);
    System.out.println("Sorted string at server is:" + sorted_string);
    byte buf2[] = sorted_string.getBytes();                    //send the sorted string to the client

    DatagramPacket dp2 = new DatagramPacket(buf2, buf2.length, ip, dp1.getPort());
    ds.send(dp2);
    System.out.println("Sorted String sent from server to Client\n");
}
}
}
