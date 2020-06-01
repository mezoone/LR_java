package lr5;

import lr4.FractalGenerator;
import lr4.JImageDisplay;
import lr4.Mandelbrot;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;


/**
 *
 * В данном классе добавлено два новых фрактала, кнопка сохранения и выпадающий список,
 * а также улучшена реализация MouseHandler
 */

public class FractalExplorer {
    private int size;
    private JImageDisplay image;
    private FractalGenerator fcGen;
    private Rectangle2D.Double range;
    private JComboBox<Object> box;
    private JButton btnReset;
    private JButton btnSave;

    public FractalExplorer(int size) {
        this.size = size;
        this.fcGen = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        fcGen.getInitialRange(this.range);
        createAndShowGUI();
        drawFractal();

    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractals");
        image = new JImageDisplay(size, size);
        btnReset = new JButton("Reset");
        btnSave = new JButton("Save");
        //btnReset.setActionCommand("Reset");
        //btnSave.setActionCommand("Save");
        JLabel label = new JLabel("Fractal: "); // добавление подписи
        box = new JComboBox<>();// сoздание выпадающего списка
        //box.setActionCommand("box");
        box.addItem(new Mandelbrot());
        box.addItem(new Tricorn());
        box.addItem(new BurningShip());

        JPanel panelBox = new JPanel();
        panelBox.add(label);
        panelBox.add(box);

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnReset);
        panelBtn.add(btnSave);

        frame.add(image, BorderLayout.CENTER);
        frame.add(panelBtn, BorderLayout.SOUTH);
        frame.add(panelBox, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionHandler handler = new ActionHandler();
        btnReset.addActionListener(handler);
        btnSave.addActionListener(handler);
        box.addActionListener(handler);
        image.addMouseListener(new MouseHandler());

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

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
                fcGen.recenterAndZoomRange(range, mouseX, mouseY, 1.5);
                drawFractal();
            }
        }
        public void mousePressed(MouseEvent e) { }
        public void mouseReleased(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
    }

    public static void main(String[] msi) {
        new FractalExplorer(600);
    }
}