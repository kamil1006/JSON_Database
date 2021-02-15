package server;

import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


//#################################################################################################
//#################################################################################################
public class Main {

    private static final int PORT = 34522;

     static boolean czyKoniec=false;

        static JsonWalidator jwMain;

    static int rozmiar = 1000;

    static  WpisyOperacje baza = new WpisyOperacje(rozmiar);



    //%%%%%%%%%%%%%%%%%%%%%%%%%
    public enum polecenia {
        set, get, delete, exit, ERROR, OK
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%

    public static void main(String[] args)
    {

        Main.polecenia cmd = null;

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started!");
           // while (true)
            while (!czyKoniec)
            {

                Session session = new Session(server.accept(),czyKoniec);
                session.start(); // it does not block this thread
                try {
                    session.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                czyKoniec=session.isCzyKoniec();
                //System.out.println(czyKoniec);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Koniec pracy.");




    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%

    //%%%%%%%%%%%%%%%%%%%%%%%%%
//#################################################################################################
//#################################################################################################
    protected static class JsonWalidator {

        JsonElement obiektJson;
        boolean prawidlowyJson;

        String polecenie;
        String klucz;
        Map.Entry<String, JsonElement> kluczWartosc;
        Map.Entry<String, JsonElement> valueWartosc;

        boolean czyPolecenie=false;
        boolean czyGet=false;
        boolean czySet=false;
        boolean czyDelete=false;
        boolean czyExit=false;


        boolean czyKlucz=false;
        boolean czyValue=false;

        public JsonWalidator() {
            obiektJson=null;
            prawidlowyJson=false;
            polecenie="";
            kluczWartosc=null;
            valueWartosc=null;
        }
        //-----------------------------------------------------------------------------
        public boolean czyPrawidlowyGSON(String json) {

            try {
                JsonElement element = JsonParser.parseString(json);
                JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
                Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return membe
                prawidlowyJson=true;
                obiektJson=element;

                for (Map.Entry<String, JsonElement> entry: entries) {
                    switch (entry.getKey()){
                        case "type":
                            polecenie=entry.getValue().getAsString();
                            czyPolecenie=true;
                            switch (polecenie){
                                case "exit":
                                    czyExit=true;
                                    break;

                                    case "set":
                                        czySet=true;
                                        break;
                                case "get":
                                    czyGet=true;
                                    break;
                                case "delete":
                                    czyDelete=true;
                                    break;
                            }



                            break;
                        case "key":
                            czyKlucz=true;
                           kluczWartosc=entry;
                           klucz=entry.getKey();

                            break;

                        case "value":
                            czyValue=true;
                            valueWartosc=entry;

                            break;


                    }




                }



            }
           catch (Exception e){
                prawidlowyJson=false;
           }

           return prawidlowyJson;

        }
        //-----------------------------------------------------------------------------




        //-----------------------------------------------------------------------------


    }
        //#################################################################################################
//#################################################################################################


    //#################################################################################################
//#################################################################################################
    static class WpisyOperacje {

        //-------------------------




        private String nazwaPliku3;

        private JsonElement elementy;

        Map<String, Object> zbior = new HashMap<String, Object>();


        //-------------------------
        public WpisyOperacje(int n) {
          nazwaPliku3="/media/kamil/Nowy/dokumenty/IdeaPRoject2020 12/JSON Database/JSON Database/task/src/server/data/db.json";
           try {
               OutputStream outputStream3 = new FileOutputStream(nazwaPliku3, false);
              // System.out.println("utworzono plik");
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
        }
        //-------------------------

        //-------------------------





        //-------------------------
        public void zapiszPlikJSON(){
            Gson gson = new Gson();
            try {
                FileWriter pliczek = new FileWriter(nazwaPliku3, false);
                pliczek.write(gson.toJson(zbior));
                pliczek.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //-------------------------


        public void setByIdZbior(JsonElement obiektJson) {

           // JsonArray entrySet = obiektJson.getAsJsonArray();

        boolean wartoscValueTekstowa=true;
        boolean wartoscKeyTekstowa=true;
        boolean wartoscValuePRymityw=false;
            boolean wartoscValueObject=false;
            boolean wartoscKeyTablica=false;



            //JsonElement element = JsonParser.parseString(json);
            JsonObject obj = obiektJson.getAsJsonObject(); //since you know it's a JsonObject
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
            String kluczyk = "";
            String wartoscTekstowa="";
            JsonElement je = null;
            JsonObject jo=null;

            JsonArray tablicaJ = null;

            for (Map.Entry<String, JsonElement> entry: entries) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                System.out.println("klucz:"+key+"<-");
                System.out.println("value "+value);

                if(key.equals("key") && !value.isJsonArray()) {
                    kluczyk= value.getAsString();
                    wartoscKeyTekstowa=true;

                }else

                if(key.equals("key") && value.isJsonArray()){
                   // System.out.println("tablica jsona");
                     tablicaJ= value.getAsJsonArray();
                    wartoscKeyTablica=true;
                   // zapiszDane(tablicaJ,obiektJson);




                }

                if(key.equals("value") && value.isJsonPrimitive()){
                   je=value;
                    wartoscValuePRymityw=true;


                }

                if(key.equals("value") && value.isJsonObject()){
                    jo=value.getAsJsonObject();
                    wartoscValueObject=true;


                }


                if(key.equals("value") && !value.isJsonArray()) {
                    wartoscTekstowa=entry.getValue().toString();
                    System.out.println("wartosc tekstowa:"+wartoscTekstowa);
                    wartoscValueTekstowa=true;

                }



                }

            if(wartoscKeyTablica){
                zapiszDane(tablicaJ,obiektJson,jo);


            }

            if (wartoscKeyTekstowa && wartoscValueTekstowa ){
                // zbior.put(kluczyk,wartoscTekstowa);
            }

            if (wartoscKeyTekstowa&&wartoscValuePRymityw ){
                 zbior.put(kluczyk,je);
                zapiszPlikJSON();
            }
            if (wartoscKeyTekstowa&&wartoscValueObject ){
                zbior.put(kluczyk,jo);
                zapiszPlikJSON();
            }


                  //  zbior.add(entry)   ;

            }


        //--------------------------------------------------------------------
        public Object getByIdZbior( JsonElement obiektJson) {

            boolean wartoscValueTekstowa=true;
            boolean wartoscKeyTekstowa=true;
            boolean wartoscValuePRymityw=false;
            boolean wartoscValueObject=false;



            //JsonElement element = JsonParser.parseString(json);
            JsonObject obj = obiektJson.getAsJsonObject(); //since you know it's a JsonObject
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
            String kluczyk = "";
            String wartoscTekstowa="";
            JsonElement je = null;
            JsonObject jo=null;


            for (Map.Entry<String, JsonElement> entry: entries) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();

                if(key.equals("key") && !value.isJsonArray()) {
                    kluczyk= value.getAsString();
                    wartoscKeyTekstowa=true;

                }else
                if(key.equals("key") && value.isJsonArray()){
                    JsonArray tablicaJ= value.getAsJsonArray();
                        var o=pobierzDane(tablicaJ,obiektJson);
                    wartoscKeyTekstowa=true;
                    if(o==null){
                        return String.valueOf(polecenia.ERROR);
                    }else{
                        System.out.println(" odczytano:"+o);
                        return o;
                    }

                }





            }
            if(wartoscKeyTekstowa) {
                var o = zbior.get(kluczyk);
                if(o==null){
                    return String.valueOf(polecenia.ERROR);
                }else{
                    System.out.println(" odczytano:"+zbior.get(kluczyk));
                    return zbior.get(kluczyk);
                }

            }else {

                return  String.valueOf(polecenia.ERROR);
            }

        }

        private Object pobierzDane(JsonArray tablicaJ, JsonElement obiektJson) {




            int x=tablicaJ.size();
            JsonObject jo;
            JsonElement je = null;
            Map<String, Object> entries = zbior;
            String key = tablicaJ.get(0).getAsString();
            jo = (JsonObject) entries.get(key);
            if(x==1){
                je=jo;
            }
           else
             {
                for (int i = 1; i < x; i++) {
                    je = jo.get(tablicaJ.get(i).getAsString());
                    System.out.println("wyiterowano" + je);
                    if (je.isJsonObject()) {
                        jo = (JsonObject) je;
                    }
                    int k = 1;
                }
            }


          //if(je.isJsonPrimitive()) return je;
         // else


            //return null;
            return je;
        }
        //-------------------------



        //-------------------------
        private void zapiszDane(JsonArray tablicaJ, JsonElement obiektJson,JsonObject wartosc) {

            Gson gson=new Gson();
            String s = gson.toJson(zbior);

            JsonElement element = JsonParser.parseString(s);
            JsonArray jsonArray = new Gson().fromJson(element, JsonElement.class).getAsJsonArray();
            JsonArray jsonArray2 = new JsonArray();

            JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject

            Iterator iterator = obj.keySet().iterator();
            String key = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();



            }






            int x=tablicaJ.size();
            JsonObject jo;
            JsonElement je = null;
           // Map<String, Object> entries = zbior;
             key = tablicaJ.get(0).getAsString();
            jo = (JsonObject) zbior.get(key);
            je=jo;
            if(x==1){
                je=jo;
                zbior.put(key,wartosc);
                jo.add(key,wartosc);
            }
            else
            {
                for (int i = 1; i < x; i++) {
                    je = jo.get(tablicaJ.get(i).getAsString());
                    System.out.println("wyiterowano" + je);
                    if (je.isJsonObject()) {
                        jo = (JsonObject) je;
                    }
                    int k = 1;
                }
                jo.add(tablicaJ.get(x-1).getAsString(),wartosc);
            }

        int cc=1;

        }


        //-------------------------



    }

    //#################################################################################################
//#################################################################################################

//#################################################################################################
//#################################################################################################
}
//#################################################################################################
//#################################################################################################
//#################################################################################################
//#################################################################################################
class Session extends Thread {
    private final Socket socket;

    Object msg;
    boolean czyKoniec2=false;
    Main.polecenia cmd;
    public Session(Socket socketForClient,boolean marker) {
        this.socket = socketForClient;
        this.czyKoniec2=marker;
    }

    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            {
                msg = input.readUTF();

                Gson gson = new Gson();
                gson.toJson(msg);


                //******

                Map<String, Object> jsonWiadomosc = new HashMap<>();

                Main.JsonWalidator jw = new Main.JsonWalidator();
                boolean jwTak=false;
                jwTak=jw.czyPrawidlowyGSON(msg.toString());

                Set<Map.Entry<String, JsonElement>> jsonWiadomosc6 = null;

                if (jwTak) {

                    System.out.println("Received: "+jw.obiektJson);
                    switch (jw.polecenie) {
                        case "exit":

                            cmd = Main.polecenia.exit;
                            czyKoniec2=true;
                            //System.out.println("bjest exit");
                            Main.czyKoniec=true;


                            jsonWiadomosc.put("response","OK");
                           output.writeUTF(gson.toJson(jsonWiadomosc));
                            System.out.println("Sent: "+gson.toJson(jsonWiadomosc));
                            break;

                        case "set":
                            Main.jwMain=jw;
                            Main.baza.setByIdZbior(jw.obiektJson);
                            msg="OK";
                           jsonWiadomosc = new HashMap<>();
                            jsonWiadomosc.put("response",msg);
                            output.writeUTF(gson.toJson(jsonWiadomosc));
                            System.out.println("Sent: "+gson.toJson(jsonWiadomosc));

                        break;
                       case "get":
                           Main.jwMain=jw;
                           msg= (Main.baza.getByIdZbior(jw.obiektJson));
                           if(msg.equals(String.valueOf(Main.polecenia.ERROR))){
                               msg=Main.polecenia.ERROR.toString();
                               jsonWiadomosc.put("response",msg);


                           }
                           else {
                               jsonWiadomosc.put("response","OK");
                               jsonWiadomosc.put("value",msg);
                               output.writeUTF(gson.toJson(jsonWiadomosc));
                               System.out.println("Sent: "+gson.toJson(jsonWiadomosc));
                           }


                            break;

                    }
                }








                //******





                output.writeUTF(gson.toJson(jsonWiadomosc));

                System.out.println("Sent end: "+gson.toJson(jsonWiadomosc));

            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCzyKoniec() {
        return czyKoniec2;
    }
}
//#################################################################################################
//#################################################################################################



//#################################################################################################
//#################################################################################################
