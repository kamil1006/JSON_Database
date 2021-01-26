import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        ArrayList<String> lista = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            lista.add(scanner.next());
        }

        SortedSet<String> sortedSet = new TreeSet<String>(lista);

        sortedSet.forEach(System.out::println);

        /*
         new Scanner(System.in)
                .tokens()
                .skip(1)
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(System.out::println);
         */

        /*
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        Set<String> data = new TreeSet<>();
        while (a > 0) {
            data.add(sc.next());
            a--;
        }
        data.forEach(n -> System.out.println(n));
         */


    }
}