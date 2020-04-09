package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Map;

public class graphController {

    @FXML
    private LineChart myChart;

    @FXML
    private void initialize(){
        XYChart.Series series1 = new XYChart.Series();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Max Temperature");
        //name of the city
        series1.setName("");

        series1.getData().add(new XYChart.Data<>(1, 20));
        series1.getData().add(new XYChart.Data<>(2, 100));
        series1.getData().add(new XYChart.Data<>(3, 80));
        series1.getData().add(new XYChart.Data<>(4, 180));
        series1.getData().add(new XYChart.Data<>(5, 20));
        series1.getData().add(new XYChart.Data<>(6, -10));
        myChart.getData().add(series1);
    }
}