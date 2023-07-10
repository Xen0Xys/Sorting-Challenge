package fr.xen0xys.ui.views;

import fr.xen0xys.models.Rod;
import fr.xen0xys.ui.GameWindow;
import fr.xen0xys.utils.Tuple;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class AlgorithmPanel extends JPanel {

    private final GameWindow window;
    private final List<Rod> rods;

    private final Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, 0, 0);

    public AlgorithmPanel(GameWindow window, List<Rod> rods) {
        this.window = window;
        this.rods = rods;
        this.initPanel();
    }

    private void initPanel(){
        this.setBackground(Color.BLACK);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width - 50, screenSize.height - 150);
        this.setPreferredSize(this.getSize());
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameWindow.font);
        Tuple<Integer, Integer> runnersAps = this.window.getRunnersAps();
        g2d.drawString("FPS : %d    TPS : %d".formatted(runnersAps.first(), runnersAps.second()), 10, 25);
        this.drawRods(g2d, this.rods);
    }

    private void drawRods(Graphics2D g, List<Rod> rods) {
        if(rods.isEmpty()) return;
        double spaceWidth = 1;
        double spaces = rods.size() * spaceWidth;
        double rodWidth = (this.getWidth() - spaces) / rods.size();
        double maxRodHeight = this.getHeight() - 50;
        for(int i = 0; i < rods.size(); i++){
            Rod rod = rods.get(i);
            double x = i * spaceWidth + i * rodWidth;
            double height = maxRodHeight * rod.getValue() / rods.size();
            double y = this.getHeight() - height;
            g.setColor(rod.getColor());
            rectangle2D.setRect(x, y, rodWidth, height);
            g.fill(rectangle2D);
        }
    }
}
