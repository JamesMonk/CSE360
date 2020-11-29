package cse360project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ListenToAddAttendance implements ActionListener {
    private final JFrame frame;
    private final ListenToLoad l;

    public ListenToAddAttendance(JFrame frame, ListenToLoad l) {
        this.frame = frame;
        this.l = l;
    }


    public void actionPerformed(ActionEvent e) {
        //Get the date from the user
        String date = JOptionPane.showInputDialog(null, "Enter the Date:");

        //Get the file from the user
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        String oneLine = "";
        int depth = 0, ii = 0, i = 0;
        String[][] newData;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith("csv")) {
                try {
                    //Read in the file to the BufferedReader
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                    //Get the depth (number of lines) of the csv file
                    while ((oneLine = reader.readLine()) != null) {
                        depth++;
                    }
                    String[] unused = new String[depth];
                    newData = new String[depth][2];

                    //Reset the BufferedReader
                    reader = new BufferedReader(new FileReader(selectedFile));

                    //Get each line, split at ",", add each token to newRow
                    while ((oneLine = reader.readLine()) != null) {
                        String[] newRow = new String[2];
                        ii = 0;
                        for (String token : oneLine.split(",")) {
                            newRow[ii] = token;
                            ii++;
                        }

                        //Add newRow as a new row to newData, copy into unused
                        newData[i] = newRow;
                        unused[i] = newRow[0];
                        i++;
                    }


                    String asurite = "";

                    //Add a new column to the table for the attendance
                    if (l.getModel().getColumnCount() == 6) {
                        l.getModel().addColumn(date);
                    }

                    /**
                     * Go through each row of the table and look for ASURITE matches in the new csv file
                     *  - If a match is found, either put attendance time into the new column,
                     *    or if there already is a value there, merge the values by adding them together
                     *  - set the indices that had matches in unused to null
                     */
                    for (int index = 0; index < l.getDepth(); index++) {
                        asurite = "" + l.getModel().getValueAt(index, 5);
                        for (int index2 = 0; index2 < depth; index2++) {
                            if (asurite.equals(newData[index2][0])) {
                                if (l.getModel().getValueAt(index, 6) != null) {
                                    l.getModel().setValueAt(Integer.parseInt(newData[index2][1]) + Integer.parseInt(("" + l.getModel().getValueAt(index, 6))), index, 6);
                                    unused[index2] = null;
                                } else {
                                    l.getModel().setValueAt(newData[index2][1], index, 6);
                                    unused[index2] = null;
                                }
                            }
                        }
                    }
                    String ret = "";
                    int count1 = 0, count2 = 0, row = 0;

                    /**
                     * Go through unused and look for elements that aren't null
                     * If found, include it in the dialogue box
                     */
                    for (String element : unused) {
                        try {
                            if (!element.equals(null)) {
                                ret = ret + element + " connected for " + newData[row][1] + " minutes\n";
                                count1++;

                            }
                        } catch (NullPointerException exception) {
                            count2++;
                        }
                        row++;
                    }

                    //Create the dialogue box and display just gathered
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
