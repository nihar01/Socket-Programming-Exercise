import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Nihar_MT2020009_udp_client_4a {
    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        String IP1_string = "";
        String IP2_string = "";                            //create a socket for carrying the data
        String SMASK_string = "";
        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf11;
        byte[] buf12;
        byte[] buf13;

        while ((!IP1_string.equals("stop"))) {
            System.out.println("Enter IP1,IP2,SUBNET MASK in form A.B.C.D");
            Scanner sc = new Scanner(System.in);       //fetching input from user

            System.out.println("Enter the 1st IP Address");
            IP1_string = sc.nextLine();                //take 2 IP address and 1 subnet mask as input
            buf11 = IP1_string.getBytes();

            System.out.println("Enter the 2nd IP Address");
            IP2_string = sc.nextLine();
            buf12 = IP2_string.getBytes();

            System.out.println("Enter the Subnet Mask");
            SMASK_string = sc.nextLine();
            buf13 = SMASK_string.getBytes();


            DatagramPacket dp11 = new DatagramPacket(buf11, buf11.length, ip, 9994);
            ds.send(dp11);
            System.out.println("IP1 sent to the server");

            DatagramPacket dp12 = new DatagramPacket(buf12, buf12.length, ip, 9994);
            ds.send(dp12);
            System.out.println("IP2 sent to the server");

            DatagramPacket dp13 = new DatagramPacket(buf13, buf13.length, ip, 9994);
            ds.send(dp13);
            System.out.println("IP3 sent to the server");
            //send the user input to the server via datagram packets

            if (IP1_string.equals("stop")) {           //if "stop" is given as input,terminate the program.
                System.out.println("Connection terminated");
                break;
            }

            byte[] buf2 = new byte[1024];
            DatagramPacket dp2 = new DatagramPacket(buf2, buf2.length);
            ds.receive(dp2);                                         //RESULT is received from the server
            String RESULT = new String(dp2.getData(), 0, dp2.getLength());
            System.out.println("RESULT from server: " + RESULT+"\n");    //display result
        }
    }
}