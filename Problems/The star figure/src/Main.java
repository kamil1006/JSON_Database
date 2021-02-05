import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if(n%2!=0){
            char[][] tab= new char[n][n];

        for (int x=0;x<n;x++)
            for (int y=0;y<n;y++)
                tab[x][y]='.';


            int i=0;
            for (int x=0;x<n;x++)
            {
               tab[x][i]='*';
                tab[x][n-1-i]='*';
                i++;
            }

            int pol=n/2;
            for (int x=0;x<n;x++) {
                tab[x][pol]='*';
                tab[pol][x]='*';
            }



            for (int x=0;x<n;x++){
                for (int y=0;y<n;y++) {
                    System.out.print(tab[x][y]+" ");
                }
                System.out.println("");


            }


        }


    }
}
/*
Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int middle = n / 2;
        int last = n - 1;
        int first = 0;
        String[][] arr = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == middle || j == first || j == last || i == middle) {
                    arr[i][j] = "*";
                } else {
                    arr[i][j] = ".";
                }
                System.out.print(arr[i][j] + " ");
            }
            first++;
            last--;
            System.out.println();
        }
 */