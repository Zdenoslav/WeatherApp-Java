package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class CSVReaderTest {

    Map<String, ArrayList<String[]>> city_data = new HashMap<String, ArrayList<String[]>>();

    public CSVReaderTest() {

        File[] files = new File("/home/c1964235/WeatherApp/src/sample/DATA").listFiles();
        this.CsvReader(files);
        this.PrintCityRecord("Aberporth.csv");
    }

    public static void main(String[] args) throws FileNotFoundException {

        CSVReaderTest c = new CSVReaderTest();

    }

    public void CsvReader(File[] files) {

        for (File file : files) {
            if (file.isDirectory()) {


            } else {
                try {

                    String pathname = "/home/c1964235/WeatherApp/src/sample/DATA/" + file.getName();
                    System.out.println(pathname);

                    Scanner scan = new Scanner(new File(pathname));
                    ArrayList<String[]> records = new ArrayList<String[]>();
                    String[] record = new String[2];
                    while (scan.hasNext()) {
                        record = scan.nextLine().split(",");
                        records.add(record);
                    }

                    this.city_data.put(file.getName(), records);


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

