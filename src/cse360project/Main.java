package cse360project;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private final JMenuBar menuBar;
    private final JMenu file;
    private final JMenuItem load;
    private final JMenuItem addAttendance;
    private final JMenuItem save;
    private final JMenuItem plot;
    private final JMenuItem about;

    public Main() {
        JFrame frame = new JFrame("CSE360 Final Project");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the Menu bar
        menuBar = new JMenuBar();

        //Create the "file" menu item
        file = new JMenu("file");
        menuBar.add(file);

        //Create the "about" menu item
        about = new JMenuItem("about");
        about.addActionListener(new ListenToAbout());
        menuBar.add(about);

        //Create one single ListenToLoad object to pass to the different action listeners
        ListenToLoad l = new ListenToLoad(frame);

        //Create submenu items for "file" menu option and add their action listeners
        load = new JMenuItem("Load a Roster");
        load.addActionListener(l);
        addAttendance = new JMenuItem("Add Attendance");
        addAttendance.addActionListener(new ListenToAddAttendance(frame, l));
        save = new JMenuItem("Save");
        save.addActionListener(new ListenToSave(frame, l));
        plot = new JMenuItem("Plot Data");
        plot.addActionListener(new ListenToPlot(l));

        //Add the submenu options to "file"
        file.add(load);
        file.add(addAttendance);
        file.add(save);
        file.add(plot);

        //Ad the menu bar to the frame
        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}