import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);
        String[] tablica= new String[5];
        for(int i = 0;i<5;i++){

            tablica[i]= scanner.next();

        }

        for(int i = 0;i<5;i++){
            System.out.println( tablica[i]);


        }

    }
}