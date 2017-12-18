import java.io.*;
import java.net.*;

class SocketGet 
{

    // main entry point for the application
    public static void main(String args[]) 
    {
        try 
        {
            // open socket
            URL httpUrl = new URL(args[0]);
            Socket httpSocket = new Socket(httpUrl.getHost(), httpUrl.getDefaultPort());

            // open up streams to write to and read from
            PrintWriter request = new PrintWriter(httpSocket.getOutputStream(), true);
            BufferedReader response = new BufferedReader(new InputStreamReader(httpSocket.getInputStream()));

            // write the HTTP "GET" request
            String httpHeader = "GET " + httpUrl + " HTTP/1.1\r\nAccept: */*\r\nAccept-Language: en-us\r\nHost: localhost\r\nConnection: close\r\n\r\n";
            System.out.println(httpHeader);
            request.println(httpHeader);

            // read and print the reply
            String responseStr = response.readLine();
            while ((responseStr != null) && (responseStr != ""))
            {   
                System.out.println(responseStr);
                responseStr = response.readLine();
            } 

            // close the socket
            httpSocket.close();
        } 
        catch (UnknownHostException e) 
        {
            System.err.println("Don't know about host: hostname");
        } 
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
    }
}
