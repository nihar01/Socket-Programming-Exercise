import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Nihar_MT2020009_tcp_server_4a {

    public static String checkFunction(String IP1_string,String IP2_string,String SMASK_string)
    {
        String[] IP1_ARRAY=IP1_string.split("\\.");    //split the string using "." as delimiter
        String[] IP2_ARRAY=IP2_string.split("\\.");
        String[]  SMASK_ARRAY=SMASK_string.split("\\.");

        int [] resultOctets1 = new int[4];
        int [] resultOctets2 = new int[4];

        for(int i=0;i< 4;i++){

            int IP1_OCTET=Integer.parseInt(IP1_ARRAY[i]);      //convert the octet in string to int
            int IP2_OCTET=Integer.parseInt(IP2_ARRAY[i]);
            int  SMASK_OCTET=Integer.parseInt(SMASK_ARRAY[i]);

            if(IP1_OCTET>255 ||IP1_OCTET<0 ||IP2_OCTET>255||IP2_OCTET<0||SMASK_OCTET>255||SMASK_OCTET<0)
            {
                System.out.println("Invalid Input String ");
                continue;
            }
            resultOctets1[i]=(IP1_OCTET & SMASK_OCTET);    //perform bitwise AND operation
            resultOctets2[i]=(IP2_OCTET & SMASK_OCTET);    //between the IP and Subnet Mask to get the
                                                           // Subnet IP
        }
        int count=0;
        for(int i=0;i< 4;i++){
            if(resultOctets1[i]==resultOctets2[i])  //if all the octets of resultant subnet ip are same for both
                count++;                              // ip addresses then they belong to same network
        }
        if(count==4) {
            return "IP1 and IP2 belong to the SAME network";

        }
        else {
            return "IP1 and IP2 belong to DIFFERENT networks";

        }
    }

    public static void main(String args[]) throws Exception    //in case port no is busy, and exception is thrown
    {
        System.out.println("Server is started");
        ServerSocket ss=new ServerSocket(9995);  // initial socket created at server //port number as used on client side

        System.out.println("Server is waiting for client request");
        Socket s= ss.accept();   //new socket object is created when the server socket accept() the request from client
        String IP1_string="";
        String IP2_string="";
        String SMASK_string="";
        System.out.println("Client Connected");

        while(!IP1_string.equals("stop")) {

            System.out.println("Waiting for client data");
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //read the client data via the inputstream
            IP1_string = br.readLine();

            if(IP1_string.equals("stop")) {                                    //if "stop" is given as input,terminate the program.
                System.out.println("Connection terminated");
                s.close();
                break;
            }

          //  System.out.println("Waiting for client data");
           // BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //read the client data via the inputstream
            IP2_string = br.readLine();

          //  System.out.println("Waiting for client data");
         //   BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); //read the client data via the inputstream
            SMASK_string = br.readLine();


            System.out.println("IP1: " + IP1_string +", "+"IP2: " + IP2_string +", " +"SUBNET MASK: "+ SMASK_string);


            String RESULT = checkFunction(IP1_string,IP2_string,SMASK_string);      //function call check if IP's are in same network

            OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out = new PrintWriter(os);                      //write the RESULT string onto the socket connection to the client
            out.println(RESULT);
            out.flush();
            System.out.println("RESULT sent from server to Client\n");
        }
        if(IP1_string.equals("stop")) {                                    //if "stop" is given as input,terminate the program.
            System.out.println("Connection terminated");
        }
        }
    }

