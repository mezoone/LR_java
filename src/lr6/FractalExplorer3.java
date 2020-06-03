package lr6;

import lr4.FractalGenerator;
import lr4.JImageDisplay;
import lr4.Mandelbrot;
import lr5.BurningShip;
import lr5.Tricorn;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


/**
 *
 * В данном классе добавлена многопоточная реализация
 */

public class FractalExplorer3 {
    private int size;
    private JImageDisplay image;
    private FractalGenerator fcGen;
    private Rectangle2D.Double range;
    private JComboBox<Object> box;
    private JButton btnReset;
    private JButton btnSave;
    private JFrame frame;
    private int rowsremaining;

    public FractalExplorer3(int size) {
        this.size = size;
        this.fcGen = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        fcGen.getInitialRange(this.range);
        createAndShowGUI();

    }

    public void createAndShowGUI() {
        frame = new JFrame("Fractals");
        image = new JImageDisplay(size, size);
        btnReset = new JButton("Reset");
        btnSave = new JButton("Save");
        JLabel label = new JLabel("Fractal: ");
        box = new JComboBox<>();
        box.addItem(new Mandelbrot());
        box.addItem(new Tricorn());
        box.addItem(new BurningShip());

        JPanel panelBox = new JPanel();
        panelBox.add(label);
        panelBox.add(box);

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnReset);
        panelBtn.add(btnSave);

        ActionHandler handler = new ActionHandler();
        btnReset.addActionListener(handler);
        btnSave.addActionListener(handler);
        box.addActionListener(handler);
        image.addMouseListener(new MouseHandler());

        frame.add(image, BorderLayout.CENTER);
        frame.add(panelBtn, BorderLayout.SOUTH);
        frame.add(panelBox, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        drawFractal();
    }

    private void drawFractal() {
        enableUI(FALSE);
        rowsremaining = size;
        for (int y = 0; y < size; y++) {
            new FractalWorker(y).execute();
        }
    }
/**включать или отключать кнопки с выпадающим списком в пользовательском интерфейсе на основе указанного параметра*/
    public void enableUI(boolean val) {
        if (val == TRUE) {
            frame.setEnabled(TRUE);
        } else {
            frame.setEnabled(FALSE);
        }
    }

    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnReset) {
                fcGen.getInitialRange(range);
                drawFractal();
            } else if (e.getSource() == btnSave) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "PNG");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(image.getBufferedImage(), "png", new File(chooser.getSelectedFile() + ".PNG"));
                    } catch (IOException ex) {
                        System.out.println("Failed to save image!");
                    }
                } else {
                    System.out.println("No file chosen!");
                }
            } else if (e.getSource() == box) {
                fcGen = (FractalGenerator) box.getSelectedItem();
                fcGen.getInitialRange(range);
                drawFractal();
            }
        }
    }

    public class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            double mouseX = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
            double mouseY = FractalGenerator.getCoord(range.y, range.y + range.width, size, e.getY());
            System.out.println(mouseX + " " + mouseY);
            if (e.getButton() == MouseEvent.BUTTON1) {
                fcGen.recenterAndZoomRange(range, mouseX, mouseY, 0.5);
                drawFractal();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                fcGen.recenterAndZoomRange(range, mouseX, mouseY, 2);
                drawFractal();
            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
/**FractalWorker отвеает за вычисление значений цвета для одной строки фрактала*/
    private class FractalWorker extends SwingWorker<Object, Object> {
        int y;
        int[] array;

        FractalWorker(int y) {
            this.y = y;
        }
/**Метод doInBackground() вызывается в фоновом потоке и отвечает за выполнение длительной задачи*/
        @Override
        protected Object doInBackground() throws Exception {
            array = new int[size];
            for (int x = 0; x < size; x++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.width, size, y);
                double numIters = fcGen.numIterations(xCoord, yCoord);
                if (numIters == -1) array[x] = 0;
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    array[x] = rgbColor;
                }
            }
            return null;
        }
/**Метод done() вызывается, когда фоновая задача завершена*/
        protected void done() {
            for (int x = 0; x < size; x++) {
                image.drawPixel(x, y, array[x]);
            }
            image.repaint(0, 0, y, size, 1);
            rowsremaining--;
            if (rowsremaining == 0) {
                enableUI(TRUE);
            }
            super.done();
        }
    }

    public static void main(String[] args) {
        new FractalExplorer3(600);
    }
}