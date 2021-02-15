import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        int liczba =4;

        char[][] tablica= new char[4][];

        for(int i =0;i<liczba;i++){
            tablica[i]=scanner.nextLine().toCharArray();
        }
    /*
      for(int i=0;i<liczba;i++){
          for (int j=0;j<liczba;j++)
             System.out.print(tablica[i][j]);
         System.out.println();
          }
      */
        String mark="YES";
        for (int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(tablica[a][b]==tablica[a+1][b] &&tablica[a][b]==tablica[a+1][b+1]
                        &&tablica[a][b]==tablica[a][b+1]  && mark=="YES" ){
                    mark="NO";
                    break;
                }


            }
        }
        System.out.println(mark);

    }
}