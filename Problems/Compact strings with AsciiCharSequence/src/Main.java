import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;

class AsciiCharSequence implements java.lang.CharSequence/* extends/implements */ {


    byte[] tablica;

    public AsciiCharSequence(byte[] tablica) {
        this.tablica = tablica;
    }

    @Override
    public int length() {
        return tablica.length;
    }

    @Override
    public char charAt(int index) {
        return (char) tablica[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(tablica, start , end ));
    }


    @Override
    public String toString() {
      
        return new String(tablica, StandardCharsets.UTF_8);
       
    }
}
