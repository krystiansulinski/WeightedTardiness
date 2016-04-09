package gui;

//import com.google.common.math.BigIntegerMath;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import management.Factory;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Controller {
    @FXML private TextField instances;
    @FXML private TextField jobs;
    @FXML private TextField processingTimeRange;
    @FXML private TextField weightRange;
    @FXML private TextField filename;
    @FXML private LineChart<Number, Number> graph;

    @FXML protected void handleCreateFileButtonAction(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        String instances = this.instances.getText();
        String jobs = this.jobs.getText();
        String processingTime = this.processingTimeRange.getText();
        String weightRangeString = this.weightRange.getText();

        int instanceCount = Integer.parseInt(instances);
        int jobCount = Integer.parseInt(jobs);
        int processingTimeRange = Integer.parseInt(processingTime);
        int weightRange = Integer.parseInt(weightRangeString);

        this.filename.setPromptText(Factory.getInputFileName(instanceCount, jobCount));

        String filepath = Factory.createInputFile(instanceCount, jobCount, processingTimeRange, weightRange);
        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution;
        solution = Factory.runBruteForceSearch(filepath, instanceCount, jobCount);
        Factory.createOutputFile(filepath, solution);

        ObservableList<XYChart.Series<Number, Number>> lineChartData = FXCollections.observableArrayList();
        ArrayList<LineChart.Series<Number, Number>> series = new ArrayList<>(solution.size());

        int i = 0;
        for (Map.Entry<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> entry : solution.entrySet()) {
            LineChart.Series<Number, Number> lineChart = new LineChart.Series<>();
            for (Integer value : entry.getValue()) {
                lineChart.getData().add(new XYChart.Data<>(i++, value));
            }
            series.add(lineChart);
        }

        int j = 0;
        for (LineChart.Series<Number, Number> one : series){
            one.setName("instance " + j++);
            lineChartData.add(one);
        }
        graph.setData(lineChartData);
        graph.createSymbolsProperty();
    }
}
