package cse360project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ListenToLoad implements ActionListener {
    private final JFrame frame;
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
        String[] column = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};

        //Add the columns
        for (String element : column) {
            model.addColumn(element);
        }

        //Get the file from the user
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith("csv")) {
                String oneLine = "";
                depth = 0;
                int ii = 0;

                try {
                    //Read in the file to the BufferedReader
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                    //Get the depth (number of lines) of the csv file
                    while (reader.readLine() != null) {
                        depth++;
                    }

                    //Reset the BufferedReader
                    reader = new BufferedReader(new FileReader(selectedFile));

                    //Get each line, split at ",", add each token to newRow
                    while ((oneLine = reader.readLine()) != null) {
                        String[] newRow = new String[6];
                        ii = 0;
                        for (String token : oneLine.split(",")) {
                            newRow[ii] = token;
                            ii++;
                        }
                        //Add newRow as a new row to model
                        model.addRow(newRow);
                    }

                    //Create a table out of the model
                    table = new JTable(model);

                    //Add that table to a JScrollPane
                    JScrollPane scrollPane = new JScrollPane(table);

                    //Add scrollbars to the scrollPane
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                    //Create a new panel and add the scrollPane to it
                    JPanel panel = new JPanel();
                    panel.add(scrollPane);

                    //Add the new panel to the frame
                    this.frame.add(panel);
                    this.frame.setVisible(true);
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }

    public int getDepth() {
        return depth;
    }

    public DefaultTableModel getModel() {
        return model;
    }


}
