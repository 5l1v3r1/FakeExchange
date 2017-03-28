package au.com.livewirelabs.assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Used to save to the log.
 */
class Logger {

    void log(String msg){

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            //NOTE: the 'classes' bit on the directory is for compiling! Maven has some interesting ways of organising folders
            //Remove it for testing.
            String directory = "classes/exchange/";
            String txtFile = directory + "Log.txt";
            File f = new File(directory);
            if(!f.exists()){
                f.mkdir();
            }
            f = new File(txtFile);
            fw = new FileWriter(f.getAbsoluteFile(),true);
            bw = new BufferedWriter(fw);

            LocalDateTime timePoint = LocalDateTime.now();

            bw.write(timePoint + ": " + msg + "\n");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }
}
