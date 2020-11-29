package cse360project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenToAbout extends JFrame implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("About");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "CSE360 Final Project\nNames:\nJames Monk\nCullen Walsh");

    }
}
