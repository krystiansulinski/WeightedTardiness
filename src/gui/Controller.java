package gui;

//import com.google.common.math.BigIntegerMath;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import utilities.Factory;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Controller {
    //tab1
    @FXML private TextField instances;
    @FXML private TextField jobs;
    @FXML private TextField processingTimeRange;
    @FXML private TextField weightRange;
    @FXML private TextField filenameBFS;
    @FXML private LineChart<Number, Number> graph1;

    //tab2
    @FXML private TextField instancesSA;
    @FXML private TextField jobsSA;
    @FXML private TextField temperature;
    @FXML private TextField coolingRate;
    @FXML private TextField filepathSA;
    @FXML private LineChart<Number, Number> graph2;

    //tab3
    @FXML private TextField instancesTS;
    @FXML private TextField jobsTS;
    @FXML private TextField tabuLength;
    @FXML private TextField numberOfIterations;
    @FXML private TextField filepathTS;
    @FXML private LineChart<Number, Number> graph3;

    @FXML protected void handleCreateFileButtonAction(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        String instances = this.instances.getText();
        String jobs = this.jobs.getText();
        String processingTime = this.processingTimeRange.getText();
        String weightRangeString = this.weightRange.getText();

        int jobsCount = Integer.parseInt(instances);
        int jobCount = Integer.parseInt(jobs);
        int processingTimeRange = Integer.parseInt(processingTime);
        int weightRange = Integer.parseInt(weightRangeString);

        String filepath = Factory.createInputFile(jobsCount, jobCount, processingTimeRange, weightRange);
        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution;
        solution = Factory.runBruteForceSearch(filepath, jobsCount, jobCount);
        Factory.createOutputFile(filepath, solution, false, "BruteForce");

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

        int j = 1;
        for (LineChart.Series<Number, Number> one : series){
            one.setName("instance  " + j++);
            lineChartData.add(one);
        }
        graph1.setData(lineChartData);
        graph1.createSymbolsProperty();
    }

    @FXML protected void handleRunSAButtonAction(ActionEvent event)  {
        String temperatureString = this.temperature.getText();
        String coolingRateString = this.coolingRate.getText();
        String loadFileString = this.filepathSA.getText();
        String numberOfJobsString = this.jobsSA.getText();
        String instancesSAString = this.instancesSA.getText();

        double temperature = Double.parseDouble(temperatureString);
        double coolingRate = Double.parseDouble(coolingRateString);
        int numberOfJobs = Integer.parseInt(numberOfJobsString);
        int numberOfInstances = Integer.parseInt(instancesSAString);

        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution;
        solution = Factory.runSimulatedAnnealing(loadFileString, numberOfInstances, numberOfJobs, temperature, coolingRate);

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

        int j = 1;
        for (LineChart.Series<Number, Number> one : series){
            one.setName("..." + loadFileString.substring(loadFileString.length()-11, loadFileString.length()));
            lineChartData.add(one);
        }
        graph2.setData(lineChartData);
    }

    @FXML protected void handleRunTSButtonAction(ActionEvent event)  {
        String instancesTSString = this.instancesTS.getText();
        String jobsTSString = this.jobsTS.getText();
        String tabuLengthString = this.tabuLength.getText();
        String numberOfIterationsString = this.numberOfIterations.getText();
        String filepathTSString = this.filepathTS.getText();

        int numberOfIterations = Integer.parseInt(numberOfIterationsString);
        int tabuLength = Integer.parseInt(tabuLengthString);
        int numberOfJobs = Integer.parseInt(jobsTSString);
        int numberOfInstances = Integer.parseInt(instancesTSString);

        Map<ArrayList<ArrayList<Integer>>, ArrayList<Integer>> solution;
        solution = Factory.runTabuSearch(filepathTSString, numberOfInstances, numberOfJobs, numberOfIterations, tabuLength);

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

        int j = 1;
        for (LineChart.Series<Number, Number> one : series){
            one.setName("..." + filepathTSString.substring(filepathTSString.length()-11, filepathTSString.length()));
            lineChartData.add(one);
        }
        graph3.setData(lineChartData);
    }
}
