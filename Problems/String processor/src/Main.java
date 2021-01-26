import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        // implement this method
        String tekst;//= scanner.nextLine();
        String tekstOdwrocony = "";

        List<String> tablica =new ArrayList<>();
        boolean czyTylkoDuze=false;
        while (!czyTylkoDuze) {
            tekst= scanner.nextLine();

            if(czyDuze(tekst)){
                czyTylkoDuze=true;
            }else{
                tablica.add(odwrocString(tekst));
            }


        }

        for( String s:tablica){
            System.out.println(s);
        }




        System.out.println("FINISHED");




    }
    //-------------------------------------
    public boolean czyDuze(String s){

        boolean tak=true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                tak = false;

            }
        }


        return tak;
    }
    //-------------------------------------
    public String odwrocString(String s){

        String tekstOdwrocony = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {

                tekstOdwrocony += String.valueOf(c).toUpperCase();
            } else if (Character.isUpperCase(c))
                tekstOdwrocony += String.valueOf(c);//.toLowerCase();
        }


        return tekstOdwrocony;
    }

    //-------------------------------------
}