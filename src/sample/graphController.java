package sample;

import javafx.beans.binding.DoubleExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import jdk.jfr.Category;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class graphController {

    final static String maxtemp = "MaxTemp";
    final static String mintemp = "MinTemp";
    final static String TotalAirFrost = "TotalAirFrost";
    final static String TotalRainFall = "TotalRainFall";

    @FXML
    private Label monthText;

    @FXML
    private Label yearText;

    @FXML
    private Label cityText;

    @FXML
    private BarChart myChart;

    Map<String, ArrayList<String[]>> CityData;

    private String city = null;

    private String year = null;

    private String month = null;

    private String max;

    private String min;

    private String TAF;

    private String TRF;

    private Integer minTempYear = 0;

    private Integer minTempMonth = 0;

    private Integer maxTempYear = 0;

    private Integer maxTempMonth = 0;

    private Double averageRF = 0.0;

    private Double averageAF = 0.0;

    private Double minTemp = Double.MAX_VALUE;

    private Double maxTemp = Double.MIN_VALUE;

    private String names;

    private String eachCity;

    @FXML
    private Button btnReport;

    @FXML
    private ComboBox<String> GraphYearCombo;

    @FXML
    private ComboBox<String> GraphStationCombo;

    @FXML
    private ComboBox<String> GraphMonthCombo;

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

        ObservableList<String> list3 = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

        GraphMonthCombo.setItems(list3);

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

           // this.setGraph();

            System.out.println("DEEP WORK");

        }

        public void comboGraphMonthChanged(ActionEvent event) {

            this.month = GraphMonthCombo.getValue();

            this.setGraph();

            System.out.println("DEEP WORK");

        }

        @FXML
        private void makeReport(ActionEvent event) {

            this.CountReport();

            this.FileMake();

            this.btnReport.setText("You ROCK");
        }


        public void CountReport() {

            Double totalAF = 0.0;
            Double totalRF = 0.0;

            //access the data of a particular/chosen city
            ArrayList<String[]> records = this.CityData.get(CityData.keySet());


            for (String[] temp : records) {

                if (Double.parseDouble(temp[2]) > maxTemp) {

                    this.maxTemp = Double.parseDouble(temp[2]);
                    this.maxTempYear = Integer.parseInt(temp[0]);
                    this.maxTempMonth = Integer.parseInt(temp[1]);

                }

                if (Double.parseDouble(temp[3]) < minTemp) {

                    this.minTemp = Double.parseDouble(temp[3]);
                    this.minTempYear = Integer.parseInt(temp[0]);
                    this.minTempMonth = Integer.parseInt(temp[1]);

                }

                totalAF += Double.parseDouble(temp[4]);
                totalRF += Double.parseDouble(temp[5]);


                this.averageAF = totalAF / records.size();
                this.averageRF = totalRF / records.size();

                System.out.println(averageAF);
                System.out.println(maxTemp);
                System.out.println(maxTempMonth);
                System.out.println(maxTempYear);
                System.out.println();

            }

        }

        public void FileMake() {

            System.out.println(averageAF);

            String filename = "report.txt";

            Integer sqNumber = 0;

            try {
                PrintWriter outputStream = new PrintWriter(filename);

                outputStream.println("\n" + "Report for all the meteorological stations" + "\n");

                for(String key : CityData.keySet() ) {

                    sqNumber++;

                    this.names = key;

                        outputStream.printf(" Number " + sqNumber );
                        outputStream.println();
                        outputStream.printf(" Station : " +  names);
                        outputStream.println();
                        outputStream.printf(" Highest : " + maxTempMonth + " " + maxTempYear + " " + maxTemp );
                        outputStream.println();
                        outputStream.printf(" Lowest : " + minTempMonth + " " + minTempYear + " " + minTemp );
                        outputStream.println();
                        outputStream.printf(" Average annual air frost : " + "%.2f", averageAF );
                        outputStream.println();
                        outputStream.printf(" Average annual rainfall : " + "%.2f", averageRF);
                        outputStream.println("\n");

                    }

                outputStream.close(); //flushes the data to the file

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

         public void setMyChart() {

            myChart.getData().clear();

            //if nothings is chosen this line is executed
            if (this.city != null && this.year != null && this.month != null) {

                //change the label to the actual month
                this.monthText.setText(this.month);

                //change the label to the actual year
               this.yearText.setText(this.year);

               //change the label to the actual city
               this.cityText.setText(this.city);

                //access the data of a particular/chosen city
                ArrayList<String[]> records = this.CityData.get(this.city);

                for (String[] temp : records) {

                    if (temp[0].equals(this.year) && temp[1].equals(this.month)) {

                        max = temp[2];

                        min = temp[3];

                        TAF = temp[4];

                        TRF = temp[5];

                        System.out.println(temp[1]);
                        System.out.println(temp[2]);
                        System.out.println(temp[3]);
                        System.out.println(temp[4]);

                    }

                }

            }
         }

        public void setGraph() {

            this.setMyChart();


            //creating a graph
            XYChart.Series series1 = new XYChart.Series();
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number>
                    bc = new BarChart<String, Number>(xAxis, yAxis);
            bc.setTitle("STATISTICS");
            //name of the city
            series1.setName("Max Temperature");

            series1.getData().add(new XYChart.Data(maxtemp, Double.parseDouble(max)));

            myChart.getData().add(series1);

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Min Temperature");

            series2.getData().add(new XYChart.Data(mintemp, Double.parseDouble(min)));

            myChart.getData().add(series2);

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("Total Air Frost");

            series3.getData().add(new XYChart.Data(TotalAirFrost, Double.parseDouble(TAF)));

            myChart.getData().add(series3);

            XYChart.Series series4 = new XYChart.Series();
            series4.setName("Total Rain Fall");

            series4.getData().add(new XYChart.Data(TotalRainFall, Double.parseDouble(TRF)));

            myChart.getData().add(series4);
        }

    }
