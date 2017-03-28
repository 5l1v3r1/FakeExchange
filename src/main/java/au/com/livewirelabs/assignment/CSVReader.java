package au.com.livewirelabs.assignment;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Typical CSV Reader
 * Can build a map of a csv, and save it
 * Obviously, this should be replaced with the actual exchange
 */

class CSVReader {

    //NOTE: the 'classes' bit on the directory is for compiling! Maven has some interesting ways of organising folders
    //Remove it for testing.
    private String csvFile = "classes/exchange/Exchange.csv";

    Map<String,Integer> buildMap(){
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        Map<String,Integer> newMap = new HashMap<String, Integer>();


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] stock = line.split(cvsSplitBy);
                if(stock.length == 2){
                    try{
                        newMap.put(stock[0],Integer.parseInt(stock[1]));
                    } catch(NumberFormatException e){
                        System.out.println("Warning: code " + stock[0] + " has an impossible amount of units (" + stock[1] + ")");
                    }

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return newMap;
    }


    void saveMap(Map<String,Integer> stockMap){

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(csvFile);
            bw = new BufferedWriter(fw);
            for (Map.Entry<String, Integer> stock : stockMap.entrySet()) {
                bw.write(stock.getKey() + "," + stock.getValue() + "\n");
            }

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
