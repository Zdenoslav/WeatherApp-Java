package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Read {

    static String xStrPath;
    static double[][] myArray;

    static void setUpMyCSVArray() {

        myArray = new double[4][5];

        Scanner scanIn = null;
        int Rowc = 0;
        int Row = 0;
        int Col = 0;
        int Colc = 0;
        String InputLine = "";
        double xnum = 0;
        String xfileLocation;

        xfileLocation = "/home/c1964235/WeatherApp/src/sample/Abertporth.csv";

        System.out.println("\n****** Setup Array ******");

        try {

            //setupscanner
            scanIn = new Scanner(new BufferedReader(new FileReader(xfileLocation)));

            //while ((InputLine = scnanIn.nextLine()) != null)
            while (scanIn.hasNextLine()) {
                //Read line in from File
                InputLine = scanIn.nextLine();
                //split the InputLine into an array at the commas
                String[] InArray = InputLine.split(",");

                //copy the content of the inArray to the my Array
                for (int x = 0; x < InArray.length; x++) {
                    myArray[Rowc][x] = Double.parseDouble(InArray[x]);
                }
            }
            //Increment the row in the array
            Rowc++;
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}