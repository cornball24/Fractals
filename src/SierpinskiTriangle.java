import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class SierpinskiTriangle extends FractalPanel {

    private int iterations;

    public SierpinskiTriangle() {
        super();
        iterations = 100;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(getWidth()/2-5, getHeight()/2-5, 10, 10);

        FractalPoint one = new FractalPoint(new double[]{-300, -300}, reference, getWidth(), getHeight(), scale);
        FractalPoint two = new FractalPoint(new double[]{0, 300}, reference, getWidth(), getHeight(), scale);
        FractalPoint three = new FractalPoint(new double[]{300, -300}, reference, getWidth(), getHeight(), scale);

        g.drawPolygon(new int[]{(int) one.calculateX(), (int) two.calculateX(), (int) three.calculateX()},
                new int[]{(int) one.calculateY(), (int) two.calculateY(), (int) three.calculateY()}, 3);

        drawSierpinski(g, iterations, -300, 300, 600, 600);
    }

    private void drawSierpinski(Graphics g, int iterations, double x, double y, double width, double height) {
        if (iterations <= 0) {
            return;
        }

        FractalPoint one = new FractalPoint(new double[]{x + width / 4f, y - height / 2f},
                reference, getWidth(), getHeight(), scale);
        FractalPoint two = new FractalPoint(new double[]{x + width / 2f, y - height},
                reference, getWidth(), getHeight(), scale);
        FractalPoint three = new FractalPoint(new double[]{x + width / 4f + width / 2f, y - height / 2f},
                reference, getWidth(), getHeight(), scale);

        int[] xPoints = {(int) one.calculateX(), (int) two.calculateX(), (int) three.calculateX()};
        int[] yPoints = {(int) one.calculateY(), (int) two.calculateY(), (int) three.calculateY()};

        if (xPoints[0] - xPoints[1] == 0) {
            return;
        }

        g.drawPolygon(xPoints, yPoints, 3);

        System.out.println("xPos: "+xPos+", yPos: "+yPos);

        if (xPos >= x && xPos <= x + width / 2 && yPos <= y - height / 2 && yPos >= yPos - height) {
            drawSierpinski(g, iterations - 1, x, y - height / 2, width / 2, height / 2);
        }
        if (xPos >= x + width / 4 - (getWidth()/2f/scale) && xPos <= x + width / 2 + width / 4 && yPos <= y && yPos >= y - height / 2) {
            drawSierpinski(g, iterations - 1, x + width / 4, y, width / 2, height / 2);
        }
        if (xPos >= x + width / 2 && xPos <= x + width && yPos <= y - height / 2 && yPos >= y - height / 2) {
            drawSierpinski(g, iterations - 1, x + width / 2, y - height / 2, width / 2, height / 2);
        }
    }

    public String toString() {
        return "Sierpinski Triangle";
    }
}
