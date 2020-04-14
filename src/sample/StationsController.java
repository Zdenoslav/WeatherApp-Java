package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.awt.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

//not main needed
public class StationsController {

    Map<String, ArrayList<String[]>> CityData;

    private String city = null;

    private String year = null;

    @FXML
    private GridPane Table;

    @FXML
    private Label cityTitle;

    @FXML
    private Label yearTitle;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> comboBox2;

   // @FXML
 //   private Button btnHistory;

   @FXML
    private void initialize() {

       this.setTableHeader();

       //create a new object of the CSVReader
       CSVReader stationRead = new CSVReader();

       this.CityData = stationRead.getCity_data();

       //set of all the city names.keyset
       for ( String key : this.CityData.keySet() ) {
           System.out.println( key);
       }

       ObservableList<String> list = FXCollections.observableArrayList(this.CityData.keySet());

       comboBox.setItems(list);

       ObservableList<String> list2 = FXCollections.observableArrayList("2011","2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");

       comboBox2.setItems(list2);

   }

   @FXML
   private void comboChanged(ActionEvent event) {

       this.city = comboBox.getValue();

       this.setTable();

   }

    //binding the attribute to the year
   public void comboChanged2(ActionEvent event) {

       this.year = comboBox2.getValue();

       this.setTable();

   }

 //  public static void btnHistory(ActionEvent event) {

   //    Tab tab = new Tab();
     //  TabPane.getTabs().add(tab);
 //  }

   //
   public void setTable() {

       //if nothings is chosen this line is executed
       if (this.city != null && this.year != null) {

           //clear all the previous data
           Table.getChildren().clear();

           this.setTableHeader();

           //change the label to the actual city
           this.cityTitle.setText(this.city);

           //change the label to the actual year
           this.yearTitle.setText(this.year);

           //access the data of a particular/chosen city
           ArrayList<String[]> records = this.CityData.get(this.city);

           for (String[] temp : records) {

               if (temp[0].equals(this.year)){

                   Table.add(new Text(temp[1]),  0, Integer.parseInt(temp[1]));
                   Table.add(new Text(temp[2]),  1, Integer.parseInt(temp[1]));
                   Table.add(new Text(temp[3]),  2, Integer.parseInt(temp[1]));
                   Table.add(new Text(temp[4]),  3, Integer.parseInt(temp[1]));
                   Table.add(new Text(temp[5]),  4, Integer.parseInt(temp[1]));

               }

           }

       }

   }

    private void setTableHeader() {

        Table.add(new Text("Month"),  0, 0);
        Table.add(new Text("Max Temperature"),  1, 0);
        Table.add(new Text("Min Temperature"),  2, 0);
        Table.add(new Text("Total Air Frost"),  3, 0);
        Table.add(new Text("Total Rain Fall"),  4, 0);

    }
}