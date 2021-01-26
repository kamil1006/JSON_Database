import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        int n =scanner.nextInt();
        int[][] tablica = new int[n][n];
        //-------------------------
        for(int x=0;x<n;x++)
            for(int y=0;y<n;y++)
                tablica[x][y]=0;
        //-------------------------
        wektor w= new wektor(n-1);
        int x=0;
        int y=0-1;

            int minx=0;
            int miny=0;
            int maxx=n-1;
            int maxy=n-1;

        for(int i=1;i<=n*n;i++){

            minx= w.minx;
            miny= w.miny;
            maxx= w.maxx;
            maxy= w.maxy;

            x=x+w.x;
            if (x==maxx+1) {
                x--;

            }
            if(x<minx){
                x++;

            }

            y=y+w.y;
           if (y==maxy+1){
               y--;

           }
           if(y<miny){
               y++;

           }

            if(x<n && y<n && x>=0 && y>=0){
                if(tablica[x][y]>0){
                    w.zmiana();
                    i--;

                }
                else tablica[x][y]=i;
            }else {
                w.zmiana();
                i--;
            }


        }
        //-------------------------
        for( x=0;x<n;x++){
            for( y=0;y<n;y++)
                System.out.print(tablica[x][y]+" ");
            System.out.println("");
        }


    }

}
//##########################################################
//##########################################################
//##########################################################
class wektor{

    int x;
    int y;
    private int t;
    int maxx;
    int minx;
    int maxy;
    int miny;

    public wektor(int n){
        this.x=0;
        this.y=1;
        this.t=0;
        this.maxx=n;
        this.maxy=n;
        this.minx=0;
        this.miny=0;
    }
    public void zmiana(){
        this.t++;
        switch (t%4){
            case 1:
                this.x=1;
                this.y=0;

                break;
            case 2:
                this.x=0;
                this.y=-1;
                this.maxy--;

                break;
            case 3:
                this.x=-1;
                this.y=0;
                this.maxx--;

                break;
            case 0:
                this.x=0;
                this.y=1;
                this.miny++;
                this.minx++;
                break;
            default:
                break;
        }

    }

}