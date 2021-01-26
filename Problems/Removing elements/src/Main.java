import java.util.*;
import java.util.stream.Collectors;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        // write your code here


        String[] string = str.split(" ");
        ArrayList<Integer> list = Arrays.stream(string)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        Set<Integer> zbior = new HashSet<Integer>(list);
        return zbior;

        /*
        Set<Integer> set = new TreeSet<>();

        for (var v : str.split("\\s")) {
            set.add(Integer.parseInt(v));
        }

        return set;
         */

    /*
    List<String> list = Arrays.asList(str.split(" "));
        Set<Integer> set = new HashSet<>();
        list.forEach(x -> set.add(Integer.parseInt(x)));
        return set;
     */


    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here
        ArrayList<Integer> list= new ArrayList<>(set);
        set.clear();
        list.stream()
                .filter(num -> num <= 10)
                .sorted()
                .forEach(num -> set.add(num));


        // set.removeIf(s -> s > 10);

    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}