import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n=scanner.nextInt();
        StringTokenizer st = new StringTokenizer(line," ");

        ArrayList<Integer> tablica = new ArrayList<Integer>();
       while (st.hasMoreTokens()){
            tablica.add(Integer.parseInt(st.nextToken()));
        }

        int odstep= Math.abs(tablica.get(0) -n);
        for(int i: tablica){
            if(Math.abs(i-n)<odstep) odstep=Math.abs(i-n);

        }
        //System.out.println(odstep);
        ArrayList<Integer> temp= new ArrayList<>();
        for(int a:tablica){
            if(Math.abs(a-n)==odstep) temp.add(a);
        }
        Collections.sort(temp);
       for(int a:temp)
           System.out.print(a+" ");
    }
}

/*
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String intLine = scanner.nextLine();
        String[] intLineArray = intLine.split(" ");

        ArrayList<Integer> integerArrayList = new ArrayList<>();

        for (String s : intLineArray) {
            integerArrayList.add(Integer.valueOf(s));
        }

        int number = scanner.nextInt();

        ArrayList<Integer> tempArrayList = new ArrayList<>();

        int smallestDistance = -1;
        for (Integer i : integerArrayList) {
            int actualDistance = i - number >= 0 ? i - number : number - i;
            if (smallestDistance == -1) {
                smallestDistance = actualDistance;
                tempArrayList = new ArrayList<>();
            } else if (actualDistance < smallestDistance) {
                smallestDistance = actualDistance;
                tempArrayList = new ArrayList<>();
            }
            if (smallestDistance == actualDistance) {
                tempArrayList.add(i);
            }

        }
        Collections.sort(tempArrayList);

        for (Integer i : tempArrayList) {
            System.out.print(i + " ");
        }
    }
}

 */



/*
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] string = scanner.nextLine().split(" ");
        ArrayList<Integer> list = Arrays.stream(string)
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toCollection(ArrayList::new));
        int number = scanner.nextInt();

        ArrayList<Integer> distanceList = list.stream()
                                                .map(num -> Math.abs(number - num))
                                                .collect(Collectors.toCollection(ArrayList::new));

//        System.out.printf("distanceList = %s%n", distanceList);

        int minimum = Collections.min(distanceList);

        list.stream()
                .filter(num -> Math.abs(number - num) == minimum)
                .sorted()
                .forEach(num -> System.out.print(num + " "));
    }
}
 */