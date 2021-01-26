package client;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    public void testMain() {
        client.Main.main("-t" ,"get", "-k", "1");
        client.Main.main("-t" ,"set", "-k", "1","-v"," hello wordl!");
        client.Main.main("-t" ,"get", "-k", "1");
        client.Main.main("-t" ,"delete", "-k", "1");
        client.Main.main("-t" ,"get", "-k", "1");
        client.Main.main("-t" ,"delete", "-k", "1");
        client.Main.main("-t" ,"set", "-k", "tekst","-v"," hello wordl!");

        //client.Main.main("-t" ,"exit");

    }




}