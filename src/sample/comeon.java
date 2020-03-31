package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class comeon {


    public static void main(String[] args) throws Exception{


        String pathname = "/home/c1964235/WeatherApp/src/sample/DATA";

        Scanner scan = new Scanner(new File(pathname));
        ArrayList<String[]> records = new ArrayList<String[]>();
        String[] record = new String[2];
        while(scan.hasNext())
        {
            record = scan.nextLine().split(",");
            records.add(record);
        }
        //now records has your records
        //here is a way to lopp through the records
        for(String[] temp : records)
        {
            for(String temp1 : temp)
            {
                System.out.println(temp1 + "");
            }
            System.out.println("\n");
        }
    }

}
