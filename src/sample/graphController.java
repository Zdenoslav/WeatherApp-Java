package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.Map;

public class graphController {

    final static String maxtemp = "MaxTemp";
    final static String mintemp = "MinTemp";
    final static String TotalAirFrost = "TotalAirFrost";
    final static String TotalRainFall = "TotalRainFall";

    @FXML
    private BarChart myChart;

    Map<String, ArrayList<String[]>> CityData;

    private String city = null;

    private String year = null;

    private String max;

    private String min;

    private String TAF;

    private String TRF;

    @FXML
    private ComboBox<String> GraphYearCombo;

    @FXML
    private ComboBox<String> GraphStationCombo;

    @FXML
    private void initialize() {

        //create a new object of the CSVReader
        CSVReader stationRead = new CSVReader();

        this.CityData = stationRead.getCity_data();

        //set of all the city names.keyset
        for (String key : this.CityData.keySet()) {
            System.out.println(key);
        }

        ObservableList<String> list = FXCollections.observableArrayList(this.CityData.keySet());

        GraphStationCombo.setItems(list);

        ObservableList<String> list2 = FXCollections.observableArrayList("2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");

        GraphYearCombo.setItems(list2);

    }
        //getting the data into the graph

        @FXML
        private void comboGraphStationChanged(ActionEvent event) {

            this.city = GraphStationCombo.getValue();

            this.setGraph();

            System.out.println("DEEP WORK");

        }

        //binding the attribute to the year
        public void comboGraphYearChanged(ActionEvent event) {

            this.year = GraphYearCombo.getValue();

            this.setGraph();

            System.out.println("DEEP WORK");

        }

        public void setGraph() {

            this.setMyChart();

            //if nothings is chosen this line is executed
            if (this.city != null && this.year != null) {

                //access the data of a particular/chosen city
                ArrayList<String[]> records = this.CityData.get(this.city);

                for (String[] temp : records) {

                    if (temp[0].equals(this.year)) {

                      max = temp[1];

                      min = temp[2];

                      TAF = temp[3];

                      TRF = temp[4];

                    }

                }


            }

        }

        public void setMyChart() {

            //creating a graph
            XYChart.Series series1 = new XYChart.Series();
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number>
                    bc = new BarChart<String, Number>(xAxis, yAxis);
            bc.setTitle("STATISTICS");
            //name of the city
            series1.setName("Max Temperature");

            series1.getData().add(new XYChart.Data(maxtemp, max));

            myChart.getData().add(series1);

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Min Temperature");

            series2.getData().add(new XYChart.Data(mintemp, min));

            //myChart.getData().add(series2);

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("Total Air Frost");

            series3.getData().add(new XYChart.Data(TotalAirFrost, TRF));

      //      myChart.getData().add(series3);

            XYChart.Series series4 = new XYChart.Series();
            series4.setName("Total Rain Fall");

            series4.getData().add(new XYChart.Data(TotalRainFall, TAF));

       //     myChart.getData().add(series4);
        }

    }
