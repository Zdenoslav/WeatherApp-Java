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
    private Label Temperature;

   @FXML
    private ComboBox<String> comboBox;

   @FXML
    private void initialize() {

       //create a new object of the CSVReader
       CSVReader stationRead = new CSVReader();

       this.CityData = stationRead.getCity_data();

       //set of all the city names.keyset
       for ( String key : this.CityData.keySet() ) {
           System.out.println( key );
       }

       ObservableList<String> list = FXCollections.observableArrayList(this.CityData.keySet());

       comboBox.setItems(list);

   }

   @FXML
   private void comboChanged(ActionEvent event) {
      //  Temperature.setText(comboBox.getValue());

       ArrayList<String[]> records = this.CityData.get(comboBox.getValue());
       for (String[] temp : records) {
           System.out.println(temp[0]);
           if (temp[0] .equals("2011")) {
               for (String temp1 : temp) {
                   System.out.println(temp1 + "");
               }

               myTemperatureLabel.getValue(temp[2])
           }
       }
   }

}

