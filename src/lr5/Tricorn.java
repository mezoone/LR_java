package lr5;

import lr4.FractalGenerator;
import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        double xn=x;
        double yn=y;
        int i=0;
        while (i<MAX_ITERATIONS){
            double nextX = xn*xn-yn*yn+x;
            double nextY = -2*xn*yn+y;
            xn=nextX;
            yn=nextY;
            if ((xn*xn+yn*yn)>4) return i;
            i+=1;
        }
        return -1;
    }

    public String toString() {
        return "Tricorn";
    }
}
