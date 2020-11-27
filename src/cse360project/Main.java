package cse360project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame implements ActionListener{
    private JPanel p;
    private JButton test;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem load, addAttendance, save, plot, about;
    public Main () {
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

        load = new JMenuItem("Load a Roster");
        load.addActionListener(new ListenToLoad(frame));
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
        private JFrame frame;

        public ListenToLoad(JFrame frame) {
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] column = {"One", "Two"};
            Object[][]data = {{1, 2}, {3, 4}, {5, 6}};

            JTable table = new JTable(data, column);
            JScrollPane pane = new JScrollPane(table);
            JPanel panel = new JPanel();
            panel.add(pane);
            this.frame.add(new JScrollPane(panel));
            this.frame.setVisible(true);
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

    public class ListenToAbout extends JFrame implements ActionListener {

        public void actionPerformed(ActionEvent e) {

//            System.out.println("About");
            JFrame frame = new JFrame("About");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JOptionPane.showMessageDialog(frame, "CSE360 Final Project\nNames:\nJames Monk\nCullen Walsh");

        }
    }
}
