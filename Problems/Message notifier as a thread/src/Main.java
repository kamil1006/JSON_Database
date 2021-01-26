class MessageNotifier extends Thread {

    // write fields to store variables here

    String tekst;
    int ilosc;


    public MessageNotifier(String msg, int repeats) {
        // implement the constructor
        this.tekst=msg;
        this.ilosc=repeats;



    }

    @Override
    public void run() {
        // implement the method to print the message stored in a field
        for (int i=0;i<ilosc;i++)
        {
            System.out.println(tekst);
        }


    }
}