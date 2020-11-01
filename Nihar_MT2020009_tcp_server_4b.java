import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class Nihar_MT2020009_tcp_server_4b {

    public static String sortString(String inputString)      //method to sort the string
    {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public static void main(String args[]) throws Exception    //in case port no is busy, and exception is thrown
    {
        System.out.println("Server is started");
        ServerSocket ss=new ServerSocket(9998);  // initial socket created at server //port number as used on client side

        System.out.println("Server is waiting for client request");
        Socket s= ss.accept();   //new socket object is created when the server socket accept() the request from client
        String input_string="";
        System.out.println("Client Connected");

        while(!input_string.equals("stop")) {
             System.out.println("Waiting for client data");
             BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //read the client data via the inputstream
             input_string = br.readLine();

             System.out.println("Client Data : " + input_string);

            if(input_string.equals("stop")) {                                    //if "stop" is given as input,terminate the program.
                System.out.println("Connection terminated");
                break;
            }
             String sorted_string = sortString(input_string);      //function call to sort the string

             OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
             PrintWriter out = new PrintWriter(os);                      //write the sorted string onto the socket connection to the client
             out.println(sorted_string);
             out.flush();
             System.out.println("Sorted String sent from server to Client\n");
         }

    }
}
