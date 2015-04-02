package com.example.hushcoolcat.inanutshell;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by hushcoolcat on 2015-03-30.
 */
public class loadFromStorage {
    String fileToRead;
    File directory;
    File sdCard;
    Context context;
    File file;
    public loadFromStorage(String filename) {
        fileToRead = filename + ".txt";
        sdCard = Environment.getExternalStorageDirectory();
        directory = new File(sdCard.getAbsolutePath() + "/InaNutshell");
        file = new File(directory, fileToRead);
    }

    public String loadFile() {
        StringBuilder inputStringBuilder = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            //FileReader inputFile = new FileReader(file.getAbsoluteFile());

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(isr);
            // Read file line by line and print on the console

            String line = bufferReader.readLine();
        while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append("\n");
                line = bufferReader.readLine();
            }
            bufferReader.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //Close the buffer reader
        Log.d("hadiya", inputStringBuilder.toString());

        return inputStringBuilder.toString();
    }

}

