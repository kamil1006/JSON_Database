package client;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    public void testMain() {

     client.Main.main("-t" ,"get", "-k", "1");
       client.Main.main("-t" ,"set", "-k", "1","-v"," hello worooodl!");
        client.Main.main("-t" ,"get", "-k", "1");
    //    client.Main.main("-t" ,"delete", "-k", "1");
        client.Main.main("-t" ,"get", "-k", "1");
 //       client.Main.main("-t" ,"delete", "-k", "1");

           client.Main.main("-t" ,"set", "-k", "tekst","-v"," hello wordqql!");
        client.Main.main("-t" ,"get", "-k", "tekst");
        //client.Main.main("-t" ,"exit");
        //client.Main.main("-t" ,"exit");
        client.Main.main("-in" ,"testSet.json");
        client.Main.main("-in" ,"testGet.json");
    }




}