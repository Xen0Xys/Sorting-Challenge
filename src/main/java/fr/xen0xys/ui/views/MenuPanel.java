package fr.xen0xys.ui.views;

import fr.xen0xys.models.algorithms.AlgorithmType;
import fr.xen0xys.ui.GameWindow;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final GameWindow frame;

    private final JLabel rodCountLabel = new JLabel("Rod count : 25");
    private final JSlider rodCountSlider = new JSlider(10, 1000, 25);
    private final JLabel fpsLabel = new JLabel("FPS : 60");
    private final JSlider fpsSlider = new JSlider(10, 1000, 100);
    private final JLabel tpsLabel = new JLabel("TPS : 20");
    private final JSlider tpsSlider = new JSlider(1, 10000, 20);
    private final JButton stopButton = new JButton("Stop");
    private final JButton shuffleButton = new JButton("Shuffle");

    public MenuPanel(GameWindow frame) {
        this.frame = frame;
        this.initPanel();
        this.initComponents();
    }

    private void initComponents() {
        this.rodCountLabel.setBounds(10, 10, 150, 30);
        this.rodCountLabel.setFont(GameWindow.font);
        this.rodCountLabel.setBackground(Color.BLACK);
        this.rodCountLabel.setForeground(Color.WHITE);
        this.add(this.rodCountLabel);
        this.rodCountSlider.setBounds(10, 50, 150, 50);
        this.rodCountSlider.setMajorTickSpacing(500);
        this.rodCountSlider.setMinorTickSpacing(100);
        this.rodCountSlider.setPaintTicks(true);
        this.rodCountSlider.setPaintLabels(true);
        this.rodCountSlider.setFont(GameWindow.font);
        this.rodCountSlider.setBackground(Color.BLACK);
        this.rodCountSlider.setForeground(Color.WHITE);
        this.rodCountSlider.addChangeListener(e -> {
            this.rodCountLabel.setText("Rod count : " + this.rodCountSlider.getValue());
            this.frame.setRodCount(this.rodCountSlider.getValue());
        });
        this.add(this.rodCountSlider);

        this.fpsLabel.setBounds(210, 10, 150, 30);
        this.fpsLabel.setFont(GameWindow.font);
        this.fpsLabel.setBackground(Color.BLACK);
        this.fpsLabel.setForeground(Color.WHITE);
        this.add(this.fpsLabel);
        this.fpsSlider.setBounds(210, 50, 150, 50);
        this.fpsSlider.setMajorTickSpacing(500);
        this.fpsSlider.setMinorTickSpacing(100);
        this.fpsSlider.setPaintTicks(true);
        this.fpsSlider.setPaintLabels(true);
        this.fpsSlider.setFont(GameWindow.font);
        this.fpsSlider.setBackground(Color.BLACK);
        this.fpsSlider.setForeground(Color.WHITE);
        this.fpsSlider.addChangeListener(e -> {
            this.fpsLabel.setText("FPS : " + this.fpsSlider.getValue());
            this.frame.setFps(this.fpsSlider.getValue());
        });
        this.add(this.fpsSlider);

        this.tpsLabel.setBounds(410, 10, 150, 30);
        this.tpsLabel.setFont(GameWindow.font);
        this.tpsLabel.setBackground(Color.BLACK);
        this.tpsLabel.setForeground(Color.WHITE);
        this.add(this.tpsLabel);
        this.tpsSlider.setBounds(410, 50, 150, 50);
        this.tpsSlider.setMajorTickSpacing(5000);
        this.tpsSlider.setMinorTickSpacing(1000);
        this.tpsSlider.setPaintTicks(true);
        this.tpsSlider.setPaintLabels(true);
        this.tpsSlider.setFont(GameWindow.font);
        this.tpsSlider.setBackground(Color.BLACK);
        this.tpsSlider.setForeground(Color.WHITE);
        this.tpsSlider.addChangeListener(e -> {
            this.tpsLabel.setText("TPS : " + this.tpsSlider.getValue());
            this.frame.setTps(this.tpsSlider.getValue());
        });
        this.add(this.tpsSlider);

        this.stopButton.setBounds(610, 50, 150, 30);
        this.stopButton.setFont(GameWindow.font);
        this.stopButton.setBackground(Color.BLACK);
        this.stopButton.setForeground(Color.WHITE);
        this.stopButton.addActionListener(e -> {
            this.frame.stopComputer();
        });
        this.add(this.stopButton);

        this.shuffleButton.setBounds(810, 50, 150, 30);
        this.shuffleButton.setFont(GameWindow.font);
        this.shuffleButton.setBackground(Color.BLACK);
        this.shuffleButton.setForeground(Color.WHITE);
        this.shuffleButton.addActionListener(e -> {
            this.frame.shuffle();
        });
        this.add(this.shuffleButton);

        for(AlgorithmType algorithmType : AlgorithmType.values()){
            JButton button = new JButton(algorithmType.getDisplayName());
            button.setBounds(610 + (algorithmType.ordinal() * 200), 10, 150, 30);
            button.setFont(GameWindow.font);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> {
                this.frame.startComputer(algorithmType, this.tpsSlider.getValue());
            });
            this.add(button);
        }
    }

    private void initPanel(){
        this.setBackground(Color.BLACK);
        this.setLayout(null);
    }
}
