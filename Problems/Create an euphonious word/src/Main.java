import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);
        String wiersz = scanner.nextLine();
        String nowy = new String();

        for(int i=0;i<wiersz.length();i++){
            switch (wiersz.charAt(i)){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                    nowy+="1";
                    break;
                default:
                    nowy+="0";
                    break;
            }



        }int k=0;
        if(wiersz.length()>2){
            for(int i=0;i+2<wiersz.length();i++){
            if(nowy.charAt(i)=='0'&&nowy.charAt(i+1)=='0'&& nowy.charAt(i+2)=='0'||nowy.charAt(i)=='1'&&nowy.charAt(i+1)=='1'&& nowy.charAt(i+2)=='1'){
                k++;
                i=i+1;
            }
            }
            //System.out.println(nowy);
            System.out.println(k);


        }

    }
}