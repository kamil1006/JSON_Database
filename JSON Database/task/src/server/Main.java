package server;

import com.google.gson.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

//#################################################################################################
//#################################################################################################
public class Main {

    private static final int PORT = 34522;

     static boolean czyKoniec=false;
        static StringWalidator swMain;

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

        boolean blokada=true;


        if(!blokada) {
            //++++++++++ poczatek blokady ************

            // System.out.println("Hello, world!");

            Scanner scanner = new Scanner(System.in);

            //-------------------------------------------------------------------------------------
            do {
                System.out.print("> ");
                String input = scanner.nextLine();
                String[] tablica = input.split(" ");
                switch (tablica[0]) {
                    case "exit":
                        cmd = Main.polecenia.exit;
                        break;
                    case "get":
                        cmd = Main.polecenia.get;
                        break;

                    case "set":
                        cmd = Main.polecenia.set;

                        break;
                    case "delete":
                        cmd = Main.polecenia.delete;

                        break;
                    default:
                        cmd = Main.polecenia.ERROR;
                        break;


                }
                //***************************
                StringWalidator sw = new StringWalidator();
                boolean tak = sw.czyPrawidlowy(input);
                //***************************
                if (cmd == Main.polecenia.get) {
                    if (tak) {
                        int k = Integer.parseInt(tablica[1]);
                        System.out.println(baza.get(k));
                    } else System.out.println(polecenia.ERROR);

                }
                //***************************
                if (cmd == Main.polecenia.set) {
                    if (tak) {
                        String zapisek = input.substring(3 + 1 + tablica[1].length() + 1);
                        int k = Integer.parseInt(tablica[1]);
                        System.out.println(baza.setById(k, zapisek));

                    } else System.out.println(polecenia.ERROR);

                }

                //***************************
                if (cmd == Main.polecenia.delete) {
                    if (tak) {

                        int k = Integer.parseInt(tablica[1]);
                        System.out.println(baza.deleteById(k));

                    } else System.out.println(polecenia.ERROR);

                }
                //***************************

                if (cmd == Main.polecenia.ERROR) {
                    System.out.println(polecenia.ERROR);
                }
                //***************************

            } while (!cmd.equals(polecenia.exit));
            //-------------------------------------------------------------------------------------
        //++++++++++ koniec blokady ************
        }

    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%

    //%%%%%%%%%%%%%%%%%%%%%%%%%

//#################################################################################################
//#################################################################################################
protected static class StringWalidator {

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
                            if (Main.baza.rozmiarMapy <= max)
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
                            if (Main.baza.rozmiarMapy <= max)
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
    static class WpisyOperacje {

        //-------------------------
        private Wpis[] wpisy;
        private Map<String, String> mapa;//= new HashMap<>();
        private int rozmiar;
        private int rozmiarMapy;
        //-------------------------

        public WpisyOperacje(int n) {
            this.wpisy = new Wpis[n];
            this.rozmiar = n + 1;
            for (int i = 0; i < n; i++) {
                wpisy[i] = new Wpis("");

            }
            rozmiarMapy=0;
            mapa= new HashMap<>();
        }
        //-------------------------

        public String get(int n) {

            if (n <= rozmiar && wpisy[n - 1].wiersz.length() > 0)
                return wpisy[n - 1].wiersz;
            else
                return String.valueOf(polecenia.ERROR);

        }
        //-------------------------
        public String getMapa(String n) {

            String s = mapa.getOrDefault(n,"0");
            if(s.equals("0"))
            return String.valueOf(polecenia.ERROR);
            else
                return s;

        }
        //-------------------------

        public void setWpisy(Wpis[] wpisy) {
            this.wpisy = wpisy;
        }

        //-------------------------
        public String setById(int n, String tekst) {
            if (n <= rozmiar) {
                this.wpisy[n - 1].setWiersz(tekst);
                return String.valueOf(polecenia.OK);
            } else
                return String.valueOf(polecenia.ERROR);
        }

        //-------------------------
        public String setByIdMapa(String n, String tekst) {
            if (rozmiarMapy <= rozmiar) {
                mapa.put(n,tekst);
                rozmiarMapy++;
                return String.valueOf(polecenia.OK);
            } else
                return String.valueOf(polecenia.ERROR);
        }

        //-------------------------


