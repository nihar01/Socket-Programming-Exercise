import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Nihar_MT2020009_tcp_client_4a {
    public static void main(String args[]) throws Exception {


        String ip_address = "127.0.0.1";  //loopback address
        int port_no = 9995;
        String IP1_string="";
       String IP2_string="";
       String SMASK_string="";

        System.out.println("Connecting to server");
        Socket s = new Socket(ip_address, port_no);
        //socket is connected and a connection request is sent to the server at given ip and port no
        System.out.println("Connected to server");

        while ((!IP1_string.equals("stop")) ) {

            Scanner sc = new Scanner(System.in);       //fetching input from user

            System.out.println("Enter IP1,IP2,SUBNET MASK in form A.B.C.D");

            System.out.println("Enter the 1st IP Address (Enter 'stop': to exit)");
            IP1_string=sc.nextLine();

            if(IP1_string.equals("stop")) {                                    //if "stop" is given as input,terminate the program.
                System.out.println("Connection terminated");
                s.close();
                break;
            }

            System.out.println("Enter the 2nd IP Address");
             IP2_string=sc.nextLine();

            System.out.println("Enter the Subnet Mask");
            SMASK_string=sc.nextLine();

            OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());   //writing the input_string onto the socket stream
            PrintWriter out = new PrintWriter(os);
            out.println(IP1_string);
            os.flush();

         //   OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());   //writing the input_string onto the socket stream
           // PrintWriter out = new PrintWriter(os);
            out.println(IP2_string);
            os.flush();

          //  OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());   //writing the input_string onto the socket stream
         //   PrintWriter out = new PrintWriter(os);
            out.println(SMASK_string);
            os.flush();



            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //read the server data via the inputstream
            String RESULT = br.readLine();

            System.out.println("RESULT FROM SERVER :" + RESULT+"\n");      //display the RESULT
        }

    }

}
