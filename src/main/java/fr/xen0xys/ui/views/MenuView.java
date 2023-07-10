package fr.xen0xys.ui.views;

import fr.xen0xys.ui.GameWindow;

import javax.swing.*;

public class MenuView extends JPanel {

    private final GameWindow frame;

    private final JButton startButton = new JButton("Start");

    public MenuView(GameWindow frame) {
        this.frame = frame;
        this.initPanel();
        this.initComponents();
    }

    private void initComponents() {
        this.startButton.setBounds(50, 250, 400, 50);
        this.startButton.addActionListener(e -> {
            this.frame.loadView(new AlgorithmView(this.frame, 25));
        });
        this.add(this.startButton);
    }

    private void initPanel(){
        this.setLayout(null);
        this.setSize(500, 500);
        this.setPreferredSize(this.getSize());
    }
}
