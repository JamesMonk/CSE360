package cse360project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ListenToLoad implements ActionListener {
    private JFrame frame;
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

        for (String element : column) {
            model.addColumn(element);
        }
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith("csv")) {
                String oneLine = "";
                depth = 0;
                int ii = 0;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    while ((oneLine = reader.readLine()) != null) {
                        depth++;
                    }
                    reader = new BufferedReader(new FileReader(selectedFile));
                    while ((oneLine = reader.readLine()) != null) {
                        String[] newRow = new String[6];
                        ii = 0;
                        for (String token : oneLine.split(",")) {
                            newRow[ii] = token;
                            ii++;
                        }
                        model.addRow(newRow);
                    }
                    table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    JPanel panel = new JPanel();
                    panel.add(scrollPane);
                    this.frame.add(panel);
                    this.frame.setVisible(true);
                } catch (FileNotFoundException ee) {
                    ee.printStackTrace();
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
