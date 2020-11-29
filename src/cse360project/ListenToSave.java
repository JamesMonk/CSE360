package cse360project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListenToSave implements ActionListener {

    private final JFrame frame;
    private final ListenToLoad l;
    private final File file = new File("output.csv");

    private String str = "";

    public ListenToSave(JFrame frame, ListenToLoad l) {
        this.frame = frame;
        this.l = l;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            //BufferedWriter to write to the output file "output.csv"
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            //If "output.csv" doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
            }

            /**
             * Write to the file
             *  - copy an entire line to str
             *  - write str to the file
             *  - reset str to ""
             *  - flush the buffer
             *  - repeat until there are no more lines to read
             */
            for (int i = 0; i < l.getModel().getRowCount(); i++) {
                for (int j = 0; j < l.getModel().getColumnCount(); j++) {
                    str += l.getModel().getValueAt(i, j) + ",";
                }
                bw.write(str + "\n");
                str = "";
                bw.flush();

            }

            //Close the buffer at the end
            bw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}

