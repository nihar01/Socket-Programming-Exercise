import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Nihar_MT2020009_udp_server_4a {
    public static String checkFunction(String IP1_string, String IP2_string, String SMASK_string) {

        String[] IP1_ARRAY = IP1_string.split("\\.");    //split the string using delimiter '.'
        String[] IP2_ARRAY = IP2_string.split("\\.");
        String[] SMASK_ARRAY = SMASK_string.split("\\.");

        int[] resultOctets1 = new int[4];
        int[] resultOctets2 = new int[4];

        for (int i = 0; i < 4; i++) {

            int IP1_OCTET = Integer.parseInt(IP1_ARRAY[i]);    //convert the string octet to int
            int IP2_OCTET = Integer.parseInt(IP2_ARRAY[i]);
            int SMASK_OCTET = Integer.parseInt(SMASK_ARRAY[i]);

            if (IP1_OCTET > 255 || IP1_OCTET < 0 || IP2_OCTET > 255 || IP2_OCTET < 0 || SMASK_OCTET > 255 || SMASK_OCTET < 0) {
                System.out.println("Invalid Input String ");
                continue;
            }
            resultOctets1[i] = (IP1_OCTET & SMASK_OCTET);  //perform bitwise AND operation
            resultOctets2[i] = (IP2_OCTET & SMASK_OCTET);  //between IP and Subnet Mask

        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (resultOctets1[i] == resultOctets2[i]) //if both the calculated subnet IP match
                count++;                               //both the IP's will be in same network
        }                                              //else in different network
        if (count == 4) {
            return "IP1 and IP2 belong to the SAME network";

        } else {
            return "IP1 and IP2 belong to DIFFERENT networks";

        }
    }


    public static void main(String args[]) throws Exception {
        System.out.println("Starting Server");
        DatagramSocket ds = new DatagramSocket(9994);   //create a socket for carrying the data
        System.out.println("Server is now running");

        String IP1_string = "";
        String IP2_string = "";
        String SMASK_string = "";

        byte[] buf11 = new byte[1024];
        byte[] buf12 = new byte[1024];
        byte[] buf13 = new byte[1024];

        InetAddress ip = InetAddress.getLocalHost();   //get ip address of localhost
        while ((!IP1_string.equals("stop"))) {


            DatagramPacket dp11 = new DatagramPacket(buf11, buf11.length);
            ds.receive(dp11);

            IP1_string = new String(dp11.getData(), 0, dp11.getLength());
            System.out.println("IP1 from Client: " + IP1_string);

            DatagramPacket dp12 = new DatagramPacket(buf12, buf12.length);
            ds.receive(dp12);
                                                           //receive the user input from the client
            IP2_string = new String(dp12.getData(), 0, dp12.getLength());
            System.out.println("IP2 from Client: " + IP2_string);

            DatagramPacket dp13 = new DatagramPacket(buf13, buf13.length);
            ds.receive(dp13);

            SMASK_string = new String(dp13.getData(), 0, dp13.getLength());
            System.out.println("SUBNET MASK from Client: " + SMASK_string);


            if (IP1_string.equals("stop")) {                   //if "stop" is given as input,terminate the program.
                System.out.println("Connection terminated");
                break;
            }

            String RESULT = checkFunction(IP1_string, IP2_string, SMASK_string); //function call to computer result
            System.out.println("RESULT is:" + RESULT);
            byte[] buf2 = RESULT.getBytes();

            DatagramPacket dp2 = new DatagramPacket(buf2, buf2.length, ip, dp11.getPort());
            ds.send(dp2);                                          //final result string is sent to the client
            System.out.println("RESULT sent from server to Client\n");
        }
    }
}
