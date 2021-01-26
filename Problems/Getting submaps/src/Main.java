import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String wiersz;
        //wiersz= scanner.nextLine();
        //String[] t = wiersz.split(" ");
        int a =(scanner.nextInt());
        int b =(scanner.nextInt());
        int x =scanner.nextInt();

        SortedMap<Integer, String> mapka = new TreeMap<>();

        for(int i=0;i<x;i++){
            /* wiersz= scanner.nextLine();
            String[] tablica = wiersz.split(" ");
            mapka.put(Integer.getInteger(tablica[0]),tablica[1]);
            wiersz= scanner.nextLine();

             */
            int k=scanner.nextInt();
            String s=scanner.next();
            mapka.put(k, s);
        }

        for(var v :mapka.entrySet()){
            if(v.getKey()<=b && v.getKey()>=a){
                System.out.println(v.getKey() + " " + v.getValue());
            }
        }


    }
}