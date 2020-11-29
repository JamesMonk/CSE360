package cse360project;


import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;


public class Main extends JFrame {
    private JPanel p;
    private JButton test;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem load, addAttendance, save, plot, about;

    public Main() {
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("CSE360 Final Project");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton test = new JButton("Test");
        menuBar = new JMenuBar();

        file = new JMenu("file");
        menuBar.add(file);

        about = new JMenuItem("about");
        about.addActionListener(new ListenToAbout());
        menuBar.add(about);

        ListenToLoad l = new ListenToLoad(frame);
        load = new JMenuItem("Load a Roster");
        load.addActionListener(l);
        addAttendance = new JMenuItem("Add Attendance");
        addAttendance.addActionListener(new ListenToAdd(frame, l));
        save = new JMenuItem("Save");
        save.addActionListener(new ListenToSave(frame, l));
        plot = new JMenuItem("Plot Data");
        plot.addActionListener(new ListenToPlot(l));

        file.add(load);
        file.add(addAttendance);
        file.add(save);
        file.add(plot);

        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}