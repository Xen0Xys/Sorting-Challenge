package fr.xen0xys.ui.views;

import fr.xen0xys.models.Computer;
import fr.xen0xys.models.Rod;
import fr.xen0xys.models.algorithms.AlgorithmType;
import fr.xen0xys.ui.GameWindow;
import fr.xen0xys.ui.utils.Runner;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmView extends JPanel {

    private final GameWindow window;
    private final List<Rod> rods;
    private final Runner graphicRunner;
    private final Computer computer;

    public AlgorithmView(GameWindow window, int rodCount) {
        this.window = window;
        this.graphicRunner = new Runner(this::repaint, 1000, false);
        this.initPanel();
        // Init rods
        this.rods = new ArrayList<>();
        this.initRods(rodCount);
        this.computer = new Computer(AlgorithmType.INSERTION_SORT, this.rods);
        // Start runners
        this.graphicRunner.start();
        this.computer.start();
    }

    private void initPanel(){
        this.setBackground(Color.BLACK);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width - 50, screenSize.height - 150);
        this.setPreferredSize(this.getSize());
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.window.setLocation(25, 75);
    }

    private void initRods(int rodCount){
        for(int i = 0; i < rodCount; i++)
            this.rods.add(new Rod(rodCount - i));
        Collections.shuffle(this.rods);
    }

    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Roboto", Font.PLAIN, 18));
        g2d.drawString("FPS : %d    TPS : %d".formatted(this.graphicRunner.getCurrentAps(), this.computer.getRunner().getCurrentAps()), 15, 25);
        this.drawRods(g2d);
    }

    private void drawRods(Graphics2D g) {
        double spaceWidth = 2;
        double spaces = this.rods.size() * spaceWidth;
        double rodWidth = (this.getWidth() - spaces) / this.rods.size();
        double maxRodHeight = this.getHeight() - 50;
        for(int i = 0; i < this.rods.size(); i++){
            Rod rod = this.rods.get(i);
            double x = i * spaceWidth + i * rodWidth;
            double height = maxRodHeight * rod.getValue() / this.rods.size();
            double y = this.getHeight() - height;
            g.setColor(rod.getColor());
            g.fill(new Rectangle2D.Double(x, y, rodWidth, height));
        }
    }
}