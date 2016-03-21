package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import management.Facade;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Controller {
    final static private Facade facade = new Facade();
    @FXML private TextField instances;
    @FXML private TextField jobs;
    @FXML private TextField processingTimeRange;
    @FXML private TextField weightRange;
    @FXML private TextField numberOfFiles;
    @FXML private LineChart<Number, Number> lineChart;


    @FXML protected void handleRunButtonAction(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        String instancesString = instances.getText();
        int instances = Integer.parseInt(instancesString);

        String jobsString = jobs.getText();
        int jobs = Integer.parseInt(jobsString);

        String processingTimeRangeString = processingTimeRange.getText();
        int processingTimeRange = Integer.parseInt(processingTimeRangeString);

        String weightRangeString = weightRange.getText();
        int weightRange = Integer.parseInt(weightRangeString);

        String numberOfFilesString = numberOfFiles.getText();
        int numberOfFiles = Integer.parseInt(numberOfFilesString);

        runFacade(numberOfFiles, instances, jobs, processingTimeRange, weightRange);
    }

    @FXML protected void generateGraph(ActionEvent event) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");

        //creating the chart
        lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        lineChart.getData().add(series);
    }

    public static void runFacade(int numberOfFiles, int instances, int jobs, int processingTimeRange, int weightRange) throws FileNotFoundException, UnsupportedEncodingException {
        facade.start(numberOfFiles, instances, jobs, processingTimeRange,weightRange, 1);
    }

}
