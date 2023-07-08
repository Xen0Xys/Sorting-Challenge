package fr.xen0xys.ui;

import fr.xen0xys.ui.views.MenuView;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        super("Sorting Challenge");
        this.initWindow();
        this.loadView(new MenuView(this));
        this.setVisible(true);
    }

    private void initWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadView(JPanel panel){
        this.setContentPane(panel);
        this.revalidate();
        this.setSize(panel.getPreferredSize());
        this.pack();
    }
}
