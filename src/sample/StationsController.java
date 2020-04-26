package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.awt.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

//not main needed
public class StationsController {

    Map<String, ArrayList<String[]>> CityData;

    private String city = null;

    private String year = null;

    private String month = null;

    @FXML
    private GridPane Table;

    @FXML
    private Label cityTitle;

    @FXML
    private Label yearTitle;

    @FXML
    private Label monthTitle;

    @FXML
    private String tabName;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private Label newTab;

    @FXML
    private Button btnReport2;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private ComboBox<String> comboBox3;

    @FXML
    private void initialize() {

        this.setTableHeader();

        //create a new object of the CSVReader
        CSVReader stationRead = new CSVReader();

        this.CityData = stationRead.getCity_data();

        //set of all the city names.keyset
        for (String key : this.CityData.keySet()) {
            System.out.println(key);
        }

        //keyset zoznam miest
        ObservableList<String> list = FXCollections.observableArrayList(this.CityData.keySet());

        comboBox.setItems(list);

        //the user can search for the city on its own
        comboBox.setEditable(true);

        ObservableList<String> list2 = FXCollections.observableArrayList("2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");

        comboBox2.setItems(list2);

        comboBox2.setEditable(true);

        ObservableList<String> list3 = FXCollections.observableArrayList("1", "2", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        comboBox3.setItems(list3);
        comboBox3.setEditable(true);

    }

    @FXML
    public Tab createNewTab(String tabName) {

        TabPane mainTabPane = new TabPane();

        this.mainTabPane = mainTabPane;
        Tab tabby = new Tab(tabName);
        mainTabPane.getTabs().add(tabby);
        //return tabby;
        System.out.println(tabby);
        tabby.setText("ii");
        return tabby;
    }

    @FXML
    public void CreateNewTab(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("report_tab.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            root.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
         //   graphController gController = fxmlLoader.getController();
          //  gController.myFunction(comboBox.getValue());
         //   gController.myFunction2(comboBox2.getValue());
          //  gController.myFunction3(comboBox3.getValue());


            TabPane mainTabPane = new TabPane();

            this.mainTabPane = mainTabPane;
            Tab tabby = new Tab(tabName);
            mainTabPane.getTabs().add(tabby);
            //return tabby;
            System.out.println(tabby);
            tabby.setText("ii");


            //access the data of a particular/chosen city
           // ArrayList<String[]> records = this.CityData.get(this.city);

           // gController.setMyChart();
           // gController.setGraph();

           // @FXML
           // private TabPane mainTabPane;

            //public void createTeamTab(Team team) {
              //  mainTabPane.getTabs().add(new Tab(team.getTeamName()));

            //}
          //  Tab tab1 = new Tab();
          //  this.mainTabPane.getTabs().add(tab1);

          //  Stage stage = new Stage();
          // stage.setScene(new Scene(root));
           // stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        System.out.println("hii");

    }


    @FXML
    private void comboChanged(ActionEvent event) {

        this.city = comboBox.getValue();
        this.setTable();

    }

    @FXML
    //binding the attribute to the year
    public void comboChanged2(ActionEvent event) {

        this.year = comboBox2.getValue();

        this.setTable();

    }

    @FXML
    public void comboChanged3(ActionEvent event) {

        this.month = comboBox3.getValue();

        this.setTable();
    }


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

            //change the label to the acta=ua month
            this.monthTitle.setText(this.month);

            //access the data of a particular/chosen city
            ArrayList<String[]> records = this.CityData.get(this.city);

            //one line
            for (String[] temp : records) {

                if (temp[0].equals(this.year)) {
                    if (this.month != null) {
                        if (temp[1].equals(this.month)) {
                            this.addTableRow(temp, 1);
                            break;
                        }
                    }
                    else {
                        this.addTableRow(temp, Integer.parseInt(temp[1]));
                    }
                }
            }

        }

    }

    private void addTableRow(String[] temp, Integer rowIndex) {
        Table.add(new Text(temp[1]), 0, rowIndex); //label, colIndex, rowIndex
        Table.add(new Text(temp[2]), 1, rowIndex);
        Table.add(new Text(temp[3]), 2, rowIndex);
        Table.add(new Text(temp[4]), 3, rowIndex);
        Table.add(new Text(temp[5]), 4, rowIndex);
    }


    private void setTableHeader() {

        Table.add(new Text("Month"),  0, 0);
        Table.add(new Text("Max Temperature"),  1, 0);
        Table.add(new Text("Min Temperature"),  2, 0);
        Table.add(new Text("Total Air Frost"),  3, 0);
        Table.add(new Text("Total Rain Fall"),  4, 0);

    }
}

