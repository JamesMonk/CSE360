package cse360project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame implements ActionListener{
    private JPanel p;
    private JButton test;
    private JMenuBar menuBar;
    private JMenu file, about;
    private JMenuItem load, addAttendance, save, plot;
    public Main () {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("CSE360 Final Project");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton test = new JButton("Test");
        menuBar = new JMenuBar();

        file = new JMenu("file");
        menuBar.add(file);

        about = new JMenu("about");
        menuBar.add(about);

        load = new JMenuItem("Load a Roster");
        load.addActionListener(new ListenToLoad());
        addAttendance = new JMenuItem("Add Attendance");
        addAttendance.addActionListener(new ListenToAdd());
        save = new JMenuItem("Save");
        save.addActionListener(new ListenToSave());
        plot = new JMenuItem("Plot Data");
        plot.addActionListener(new ListenToPlot());

        file.add(load);
        file.add(addAttendance);
        file.add(save);
        file.add(plot);

        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        System.out.println("Done");
    }

    public void loadFileSelected() {
        System.out.println("Load File Selected");
    }

    public class ListenToLoad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Load");
        }
    }

    public class ListenToAdd implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Add Attendence");
        }
    }

    public class ListenToSave implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Save");
        }
    }

    public class ListenToPlot implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Plot");
        }
    }
}
