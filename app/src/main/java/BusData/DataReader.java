package BusData;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import SparseArray.*;

public class DataReader {

    /**
     * Reads data from given file to SparseArray
     *
     * @param file - file
     * @return SparseArray
     * @throws FileNotFoundException
     */
   public static SparseArray<Bus> readBusData(InputStream file) throws FileNotFoundException {

        SparseArray<Bus> arr = new SparseArray<Bus>(60);

        try {
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            while (sc.hasNextLine()) {
                String[] data = line.split(";");
                int num = Integer.parseInt(data[0]);
                String name = data[1];
                List<String> busStops = new ArrayList<String>();
                line = sc.nextLine();
                while (!line.contains(";")) {
                    busStops.add(line);
                    line = sc.nextLine();
                }
                Bus bus = new Bus(name, busStops);
                arr.put(num, bus);
            }
        } catch (InputMismatchException e) {
            //throw new InputMismatchException("Error in input - DataReader.java");
        } catch (NoSuchElementException e) {
            //throw new NoSuchElementException("There is no such element - DataReader.java");
        }
        return arr;
    }
}
