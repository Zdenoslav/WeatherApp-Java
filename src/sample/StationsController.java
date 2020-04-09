package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.awt.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


import java.io.File;
import java.util.ArrayList;
import java.util.Map;

//not main needed
public class StationsController {

    Map<String, ArrayList<String[]>> CityData;

    @FXML
    private Label MaxTemp;

    @FXML
    private Label MinTemp;

    @FXML
    private Label TotAirFrost;

    @FXML
    private Label TotRainFall;
//january
    @FXML
    private Label MaxTemp1;

    @FXML
    private Label MinTemp1;

    @FXML
    private Label TotAirFrost1;

    @FXML
    private Label TotRainFall1;
    //february
    @FXML
    private Label MaxTemp2;

    @FXML
    private Label MinTemp2;

    @FXML
    private Label TotAirFrost2;

    @FXML
    private Label TotRainFall2;

    @FXML
    private Label MaxTemp3;

    @FXML
    private Label MinTemp3;

    @FXML
    private Label TotAirFrost3;

    @FXML
    private Label TotRainFall3;

    @FXML
    private Label MaxTemp4;

    @FXML
    private Label MinTemp4;

    @FXML
    private Label TotAirFrost4;

    @FXML
    private Label TotRainFall4;

    @FXML
    private Label MaxTemp5;

    @FXML
    private Label MinTemp5;

    @FXML
    private Label TotAirFrost5;

    @FXML
    private Label TotRainFall5;

    @FXML
    private Label MaxTemp6;

    @FXML
    private Label MinTemp6;

    @FXML
    private Label TotAirFrost6;

    @FXML
    private Label TotRainFall6;///
   @FXML
    private ComboBox<String> comboBox;

   @FXML
    private void initialize() {

       //create a new object of the CSVReader
       CSVReader stationRead = new CSVReader();

       this.CityData = stationRead.getCity_data();

       //set of all the city names.keyset
       for ( String key : this.CityData.keySet() ) {
           System.out.println( key);
       }

       ObservableList<String> list = FXCollections.observableArrayList(this.CityData.keySet());

       comboBox.setItems(list);

   }

   @FXML
   private void comboChanged(ActionEvent event) {

       ArrayList<String[]> records = this.CityData.get(comboBox.getValue());
       for (String[] temp : records) {

           if (temp[0].equals("2019") && temp[1].equals("1")) {
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
                   MaxTemp.setText(temp[2]);
                   MinTemp.setText(temp[3]);
                   TotAirFrost.setText(temp[4]);
                   TotRainFall.setText(temp[5]);
               }

           } else if (temp[0].equals("2019") && temp[1].equals("2")) {
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
                   MaxTemp1.setText(temp[2]);
                   MinTemp1.setText(temp[3]);
                   TotAirFrost1.setText(temp[4]);
                   TotRainFall1.setText(temp[5]);
               }
           }
           else if(temp[0].equals("2019") && temp[1].equals("3")){
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
                   MaxTemp2.setText(temp[2]);
                   MinTemp2.setText(temp[3]);
                   TotAirFrost2.setText(temp[4]);
                   TotRainFall2.setText(temp[5]);
               }
           }
           else if(temp[0].equals("2019") && temp[1].equals("4")){
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
                   MaxTemp3.setText(temp[2]);
                   MinTemp3.setText(temp[3]);
                   TotAirFrost3.setText(temp[4]);
                   TotRainFall3.setText(temp[5]);
               }
           }
           else if(temp[0].equals("2019") && temp[1].equals("5")){
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
                   MaxTemp4.setText(temp[2]);
                   MinTemp4.setText(temp[3]);
                   TotAirFrost4.setText(temp[4]);
                   TotRainFall4.setText(temp[5]);
               }
           }
           else if(temp[0].equals("2019") && temp[1].equals("6")){
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
                   MaxTemp5.setText(temp[2]);
                   MinTemp5.setText(temp[3]);
                   TotAirFrost5.setText(temp[4]);
                   TotRainFall5.setText(temp[5]);
               }
           }
       }
   }
}