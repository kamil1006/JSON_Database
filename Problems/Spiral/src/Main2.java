import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        int n ;
        n=5;
        //n= scanner.nextInt();
        int[][] tablica = new int[n][n];
        //-------------------------
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                tablica[x][y] = 0;
        //-------------------------
        int x=0;
        int y=0;
        int minx=0;
        int miny=0;
        int maxx=n-1;
        int maxy=n-1;
        int kierunekX=1;
        int kierunekY=0;

        for(int i=1;i<=n*n;i++){
            tablica[x][y]=i;






        }





    }

}
