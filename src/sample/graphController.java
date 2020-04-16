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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

            System.out.println("lol");

            this.FileMake();

            this.btnReport.setText("You ROCK");
        }

        public void FileMake () {

            try{

                 String filename = "rock.txt";
                 String text = "Hello, Youtube";

                  File file = new File(filename);

                if(!file.exists()){
                    file.createNewFile();

                //now lets wirte our text to new file
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(text);
                bw.close();

                System.out.println("File " + filename + " has been created");

                } else {
                      System.out.println("File " + filename + " already exists.");
                }
             } catch(IOException e){
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
