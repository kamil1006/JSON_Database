package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;


import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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



    @Parameter(names={"--plik", "-in"})
    String nazwaPliku;



    static String s;
    //%%%%%%%%%%%%%%%%%%%%%%%%%
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    //%%%%%%%%%%%%%%%%%%%%%%%%%

    public static void main(String ... argv) {

        try {
            String current = new java.io.File( "." ).getCanonicalPath();
          //  System.out.println("Current dir:"+current);
            String currentDir = System.getProperty("user.dir");
         //   System.out.println("Current dir using System:" +currentDir);


        }
        catch (Exception e){

        }


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
            else if(main.nazwaPliku!=null){

                try{
                    String pathToFile = ""+main.nazwaPliku;
                     s= new String(Files.readAllBytes(Paths.get(pathToFile)));
                    //System.out.println(s);
                    StringWalidator sw = new StringWalidator();
                    boolean tak=false;
                    //tak = sw.czyPrawidlowy(msg);
                    tak = sw.czyPrawidlowyGSON(s);
                    if (tak) {
                        if(sw.polecenie!=null)
                        {
                            msg= sw.polecenie;
                            jsonWiadomosc.put("type", sw.polecenie);
                            if(sw.nr2!=(null)) {
                                msg = msg + " " + sw.nr2;
                                jsonWiadomosc.put("key", String.valueOf(sw.nr2));
                                if(sw.tekst!=(null)&& sw.tekst!="") {
                                    msg = msg + " " + sw.tekst;
                                    jsonWiadomosc.put("value", sw.tekst);
                                }
                            }


                        }



                    }



                }catch (Exception e){
                    System.out.println("brak pliku?");
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
class StringWalidator {

    //-------------------------
    String polecenie;
    int nr;
    String nr2;
    String tekst;
    boolean prawidlowy;
    int max;
    boolean nieLiczba=false;
    //-------------------------

    public StringWalidator() {
        polecenie = "";
        nr = 0;
        tekst = "";
        prawidlowy = false;
        nr2="";
    }
    //-------------------------
    public boolean czyPrawidlowyGSON(String json) {


        String tt = "";
        JsonElement element = JsonParser.parseString(json);
        JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
        //**********
        for (Map.Entry<String, JsonElement> entry: entries) {
            //System.out.print(entry.getKey());
            //System.out.println(" "+ entry.getValue().getAsString());

            switch (entry.getKey()){
                case "type":
                    polecenie=entry.getValue().getAsString();
                    break;
                case "key":

                    try {
                        nr = Integer.parseInt(entry.getValue().getAsString());
                    }
                    catch (Exception e){
                        nr=0;
                        // nieLiczba=true;
                    }
                    nr2=entry.getValue().getAsString();
                    break;
                case "value":
                    tekst=entry.getValue().getAsString();
                    break;

            }

        }
        //**********
        tt=polecenie+" "+nr2+" "+tekst;




        return czyPrawidlowy(tt);
    }

    //-------------------------
    public boolean czyPrawidlowy(String s) {
        StringTokenizer st = new StringTokenizer(s);
        //==================
        if (st.countTokens() == 1) {
            String[] tablica = s.split(" ");
            if (tablica[0].equals("exit")) {
                prawidlowy = true;
                polecenie="exit";
            }

        }
        //==================
        else if (st.countTokens() == 2) {
            String[] tablica = s.split(" ");
            if (tablica[0].equals("get") || tablica[0].equals("delete")) {
                try {

                    // int k = Integer.parseInt(tablica[1]);
                    max = 1000;

                    //if (k > 0 && k <= max)
                    //if (server.Main.baza.rozmiarMapy <= max)
                    {
                        prawidlowy = true;
                        if (tablica[0].equals("get") ) polecenie="get";
                        if (tablica[0].equals("delete") ) polecenie="delete";
                        //nr=k;
                        nr2=tablica[1];
                    }

                } catch (Exception e) {
                }

            }
        }
        //==================
        else if (st.countTokens() > 2) {
            String[] tablica = s.split(" ");
            if (tablica[0].equals("set")) {
                try {
                    //int k = Integer.parseInt(tablica[1]);
                    max = 1000;
                    //if (k > 0 && k <= max)
                    //if (server.Main.baza.rozmiarMapy <= max)
                    {
                        prawidlowy = true;
                        polecenie="set";
                        //nr=k;
                        nr2=tablica[1];
                        tekst = s.substring(3 + 1 + tablica[1].length() + 1);




                    }

                } catch (Exception e) {
                }

            }


        }
        //==================


        return prawidlowy;
    }
    //-------------------------


    public String getPolecenie() {
        return polecenie;
    }
}

//#################################################################################################
//#################################################################################################

//#################################################################################################
//#################################################################################################