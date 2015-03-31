package com.example.hushcoolcat.inanutshell;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hushcoolcat on 2015-03-30.
 */
public class loadFromStorage {
    String fileToRead;
    public loadFromStorage(String filename) {
        fileToRead = filename;
    }

    public String loadFile() {

        String line= "";
        // Read file line by line and print on the console
        try {
            FileReader inputFile = new FileReader(fileToRead);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data

            while ((line = bufferReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferReader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //Close the buffer reader
        return line.toString();
    }



    /*
        int ch;
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis
        try {
            fis = fis.openFileInput(filename);
            try {
                while( (ch = fis.read()) != -1)
                    fileContent.append((char)ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String data = new String(fileContent);

    */


}

