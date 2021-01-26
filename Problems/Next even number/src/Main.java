import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = x-> {

        if(x%2==0) return x+2;
        else
        return x+1;
    };// Write your code here

    //public static LongUnaryOperator unaryOperator = x -> x % 2 == 0 ? x + 2 : x + 1;
    //public static LongUnaryOperator unaryOperator = x -> x + 2 - x % 2;

}