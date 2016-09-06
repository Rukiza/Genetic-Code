package main;

public class Main {

    static String G = "G";
    static String A = "A";
    static String C = "C";
    static String T = "T";

    private static int STRING_AMOUNT = 10;
    private static int MAX_STRING_LENGTH = 100;

    public static void main(String[] args) {
	    // write your code here
        GeneticCode gc = new GeneticCode();


        for (int i = 0; i < STRING_AMOUNT; i++) {
            Tuple t = gen();
            GeneticCode.Tuple t2 = gc.alignment(t.s1, t.s2);
            System.out.println(t2.x);
            System.out.println(t2.y);
            System.out.println(t2.a + "  ("+t2.t+")");
            System.out.println();
        }

        /*
        for (int j = 10; j < 10000; j = j * 5) {
            for (int i = 0; i < 10; i++) {
                String x = createString(j);
                String y = createString(j);
                long start = System.currentTimeMillis();
                gc.alignment(x,y);
                long end = System.currentTimeMillis();
                System.out.println("String Size = "+j);
                System.out.println(end - start);
                System.out.println();
            }
        }
        */
    }

    public static Tuple gen () {
        String x;
        String y;

        int xLength = (int) ((1+Math.random()) * (MAX_STRING_LENGTH/2));
        int yLength = (int) ((1+Math.random()) * (MAX_STRING_LENGTH/2));

        x = createString(xLength);
        y = createString(yLength);

        return new Tuple(x, y);
    }

    private static String createString(int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            double r = Math.random();
            if (r < 0.25) {
                s = s + G;
            } else if (r < 0.5) {
                s = s + A;
            } else if (r < 0.75) {
                s = s + C;
            } else {
                s = s + T;
            }
        }
        return s;
    }

    private static class Tuple {
        private final String s1;
        private final String s2;

        public Tuple (String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }
}
