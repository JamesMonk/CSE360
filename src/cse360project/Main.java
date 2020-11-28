package cse360project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.util.List;





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

        ListenToLoad l = new ListenToLoad(frame);
        load = new JMenuItem("Load a Roster");
        load.addActionListener(l);
        addAttendance = new JMenuItem("Add Attendance");
        addAttendance.addActionListener(new ListenToAdd(frame, l));
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
//        private Object[][] data;
        private int depth;
        private JTable table;
        private DefaultTableModel model;

        public ListenToLoad(JFrame frame) {
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            model = new DefaultTableModel();
            Object[] column = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};

            for (Object element : column) {
                model.addColumn(element);
            }
            int pos = 0;
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile.getName().endsWith("csv")){
                String delimiter = ",";
                     String oneLine = "";
                        depth = 0;

                        int i = 0, ii = 0;
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                            while((oneLine=reader.readLine()) != null) {
                                depth ++;
                            }
//                            data = new Object[depth][6];
                            reader = new BufferedReader(new FileReader(selectedFile));
                            while((oneLine = reader.readLine()) != null) {
                                Object [] newRow = new Object[6];
                                ii = 0;
                                for (String token: oneLine.split(",")) {
                                    newRow[ii] = token;
                                    ii++;
                                }
                                model.addRow(newRow);
//                                data[i] = newRow;
                                i++;
                            }

                            table = new JTable(model);
//                            JScrollPane pane = new JScrollPane(table);
//                            JPanel panel = new JPanel();
//                            panel.add(pane);
//                            TableColumn newColumn = new TableColumn("Attendence");
//                            table.addColumn(newColumn);
                            this.frame.add(new JScrollPane(table));
//                            this.frame.add(pane);
                            this.frame.setVisible(true);


                        }
                        catch (FileNotFoundException ee) {
                            ee.printStackTrace();
                        }
                        catch (IOException ee) {
                            ee.printStackTrace();
                        }
                    }
                }
        }

//        public Object[][] getData() {
//            return data;
//        }
        public int getDepth() {
            return depth;
        }
        public JTable getTable() {
            return table;
        }
        public DefaultTableModel getModel() {
            return model;
        }
    }

    public class ListenToAdd implements ActionListener {
        private JFrame frame;
        private ListenToLoad l;

        public ListenToAdd(JFrame frame, ListenToLoad l) {
            this.frame = frame;
            this.l = l;
        }


        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            String oneLine = "";
            Object asurite = "";
            int depth = 0, ii = 0, i = 0;

//            Object[][] data = l.getData();
            Object[][] newData;
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile.getName().endsWith("csv")) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        while ((oneLine = reader.readLine()) != null) {
                            depth++;
                        }
                        newData = new Object[depth][2];
                        reader = new BufferedReader(new FileReader(selectedFile));
                        while ((oneLine = reader.readLine()) != null) {
                            Object[] newRow = new Object[2];
                            ii = 0;
                            for (String token : oneLine.split(",")) {
                                newRow[ii] = token;
                                ii++;
                            }
                            newData[i] = newRow;
                            i++;
                        }
                        i = 0;
                        Object [] unused = new Object[depth];
                        for (Object element : newData) {
                            unused[i] = element;
                        }
                        if(l.getModel().getColumnCount() == 6) {
                            l.getModel().addColumn("Nov 28");
                        }
                        for (int index = 0; index < l.getDepth(); index++) {
                            asurite = l.getModel().getValueAt(index, 5);
                            for (int index2 = 0; index2 < depth; index2++) {
                                if (asurite.equals(newData[index2][0])) {
                                    l.getModel().setValueAt(newData[index2][1], index, 6);
                                }
                            }
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioexecption) {
                        ioexecption.printStackTrace();
                    }
                }
            }
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
