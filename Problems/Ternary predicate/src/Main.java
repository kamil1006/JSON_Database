class Predicate {

    @FunctionalInterface
    public interface TernaryIntPredicate {
        // Write a method here
         boolean test(int x1, int x2, int x3);

    }

    public static final TernaryIntPredicate allValuesAreDifferentPredicate =

            ((x1, x2, x3) -> {


                boolean odpowiedz = (x1==x2) || (x1==x3) || (x2==x3);
                return !odpowiedz;

            });


            //(x, y, z) -> {
    //        return x == y || y == z || x == z ? false : true;



    //  (a, b, c) -> a != b && a != c && b != c;

        // Write a lambda expression here

}