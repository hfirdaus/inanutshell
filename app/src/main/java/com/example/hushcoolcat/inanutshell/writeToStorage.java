package com.example.hushcoolcat.inanutshell;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by hushcoolcat on 2015-02-19.
 */
public class writeToStorage {

    public writeToStorage(String filename, String dataToSave) {
        if (isExternalStorageWritable()) {

            try {
                File sdCard = Environment.getExternalStorageDirectory();

                File directory = new File(sdCard.getAbsolutePath() + "/InaNutshell");
                directory.mkdirs();
                File file = new File(directory, filename);

                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(dataToSave);
                osw.flush();
                osw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    public boolean isExternalStorageWritable() {
        String state= Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            return true;

        return false;
    }
}
