import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner=new Scanner(System.in);
        long a= scanner.nextLong();
        String znak=scanner.next();
        long b=scanner.nextLong();

        switch (znak){

            case "+":
                System.out.println(a+b);
                break;
            case "-":
                System.out.println(a-b);
                break;
            case "/":

                if (b == 0) {
                    System.out.println("Division by 0!");
                } else {
                    System.out.println(a / b);
                }


                break;

            case "*":
                System.out.println(a*b);
                break;

            default:
                System.out.println("Unknown operator");
                break;
        }

    }
}