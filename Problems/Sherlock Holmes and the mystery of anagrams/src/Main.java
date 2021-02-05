import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

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

        Map< Character,Integer> mapa1 = new HashMap<>();
        TreeSet set1 = new TreeSet<>();

        for(char c:tab1){
            boolean jest= mapa1.containsKey(c);
            if(jest){
                int k=mapa1.get(c);
                k++;
                mapa1.remove(c);
                mapa1.put(c,k);

            }else
                mapa1.put(c,1);

        }

        Map< Character,Integer> mapa2 = new HashMap<>();
        for(char c:tab2){
            boolean jest= mapa2.containsKey(c);
            if(jest){
                int k=mapa2.get(c);
                k++;
                mapa2.remove(c);
                mapa2.put(c,k);

            }else
                mapa2.put(c,1);

        }

       if(mapa1.equals(mapa2)) System.out.println("yes");
       else System.out.println("no");







    }
}

/*
Scanner sc = new Scanner(System.in);

        var wordOne = sc.nextLine().toLowerCase(Locale.ROOT);
        var wordTwo = sc.nextLine().toLowerCase(Locale.ROOT);

        Map<Character, Integer> wordOneLetters = new HashMap<>();
        Map<Character, Integer> wordTwoLetters = new HashMap<>();

        for (char c : wordOne.toCharArray()) {
            wordOneLetters.put(c, wordOneLetters.getOrDefault(c, 0) + 1);
        }

        for (char c : wordTwo.toCharArray()) {
            wordTwoLetters.put(c, wordTwoLetters.getOrDefault(c, 0) + 1);
        }

        if (wordOneLetters.equals(wordTwoLetters)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
 */