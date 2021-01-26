class Problem {
    public static void main(String[] args) {

        String mode = null;
        boolean jest=false;

        for (int i=0;i< args.length-1;i++){
            if(i< args.length && args[i].equals("mode")){

                jest=true;
                if(i+1< args.length)
                   mode=args[i+1];



            }
            i++;


        }

        if(jest) System.out.println(mode);
        else System.out.println("default");

    }
}

/*
class Problem {
    public static void main(String[] args) {
        String outputStr = "default";
        int counter = 0;
        while (counter != args.length - 1) {
            if ("mode".equals(args[counter])) {
                outputStr = args[counter + 1];
                break;
            }
            counter++;
        }

        System.out.println(outputStr);
    }
}
 */