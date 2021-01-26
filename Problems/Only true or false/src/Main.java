class Primitive {
    public static boolean toPrimitive(Boolean b) {

        //lepsze
        //return b != null && b;

        boolean bb= b != null ? b:false;
        return bb;

    }
}