import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);
        String wiersz = scanner.nextLine();
        String[] tablica = wiersz.split("-");
        String nowy=tablica[1]+"/"+tablica[2]+"/"+tablica[0];
        System.out.println(nowy);
    }
}