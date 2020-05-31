package lr4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * @author shaidullin
 *
 * Kласс FractalExplorer, который позволяет исследовать различные области фрактала, путем его создания, отображения через
 * графический интерфейс Swing и обработки событий, вызванных взаимодействием приложения с пользователем
*/

public class FractalExplorer {
    private int size;
    private JImageDisplay image;
    private FractalGenerator fcGen;
    private Rectangle2D.Double range;

    public FractalExplorer(int size) {
        this.size = size;
        this.fcGen = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        fcGen.getInitialRange(this.range);
        createAndShowGUI();
        drawFractal();

    }
    /** инициализирует графический интерфейс Swing */
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal");
        JButton button = new JButton("Reset");
        image = new JImageDisplay(size, size);

        frame.add(image, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        button.addActionListener( new ActionHandler());
        image.addMouseListener(new MouseHandler());
    }
    /** вспомогательный метод для вывода на экран фрактала */
    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.width, size, y);
                double numIters = fcGen.numIterations(xCoord, yCoord);
                if (numIters == -1) image.drawPixel(x, y, 0);
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    image.drawPixel(x, y, rgbColor);
                }
            }
        }
        image.repaint();
    }
/** реализация кнопки */
    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fcGen.getInitialRange(range);
            drawFractal();
        }
    }
/** реализация кнопоки мышки */
    public class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            double mouseX = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
            double mouseY = FractalGenerator.getCoord(range.y, range.y + range.width, size, e.getY());
            System.out.println(mouseX + " "+ mouseY);
            fcGen.recenterAndZoomRange(range, mouseX, mouseY, 0.5);
            drawFractal();
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) {new FractalExplorer(500);}
}