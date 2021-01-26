import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);
        String wiersz = scanner.nextLine();
        wiersz=wiersz.toUpperCase(Locale.ROOT);

        int gc=0;

        for(int i=0;i<wiersz.length();i++){
            if(wiersz.charAt(i)=='G'||wiersz.charAt(i)=='C')
                gc++;



        }

        double result = ((double) gc / (double) wiersz.length())*100;
        System.out.println(result);

    }
}