        public String deleteById(int n) {
            if (n <= rozmiar )
            {
                if( this.wpisy[n-1].getWiersz()!=""){
                this.wpisy[n - 1].setWiersz("");
                return String.valueOf(polecenia.OK);
                 }else return String.valueOf(polecenia.ERROR);
            } else
                return String.valueOf(polecenia.ERROR);

           // return setById(n, tekst);

        }
        //-------------------------
        public String deleteByIdMapa(String n) {

                String s = mapa.getOrDefault(n,"0");
                if (s.equals("0")) {
                    return String.valueOf(polecenia.ERROR);
                } else {
                    mapa.remove(n);
                    rozmiarMapy--;
                    return String.valueOf(polecenia.OK);
                }




        }
        //-------------------------

    }

    //#################################################################################################
//#################################################################################################
    static class Wpis {

        String wiersz = "";

        public Wpis(String wiersz) {
            this.wiersz = wiersz;
        }

        public String getWiersz() {
            return wiersz;
        }

        public void setWiersz(String wiersz) {
            this.wiersz = wiersz;
        }


    }
//#################################################################################################
//#################################################################################################
}
//#################################################################################################
//#################################################################################################
//#################################################################################################
//#################################################################################################
class Session extends Thread {
    private final Socket socket;

    String msg;
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
            //for (int i = 0; i < 5; i++)
            {
                msg = input.readUTF();

                Gson gson = new Gson();
                gson.toJson(msg);

               // System.out.println("Received: "+msg+"");
                //System.out.println("Received: "+gson);

                //******
                Main.StringWalidator sw = new Main.StringWalidator();
                boolean tak=false;
                        //tak = sw.czyPrawidlowy(msg);
                        tak = sw.czyPrawidlowyGSON(msg);


                //System.out.println(tak);
                //System.out.println(sw.getPolecenie());

                gson = new Gson();
                Map<String, String> jsonWiadomosc = new HashMap<>();

                int k;
                String k2;
                if (tak) {
                    switch (sw.polecenie){
                        case "exit":
                            cmd = Main.polecenia.exit;
                            czyKoniec2=true;
                            //System.out.println("bjest exit");
                            Main.czyKoniec=true;
                            jsonWiadomosc.put("response","OK");
                            break;
                        case "get":
                            Main.swMain=sw;
                             k = sw.nr;
                                k2=String.valueOf(sw.nr2);
                            //msg=(Main.baza.get(k));
                                msg=Main.baza.getMapa(k2);
                            if(msg.equals(Main.polecenia.ERROR.toString())){
                                String ttt="No such key";
                                jsonWiadomosc.put("response",msg);

                                if(!sw.nieLiczba)
                                jsonWiadomosc.put("reason",ttt);
                            }
                            else {
                                jsonWiadomosc.put("response","OK");
                                jsonWiadomosc.put("value",msg);
                            }
                            break;
                        case "set":

                            Main.swMain=sw;
                            // k = sw.nr;
                            k2=String.valueOf(sw.nr2);
                           // Main.baza.setById(k, sw.tekst);
                            Main.baza.setByIdMapa(k2, sw.tekst);
                            msg="OK";
                            jsonWiadomosc.put("response",msg);
                               break;
                        case "delete":

                            Main.swMain=sw;
                            //k = sw.nr;
                            k2=String.valueOf(sw.nr2);
                            //msg=Main.baza.deleteById(k);
                            msg=Main.baza.deleteByIdMapa(k2);
                            //msg="OK";

                            if(msg.equals(Main.polecenia.ERROR.toString())){
                                String ttt="No such key";
                                jsonWiadomosc.put("response",msg);
                                jsonWiadomosc.put("reason",ttt);
                            }
                            else {
                                jsonWiadomosc.put("response","OK");
                                //jsonWiadomosc.put("value",msg);
                            }
                            break;



                        default:
                          // msg= String.valueOf(Main.polecenia.ERROR);
                    }
                }
                else {
                    if(sw.polecenie.equals("exit")){
                        jsonWiadomosc.put("response","OK");
                        czyKoniec2=true;

                        Main.czyKoniec=true;



                    }else{
                        msg= String.valueOf(Main.polecenia.ERROR);
                        jsonWiadomosc.put("response",msg);
                        if(!sw.nieLiczba)
                        jsonWiadomosc.put("reason","No such key");
                    }


                }

                //******




                //output.writeUTF(msg);
                output.writeUTF(gson.toJson(jsonWiadomosc));
                //System.out.println("Sent: A record # "+msg + "  was sent!");
                //System.out.println("Sent: "+msg+"");
               // System.out.println("Sent: "+gson.toJson(jsonWiadomosc));

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
class wiadomoscWalidator{

    String wiadomosc;
    int liczba;
    boolean czyOk;

    public wiadomoscWalidator(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    public boolean waliduj(String s){


        return false;
    }


}


//#################################################################################################
//#################################################################################################
