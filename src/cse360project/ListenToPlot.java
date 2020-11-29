package cse360project;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenToPlot extends JFrame implements ActionListener {
    private JFrame frame;
    private XYDataset dataset;
    private final DefaultTableModel model;
    private final ListenToLoad l;

    public ListenToPlot(ListenToLoad l) {
        this.l = l;
        model = l.getModel();
    }


    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("Plot");
        this.dataset = createDataset();
        JFreeChart chart = ChartFactory.createScatterPlot("Plot", "Percentage of Attendance", "Number of Studens", dataset, PlotOrientation.VERTICAL, false, false, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));

        ChartPanel panel = new ChartPanel(chart);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private XYDataset createDataset() {
        //Dataset to return
        XYSeriesCollection dataset = new XYSeriesCollection();

        //Series to add the points to and
        XYSeries series = new XYSeries("");

        //Integers to keep track of number of people in each category (0% attendance, 10% attendance, etc.)
        int zero = 0, ten = 0, twenty = 0, thirty = 0, forty = 0, fifty = 0, sixty = 0, seventy = 0, eighty = 0, ninety = 0, hundred = 0;

        //Go through table, check each person's attendance and see what category they fall into. Increment the integers accordingly
        for (int i = 0; i < l.getDepth(); i++) {
            if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 3.75) {
                zero++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 7.5 + 3.75) {
                ten++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 15 + 3.75) {
                twenty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 22.5 + 3.75) {
                thirty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 22.5 + 3.75) {
                thirty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 30 + 3.75) {
                forty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 37.5 + 3.75) {
                fifty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 45 + 3.75) {
                sixty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 52.5 + 3.75) {
                seventy++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 60 + 3.75) {
                eighty++;
            } else if (Integer.parseInt("" + l.getModel().getValueAt(i, 6)) < 67.5 + 3.75) {
                ninety++;
            } else {
                hundred++;
            }

        }

        //Add the points to the graph
        series.add(0, zero);
        series.add(10, ten);
        series.add(20, twenty);
        series.add(30, thirty);
        series.add(40, forty);
        series.add(50, fifty);
        series.add(60, sixty);
        series.add(70, seventy);
        series.add(80, eighty);
        series.add(90, ninety);
        series.add(100, hundred);

        //Add the series to the dataset and return the dataset
        dataset.addSeries(series);
        return dataset;
    }
}
