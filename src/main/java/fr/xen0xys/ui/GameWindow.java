package fr.xen0xys.ui;

import fr.xen0xys.models.Computer;
import fr.xen0xys.models.Rod;
import fr.xen0xys.models.algorithms.AlgorithmType;
import fr.xen0xys.ui.utils.Runner;
import fr.xen0xys.ui.views.AlgorithmPanel;
import fr.xen0xys.ui.views.MenuPanel;
import fr.xen0xys.utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameWindow extends JFrame implements ComponentListener {

    private final MenuPanel menuPanel;
    private final AlgorithmPanel algorithmPanel;
    private final Runner graphicRunner;
    private Computer computer;
    private final List<Rod> rods;

    public static final Font font = new Font("Roboto", Font.PLAIN, 18);

    public GameWindow() {
        super("Sorting Challenge");
        this.rods = new ArrayList<>();
        // Init panels and window
        this.menuPanel = new MenuPanel(this);
        this.algorithmPanel = new AlgorithmPanel(this, this.rods);
        this.graphicRunner = new Runner(this.algorithmPanel::repaint, 60, false);
        this.initWindow();
        this.setVisible(true);
        this.generateRods(100);
        this.graphicRunner.start();
    }

    private void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width - 50;
        int height = screenSize.height - 300;
        this.setLocation(25, 75);
        this.resizePanels(width, height);
        this.setSize(width + 15, height + 39);
        this.setMinimumSize(new Dimension(1400, 675));
        // Add panels to window
        this.setLayout(null);
        this.add(this.menuPanel);
        this.add(this.algorithmPanel);
        this.addComponentListener(this);
    }

    public Tuple<Integer, Integer> getRunnersAps() {
        if (Objects.isNull(this.computer))
            return new Tuple<>(this.graphicRunner.getCurrentAps(), -1);
        return new Tuple<>(this.graphicRunner.getCurrentAps(), this.computer.getRunner().getCurrentAps());
    }

    private void resizePanels(int width, int height) {
        int algorithmPanelHeight = (int) (height * 0.85);
        int menuPanelHeight = height - algorithmPanelHeight;
        this.algorithmPanel.setSize(width, algorithmPanelHeight);
        this.algorithmPanel.setLocation(0, menuPanelHeight);
        this.menuPanel.setSize(width, menuPanelHeight);
        this.menuPanel.setLocation(0, 0);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.resizePanels(this.getWidth() - 15, this.getHeight() - 39);
        this.menuPanel.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    private void generateRods(int rodCount) {
        this.rods.clear();
        for (int i = 0; i < rodCount; i++)
            this.rods.add(new Rod(rodCount - i));
        this.shuffle();
    }

    public void stopComputer() {
        if(Objects.isNull(this.computer))
            return;
        this.computer.stop();
        this.computer = null;
        this.rods.forEach(rod -> rod.setColor(Color.WHITE));
    }

    public void startComputer(AlgorithmType algorithmType, int tps) {
        if(Objects.nonNull(this.computer))
            this.stopComputer();
        this.computer = new Computer(algorithmType, this.rods, tps);
        this.computer.start();
    }

    public void setFps(int fps) {
        this.graphicRunner.setAps(fps);
    }

    public void setTps(int tps){
        if(Objects.nonNull(this.computer))
            this.computer.getRunner().setAps(tps);
    }

    public void setRodCount(int rodCount) {
        if(Objects.nonNull(this.computer))
            this.stopComputer();
        this.generateRods(rodCount);
    }

    public void shuffle(){
        this.stopComputer();
        Collections.shuffle(this.rods);
    }
}
