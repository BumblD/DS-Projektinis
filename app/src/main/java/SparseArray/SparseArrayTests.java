package SparseArray;

import java.io.FileNotFoundException;

public class SparseArrayTests {

    public static void main(String[] args) throws FileNotFoundException {
        SparseArrayFixed<String> arr = new SparseArrayFixed<>(10);
        /*arr.append(0, "Lol");
        arr.append(2, "Lol");
        arr.append(3, "Lol");
        arr.append(4, "Lol");*/
        //arr.put(1, "A");
        arr.put(2, "B");
        arr.put(3, "C");
        //arr.put(4, "D");
        arr.put(5, "E");
        arr.put(6, "F");
        arr.put(7, "G");
        arr.append(2, "qwerty");
        //System.out.println(arr.get(1, "krakabolix"));
        //System.out.println(arr.keyAt(7));
        arr.setValueAt(1, "yolooooooo");

        /*for (int i = 0; i < arr.values.length; i++) {
            System.out.println(arr.values[i]);
        }*/

        //System.out.println(arr.get(2));

        /*for (int i = 0; i <= 10; i++) {
            System.out.println(arr.get(i));
        }*/
        
        /*SparseArrayFixed<Bus> arr2 = DataReader.readBusData("C:\\Users\\Dovydas\\Documents\\Programming\\3_Semestras\\JavaApplication2\\src\\javaapplication2\\bus_stops_data.txt");
        
        System.out.println(1 + " " + arr2.get(1).getRouteName());
        for (int i = 0; i < arr2.get(1).getRouteList().size(); i++) {
            System.out.println(arr2.get(1).getRouteList().get(i));
        }
        
        for (int i = 0; i < 70; i++) {
            if (arr2.get(i) != null)
                System.out.println(i+"=" + arr2.get(i).getRouteName());
        }*/
        
        /**
         * Real life uses :
         * 1) Palei telefono numeri (69512345) randama informacija apie draugus, paskutines ju zinutes, skambuciai, t.t...
         * 2) To-do listas, pagal data (key=0901 - rugsejo 1 ir t.t.)
         * 3) Automobilio daliu arba siaip prekiu sarasas pagal ID
         * 4) Palei autobuso numeri suranda jo marsruta
         */
    }
}
