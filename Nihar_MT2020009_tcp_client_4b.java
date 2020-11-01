import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Nihar_MT2020009_tcp_client_4b  {
    public static void main(String args[]) throws Exception {


        String ip_address = "127.0.0.1";  //loopback address
        int port_no = 9998;
String input_string="";
        System.out.println("Connecting to server");
        Socket s = new Socket(ip_address, port_no);
        //socket is connected and a connection request is sent to the server at given ip and port no
        System.out.println("Connected to server");

        while ((!input_string.equals("stop")) ) {


            System.out.println("Enter the String to sort (Enter 'stop': to exit)");
            Scanner scanner = new Scanner(System.in);       //fetching input from user
            input_string = scanner.nextLine();
            System.out.println(input_string);


            OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());   //writing the input_string onto the socket stream
            PrintWriter out = new PrintWriter(os);
            out.println(input_string);
            os.flush();

            if(input_string.equals("stop")) {                                    //if "stop" is given as input,terminate the program.
                System.out.println("Connection terminated");
                break;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //read the server data via the inputstream
            String sorted_string = br.readLine();

            System.out.println("Sorted string is :" + sorted_string);      //display the sorted string
        }

    }
}
