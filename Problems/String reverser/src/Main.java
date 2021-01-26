import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        StringReverser reverser = new StringReverser() {
            @Override
            public String reverse(String str) {

               String s= "";
               for(int i =str.length()-1;i>=0;i--)
                   s=s+str.charAt(i);
                return s;
                //return new StringBuilder().append(line).reverse().toString();
            }
        };

                /* create an instance of an anonymous class here,
                                     do not forget ; on the end */

        System.out.println(reverser.reverse(line));
    }

    interface StringReverser {

        String reverse(String str);
    }

}