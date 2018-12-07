package BusData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import SparseArray.*;

public class DataReader {

   public static SparseArrayFixed<Bus> readBusData(InputStream file) throws FileNotFoundException {

        SparseArrayFixed<Bus> arr = new SparseArrayFixed<Bus>(60);

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
            //Ks.ern("Blogas duomenų formatas apie knygą -> " + dataString);
        } catch (NoSuchElementException e) {
            //Ks.ern("Trūksta duomenų apie knygą -> " + dataString);
        }
        return arr;
    }
}
