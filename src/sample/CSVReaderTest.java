package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class CSVReaderTest {

    public static void main(String[] args) throws FileNotFoundException {

        File[] files = new File("/home/c1964235/WeatherApp/src/sample/DATA").listFiles();
        showFiles(files);
    }

    public static void showFiles(File[] files) {


        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                showFiles(file.listFiles()); //calls same method again

            } else {

                String pathname = file.getName();

                try {
                Scanner scnr = new Scanner(new File(pathname));
                while (scnr.hasNextLine()) {
                    System.out.println(scnr.hasNextLine());
                    scnr.close();
                }
                }
                catch (FileNotFoundException lol){
                        System.out.println(lol);
                    }
                }
            }
        }
    }

