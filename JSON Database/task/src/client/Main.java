package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;


import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;

class MojValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
    try {
        int indeks = Integer.parseInt(value);

    }catch (Exception e){

    }


    }
}


//#################################################################################################
//#################################################################################################
public class Main {


    //%%%%%%%%%%%%%%%%%%%%%%%%%
    @Parameter(names={"--type", "-t"})
    String type;
//    @Parameter(names={"--indeks", "-i"})
    @Parameter(names={"--key", "-k"})//, validateWith = MojValidator.class)
   // int indeks;
        String indeks;


//    @Parameter(names={"--wiadomosc", "-m"})
    @Parameter(names={"--value", "-v"})

    String wiadomosc;

    //%%%%%%%%%%%%%%%%%%%%%%%%%
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    //%%%%%%%%%%%%%%%%%%%%%%%%%

    public static void main(String ... argv) {

        Main main = new Main();
       try {
           JCommander.newBuilder()
                   .addObject(main)
                   .build()
                   .parse(argv);
       }catch (Exception e){       }



        Gson gson = new Gson();
        Map<String, String> jsonWiadomosc = new HashMap<>();

       // JSONObject jsonObject = new JSONObject();


        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output  = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");
           // Scanner scanner = new Scanner(System.in);
           // String msg = scanner.nextLine();
            //String msg= main.wiadomosc;//="12";
            String msg="";
            if(main.type!=null)
             {
                msg= main.type;
                jsonWiadomosc.put("type", main.type);

                if(main.indeks!=(null)){
                    msg=msg+" "+main.indeks;
                    jsonWiadomosc.put("key", String.valueOf(main.indeks));
                    if(main.wiadomosc!=(null)){
                        msg=msg+" "+main.wiadomosc;
                        jsonWiadomosc.put("value", main.wiadomosc);
                    }
                }
            }


           // String msg2= main.type+" "+main.indeks+" "+main.wiadomosc;

            //gson.toJson(jsonWiadomosc);

            //output.writeUTF(msg); // sending message to the server

            output.writeUTF(gson.toJson(jsonWiadomosc));


            //System.out.println("Sent: Give me a record # "+msg);
            //System.out.println("Sent: "+msg);
            System.out.println("Sent: "+gson.toJson(jsonWiadomosc));
            
            String receivedMsg = input.readUTF(); // response message

            //System.out.println("Received: A record # " + receivedMsg+ " was sent!");
            System.out.println("Received: " + receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%

    //%%%%%%%%%%%%%%%%%%%%%%%%%
}
//#################################################################################################
//#################################################################################################

//#################################################################################################
//#################################################################################################