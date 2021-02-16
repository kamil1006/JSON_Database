package client;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

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
       // Map<String, String> jsonWiadomosc = new HashMap<>();
       // Map<String, JsonElement> jsonWiadomosc2 = new HashMap<>();
        JsonObject jsonWiadomosc = new JsonObject();
        JsonObject jsonWiadomosc2 = new JsonObject();


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
                //jsonWiadomosc.put("type", main.type);
                 jsonWiadomosc.addProperty("type", main.type);

                if(main.indeks!=(null)){
                    msg=msg+" "+main.indeks;
                    //jsonWiadomosc.put("key", String.valueOf(main.indeks));
                    jsonWiadomosc.addProperty("key", String.valueOf(main.indeks));

                    if(main.wiadomosc!=(null)){
                        msg=msg+" "+main.wiadomosc;
                        //jsonWiadomosc.put("value", main.wiadomosc);
                        jsonWiadomosc.addProperty("value", main.wiadomosc);

                    }
                }

                 output.writeUTF(gson.toJson(jsonWiadomosc));
                // System.out.println("Sent: "+msg);
                 //System.out.println("--1--");
                  System.out.println(gson.toJson(jsonWiadomosc));

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
                            //jsonWiadomosc.put("type", sw.polecenie);
                            jsonWiadomosc.addProperty("type", sw.polecenie);



                            if(sw.nr2!=(null)) {
                                msg = msg + " " + sw.nr2;
                               // jsonWiadomosc.put("key", String.valueOf(sw.nr2));
                                jsonWiadomosc.addProperty("key", String.valueOf((sw.nr2)).replaceAll("\\\"",""));
                               // jsonWiadomosc.addProperty("key", String.valueOf((sw.nr2)));

                                // if(sw.tekst!=(null)&& sw.tekst!="")
                                    if(sw.tekst!=(null))
                                    {
                                    msg = msg + " " + sw.tekst;
                                    //jsonWiadomosc2.put("value", sw.tekst);

                                        String tek=String.valueOf(sw.tekst);
                                        tek=tek.replace("\\", "\"");
                                        //System.out.println(tek);
                                        jsonWiadomosc.addProperty("value", tek);
                                }
                            }


                        }



                    }



                }catch (Exception e){
                    System.out.println("brak pliku?");
                }





                JsonElement element = JsonParser.parseString(s);
                JsonObject obj = element.getAsJsonObject(); //
                output.writeUTF(String.valueOf(obj));
                System.out.println("Sent: "+ String.valueOf(obj));
            }



            String receivedMsg = input.readUTF(); // response message


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
    //String nr2;
    //Gson nr2;
    JsonElement nr2;
    //JsonObject nr2;
    //String tekst;
    JsonElement tekst;
    boolean prawidlowy;
    int max;
    boolean nieLiczba=false;
    //-------------------------

    public StringWalidator() {
        polecenie = "";
        nr = 0;
       // tekst = "";
        tekst=null;
        prawidlowy = false;
        //nr2="";//new Gson();
        nr2=null;
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
                    //nr2=entry.getValue().getAsString();
                    nr2=entry.getValue();//.getAsString();
                   // nr2=entry.getValue().getAsJsonObject();
                   // nr2.toJson(entry.getValue());
                    break;
                case "value":
                    //tekst=entry.getValue().getAsString();
                    tekst=entry.getValue();
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
                        //nr2=tablica[1];
                        nr2=JsonParser.parseString(tablica[1]);
                        //nr2= (JsonObject) JsonParser.parseString(tablica[1]);
                       // nr2.toJson(tablica[1]);

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
                        //nr2=tablica[1];
                        nr2=JsonParser.parseString(tablica[1]);
                        //nr2= (JsonObject) JsonParser.parseString(tablica[1]);
                       // nr2.toJson(tablica[1]);
                        //tekst = s.substring(3 + 1 + tablica[1].length() + 1);




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