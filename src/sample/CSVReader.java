package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVReader {

    Map<String, ArrayList<String[]>> city_data = new HashMap<String, ArrayList<String[]>>();

    public CSVReader() {

        File[] files = new File("src/sample/DATA").listFiles();
        this.CsvReader(files);

    }

    public Map<String, ArrayList<String[]>> getCity_data() { return city_data; }

    public void CsvReader(File[] files) {

        for (File file : files) {
            if (file.isDirectory()) {


            } else {
                try {

                    String pathname = "src/sample/DATA/" + file.getName();

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

}

