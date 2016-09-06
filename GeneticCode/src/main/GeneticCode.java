package main;

/**
 * Created by Shane on 4/09/16.
 */
public class GeneticCode {


    public Tuple alignment(String x, String y) {
        Tuple[][] cache = new Tuple[x.length()+1][y.length()+1];

        //Init both bases [0, i] and [j, 0]
        if (0 < cache.length && 0 < cache[0].length) {
            cache[0][0] = new Tuple(0, "", "", "");
        }
        for (int i = 1; 0 < cache.length && i < cache[0].length ; i++) {
            Tuple t = cache[0][i -1];
            cache[0][i] = new Tuple(t.t-2, t.a+"*", t.x+" ", t.y+y.charAt(i-1));
        }
        for (int i = 1; i < cache.length && 0 < cache[0].length ; i++) {
            Tuple t = cache[i -1][0];
            cache[i][0] = new Tuple(t.t-2, t.a+"*", t.x+x.charAt(i-1), t.y+" ");
        }

        for (int i = 1; i < cache.length; i++) {
            for (int j = 1; j < cache[0].length; j++ ) {
                int alpha = calculate(x,i-1,y, j-1);
                int v1 = alpha + cache[i-1][j-1].t;
                int v2 = -2 + cache[i-1][j].t;
                int v3 = -2 + cache[i][j-1].t;
                cache[i][j] = calculate(alpha, v1,v2,v3, cache, i, j, x, y);
            }
        }
        return cache[x.length()][y.length()];
    }

    private Tuple calculate(int alpha, int v1, int v2, int v3, Tuple[][] cache, int i, int j, String x, String y) {
        int test = Math.max(Math.max(v2, v3), v1);
        if (v1 == test) {
            String s = alpha > 0 ? "+" : "-";
            Tuple t = cache[i-1][j-1];
            return new Tuple(v1, t.a+s, t.x+(""+x.charAt(i-1)), t.y+(""+y.charAt(j-1)));
        } else if (v2 == test) {
            Tuple t = cache[i-1][j];
            return new Tuple(v2, t.a+"*", t.x+(""+x.charAt(i-1)), t.y+" ");
        } else {
            Tuple t = cache[i][j-1];
            return new Tuple(v3, t.a+"*", t.x+" ", t.y+(""+y.charAt(j-1)));
        }
    }

    private int calculate(String x, int xIndex, String y, int yIndex) {
        if (x.charAt(xIndex) == y.charAt(yIndex)) {
            return 1;
        } else {
            return -1;
        }
    }

    public class Tuple {
        public final int t;
        public final String a;
        public final String x;
        public final String y;

        public Tuple (int t, String a, String x, String y) {
            this.t = t;
            this.a = a;
            this.x = x;
            this.y = y;
        }
    }
}
