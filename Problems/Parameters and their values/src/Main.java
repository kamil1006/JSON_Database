class Problem {
    public static void main(String[] args) {

        for (int i=0;i< args.length-1;i++){
            if(i< args.length)
            System.out.print(args[i]);
            if(i+1< args.length)
            System.out.println("="+args[i+1]);
            i++;

        }

    }
}