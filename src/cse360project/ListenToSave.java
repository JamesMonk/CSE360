package cse360project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListenToSave implements ActionListener {

    private JFrame frame;
    private ListenToLoad l;
    private File file = new File("output.csv");

    private String str = "";

    public ListenToSave(JFrame frame, ListenToLoad l) {
        this.frame = frame;
        this.l = l;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            if (!file.exists()) {
                file.createNewFile();
            }
            for (int i = 0; i < l.getModel().getRowCount(); i++) {
                for (int j = 0; j < l.getModel().getColumnCount(); j++) {
                    str += l.getModel().getValueAt(i, j) + ",";
                }
                bw.write(str + "\n");
                str = "";
                bw.flush();

            }

            bw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}

