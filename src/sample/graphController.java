package sample;

import com.sun.glass.ui.CommonDialogs;
import com.sun.javafx.image.IntPixelGetter;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class graphController {

    final static String maxtemp = "Max Temp";
    final static String mintemp = "Min Temp";
    final static String TotalAirFrost = "Total Air Frost";
    final static String TotalRainFall = "Total Rain Fall";

    final static String january = "January";
    final static String february = "February";
    final static String march = "March";
    final static String april = "April";
    final static String may = "May";
    final static String june = "June";
    final static String july = "July";
    final static String august = "August";
    final static String september = "September";
    final static String october = "October";
    final static String november = "November";
    final static String december = "December";

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

    private Integer dat;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnBarChart;


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
        GraphStationCombo.setEditable(true);

        ObservableList<String> list2 = FXCollections.observableArrayList("2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");

        GraphYearCombo.setItems(list2);
        GraphYearCombo.setEditable(true);

        System.out.println();

        ObservableList<String> list3 = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        GraphMonthCombo.setItems(list3);
        GraphMonthCombo.setEditable(true);

        }
        //getting the data into the graph

        private static String getMonthNumber(String list3) {
            if (list3 == "January") {
                return "1";
            }
            if (list3 =="February"){
                return "2";
            }
            if (list3 == "March"){
                return "3";
            }
            if (list3 == "April"){
                return "4";
            }
            if (list3 == "May"){
                return "5";
            }
            if (list3 == "June"){
                return "6";
            }
            if (list3 == "July"){
                return "7";
            }
            if (list3 == "August"){
                return "8";
            }
            if (list3 == "September"){
                return "9";
            }
            if (list3 == "October"){
                return "10";
            }
            if (list3 == "November"){
                return "11";
            }
            if (list3 == "December"){
                return "12";
            }
            return null;
        }


        @FXML
        private void comboGraphStationChanged(ActionEvent event) {


            this.city = GraphStationCombo.getValue();
           // this.setMyChart();
            this.setGraph();
        }

        @FXML
        //binding the attribute to the year
        public void comboGraphYearChanged(ActionEvent event) {

            this.year = GraphYearCombo.getValue();
            //this.setMyChart();
            this.setGraph();
        }

        @FXML
        public void comboGraphMonthChanged(ActionEvent event) {

            this.month = GraphMonthCombo.getValue();
           //this.setMyChart();
            this.setGraph();
        }


        @FXML
        private ListView listview;

        @FXML
        private void makeReport(ActionEvent event) {

          //  this.start();

            this.FileMake();

            this.btnReport.setText("You ROCK");
        }

        public void start(Stage stage){
            try {
                stage.setTitle("yeaa");

                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File("report.txt"));
                fc.showSaveDialog(stage);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };


        public void myFunction(String text){
                cityText.setText(text);

        }

        public void myFunction2(String text) {
               yearText.setText(text);
              // this.setGraph();
        }

        public void myFunction3(String text) {
               monthText.setText(text);
               //this.setGraph();
        }

        //a function to count all the values and means for every meteorological station
         public void CountReport(String key) {

            this.minTemp = Double.MAX_VALUE;
            this.maxTemp = Double.MIN_VALUE;

            Double totalAF = 0.0;
            Double totalRF = 0.0;

            //access the data of a particular/chosen city
            ArrayList<String[]> records = this.CityData.get(key);


            for (String[] temp : records) {

                if (Double.parseDouble(temp[2]) > this.maxTemp) {

                    this.maxTemp = Double.parseDouble(temp[2]);
                    this.maxTempYear = Integer.parseInt(temp[0]);
                    this.maxTempMonth = Integer.parseInt(temp[1]);

                }

                if (Double.parseDouble(temp[3]) < this.minTemp) {

                    this.minTemp = Double.parseDouble(temp[3]);
                    this.minTempYear = Integer.parseInt(temp[0]);
                    this.minTempMonth = Integer.parseInt(temp[1]);

                }

                totalAF += Double.parseDouble(temp[4]);
                totalRF += Double.parseDouble(temp[5]);
            }

                this.averageAF = totalAF / records.size();
                this.averageRF = totalRF / records.size();

                System.out.println(averageAF);
                System.out.println(maxTemp);
                System.out.println(maxTempMonth);
                System.out.println(maxTempYear);
                System.out.println();

         }


        public void FileMake() {

            String filename = "report.txt";

            Integer sqNumber = 0;

            try {
                PrintWriter outputStream = new PrintWriter(filename);

                outputStream.println("\n" + "Report for all the meteorological stations" + "\n");

                //give the user the time when the report was created
                Date date = new Date();

                outputStream.printf("%tT%n", date);

                for(String key : CityData.keySet() ) {

                    this.names = key;

                    //if statement to avoid the files with no data
                    if(this.CityData.get(key).size() == 0){
                        continue;
                    }

                    this.CountReport(key);


                    sqNumber++;

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

                 Stage stage = new Stage();
                 //stage.setScene(new Scene(root));
                // stage.show();

                FileChooser fileChooser = new FileChooser();


                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setTitle("Open Folder");
                File file = fileChooser.showOpenDialog(new Stage());

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

                    if (temp[0].equals(this.year) && temp[1].equals(getMonthNumber(this.month))) {

                        max = temp[2];

                        min = temp[3];

                        TAF = temp[4];

                        TRF = temp[5];

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
                    bc = new BarChart<>(xAxis, yAxis);
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

        public void setMyChart2() {


        myChart.getData().clear();

        //if nothings is chosen this line is executed
        if (this.city != null && this.year != null && this.month != null) {

            //change the label to the actual year
            this.yearText.setText(this.year);

            //change the label to the actual city
            this.cityText.setText(this.city);

            //access the data of a particular/chosen city
            ArrayList<String[]> records = this.CityData.get(this.city);

            for (String[] temp : records) {

                if (temp[0].equals(this.year)) {

                    max = temp[2];

                    min = temp[3];

                    TAF = temp[4];

                    TRF = temp[5];

                }

            }

        }
       }

    public void setGraph2() {

        this.setMyChart2();

        //creating a graph
        XYChart.Series series1 = new XYChart.Series();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number>
                bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("STATISTICS");
        //name of the city
        series1.setName("Max Temp");

        series1.getData().add(new XYChart.Data(january, Integer.parseInt(max)));
        series1.getData().add(new XYChart.Data(january, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(january, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(january, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(february, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(february, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(february, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(february, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(march, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(march, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(march, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(march, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(april, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(april, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(april, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(april, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(may, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(may, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(may, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(may, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(june, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(june, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(june, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(june, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(july, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(july, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(july, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(july, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(august, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(august, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(august, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(august, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(september, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(september, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(september, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(september, Double.parseDouble(TRF)));


        series1.getData().add(new XYChart.Data(october, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(october, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(october, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(october, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(november, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(november, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(november, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(november, Double.parseDouble(TRF)));

        series1.getData().add(new XYChart.Data(december, Double.parseDouble(max)));
        series1.getData().add(new XYChart.Data(december, Double.parseDouble(min)));
        series1.getData().add(new XYChart.Data(december, Double.parseDouble(TAF)));
        series1.getData().add(new XYChart.Data(december, Double.parseDouble(TRF)));



        myChart.getData().add(series1);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Min Temp");

        series2.getData().add(new XYChart.Data(january, Double.parseDouble(max)));
        series2.getData().add(new XYChart.Data(january, Double.parseDouble(min)));
        series2.getData().add(new XYChart.Data(january, Double.parseDouble(TAF)));
        series2.getData().add(new XYChart.Data(january, Double.parseDouble(TRF)));

        myChart.getData().add(series2);

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Total Air Frost");

        series3.getData().add(new XYChart.Data(january, Double.parseDouble(max)));
        series3.getData().add(new XYChart.Data(january, Double.parseDouble(min)));
        series3.getData().add(new XYChart.Data(january, Double.parseDouble(TAF)));
        series3.getData().add(new XYChart.Data(january, Double.parseDouble(TRF)));


        myChart.getData().add(series3);

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Total Rain Fall");

        series4.getData().add(new XYChart.Data(january, Double.parseDouble(max)));
        series4.getData().add(new XYChart.Data(january, Double.parseDouble(min)));
        series4.getData().add(new XYChart.Data(january, Double.parseDouble(TAF)));
        series4.getData().add(new XYChart.Data(january, Double.parseDouble(TRF)));


        myChart.getData().add(series4);
    }
}


