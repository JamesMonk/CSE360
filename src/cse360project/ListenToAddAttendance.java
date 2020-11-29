package cse360project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListenToAddAttendance implements ActionListener {
    private JFrame frame;
    private ListenToLoad l;

    public ListenToAddAttendance(JFrame frame, ListenToLoad l) {
        this.frame = frame;
        this.l = l;
    }


    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        String oneLine = "";
        String asurite = "";
        int depth = 0, ii = 0, i = 0;
        String[][] newData;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith("csv")) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    while ((oneLine = reader.readLine()) != null) {
                        depth++;
                    }
                    String[] unused = new String[depth];
                    newData = new String[depth][2];
                    reader = new BufferedReader(new FileReader(selectedFile));
                    while ((oneLine = reader.readLine()) != null) {
                        String[] newRow = new String[2];
                        ii = 0;
                        for (String token : oneLine.split(",")) {
                            newRow[ii] = token;
                            ii++;
                        }
                        newData[i] = newRow;
                        unused[i] = newRow[0];

                        i++;
                    }
                    i = 0;
                    if (l.getModel().getColumnCount() == 6) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
                        LocalDateTime now = LocalDateTime.now();
                        l.getModel().addColumn(dtf.format(now));
                    }
                    for (int index = 0; index < l.getDepth(); index++) {
                        asurite = "" + l.getModel().getValueAt(index, 5);
                        for (int index2 = 0; index2 < depth; index2++) {
                            if (asurite.equals(newData[index2][0])) {
                                if (l.getModel().getValueAt(index, 6) != null) {
                                    l.getModel().setValueAt(Integer.parseInt(newData[index2][1]) + Integer.parseInt(("" + l.getModel().getValueAt(index, 6))), index, 6);
                                    unused[index2] = null;
                                }
                                else {
                                    l.getModel().setValueAt(newData[index2][1], index, 6);
                                    unused[index2] = null;
                                }
                            }
                        }
                    }
                    String ret = "";
                    int count1 = 0, count2 = 0, a = 0;
                    for (String element : unused) {
                        try {
                            if (!element.equals(null)) {
                                ret = ret + element + " connected for " + newData[a][1] + " minutes\n";
                                count1++;

                            }
                        } catch (NullPointerException exception) {
                            count2++;
                        }
                        a++;
                    }
                    JFrame newFrame = new JFrame("");
                    JOptionPane.showMessageDialog(newFrame, "Data loaded from " + count2 + " users in the roster.\n" + count1 + " additional attendees were found:" +
                            "\n" + ret + "");
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }
}
