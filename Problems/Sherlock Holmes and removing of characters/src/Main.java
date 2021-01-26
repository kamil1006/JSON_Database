import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        String wiersz1,wiersz2;
        wiersz1= scanner.next();
        wiersz2= scanner.next();
        wiersz1=wiersz1.toLowerCase();
        wiersz2=wiersz2.toLowerCase();

        char[] tab1= wiersz1.toCharArray();
        char[] tab2= wiersz2.toCharArray();

        int size1=tab1.length;
        int size2=tab2.length;

        Map<Integer, Character> mapa1 = new HashMap<>();

        TreeSet set1 = new TreeSet<>();
        int i=1;
        for(char c:tab1){
                set1.add(c);
                mapa1.put(i,c);
                i++;
            }

        Map<Integer, Character> mapa2= new HashMap<>();

        i=1;
        TreeSet set2 = new TreeSet<>();
        for(char c:tab2){
            set2.add(c);
            mapa2.put(i,c);
            i++;

        }

        TreeSet set3 = new TreeSet<>();

        set3.addAll(set1);
        set3.addAll(set2);

        int o=0;
        for(Object c:set3){

            int l1=0;
            for(int k=0 ;k< tab1.length;k++){
            if(c.equals(tab1[k])) l1++;
            }
            int l2=0;
            for(int k=0 ;k< tab2.length;k++){
                if(c.equals(tab2[k])) l2++;
            }
            o=o+Math.abs(l1-l2);

        }



        TreeSet set4 = new TreeSet<>(set1);
        set4.removeAll(set2);
        TreeSet set5 = new TreeSet<>(set2);
        set5.removeAll(set1);

        i=1;
        System.out.println(
                //set4.size()+set5.size()+
                        o);

    }
}