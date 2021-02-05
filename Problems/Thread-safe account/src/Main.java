class Account {

    private long balance = 0;

    public synchronized boolean withdraw(long amount) {
       if(getBalance()<amount)

        return false;
       else {
           balance-=amount;

           return true;
       }
    }

    public synchronized void deposit(long amount) {
        // do something useful
        this.balance+=amount;
    }

    public synchronized long getBalance() {
        return balance;
    }
}