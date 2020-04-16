package sample;

import com.sun.javafx.beans.event.AbstractNotifyListener;

import java.io.*;

public class textftile {

    public static void main(String[] args) {

        try{

            String filename = "results.txt";
            String text = "Hello, Youtube";

            File file = new File(filename);

            if(!file.exists()){
                file.createNewFile();

                //now lets wirte our text to new file
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(text);
                bw.close();

                System.out.println("File " + filename + " has been created");

            } else {
                System.out.println("File " + filename + " already exists.");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
