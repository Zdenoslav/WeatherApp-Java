package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVReader {

    Map<String, ArrayList<String[]>> city_data = new HashMap<String, ArrayList<String[]>>();

    Map<String, ArrayList<String[]>> city_data1 = new HashMap<String, ArrayList<String[]>>();



    public CSVReader() {

        File[] files = new File("/home/c1964235/WeatherApp/src/sample/DATA").listFiles();
        this.CsvReader(files);
      //  this.PrintCityRecord("Aberporth.csv");
       // for( File station : files){
         //   System.out.println(station);
 //       }

    }

   // public static void main(String[] args) throws FileNotFoundException {

        //new object of the class CSVreader created
     //   CSVReader c = new CSVReader();
    //}


    public Map<String, ArrayList<String[]>> getCity_data() {
        return city_data;
    }

    public void CsvReader(File[] files) {

        for (File file : files) {
            if (file.isDirectory()) {


            } else {
                try {

                    String pathname = "/home/c1964235/WeatherApp/src/sample/DATA/" + file.getName();

                    Scanner scan = new Scanner(new File(pathname));
                    ArrayList<String[]> records = new ArrayList<String[]>();
                    String[] record = new String[2];
                    while (scan.hasNext()) {
                        record = scan.nextLine().split(",");
                        records.add(record);
                    }

                    this.city_data.put(file.getName().replace(".csv", ""), records);

                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }

            }
        }


    }

    public void PrintCityRecord(String name) {

        ArrayList<String[]> records = this.city_data.get(name);
        //now records has your records
        //here is a way to loop through the records
        for (String[] temp : records) {
            for (String temp1 : temp) {
                System.out.println(temp1 + "");
            }
            System.out.println("\n");
        }
    }

}
