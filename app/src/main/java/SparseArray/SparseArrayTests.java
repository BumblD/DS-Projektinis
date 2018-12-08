package SparseArray;

import java.io.FileNotFoundException;

public class SparseArrayTests {

    public static void main(String[] args) throws FileNotFoundException {
        SparseArray<String> arr = new SparseArray<String>(10);
        /*arr.append(0, "abc");
        arr.append(2, "abc");
        arr.append(3, "abc");
        arr.append(4, "abc");*/
        //arr.put(1, "A");
        arr.put(2, "B");
        arr.put(3, "C");
        //arr.put(4, "D");
        arr.put(5, "E");
        arr.put(6, "F");
        arr.put(7, "G");
        arr.append(2, "qwerty");
        //System.out.println(arr.get(1, "wasd"));
        //System.out.println(arr.keyAt(7));
        arr.setValueAt(1, "uiop");

        /*for (int i = 0; i < arr.values.length; i++) {
            System.out.println(arr.values[i]);
        }*/

        //System.out.println(arr.get(2));

        /*for (int i = 0; i <= 10; i++) {
            System.out.println(arr.get(i));
        }*/
        
        /*SparseArray<Bus> arr2 = DataReader.readBusData("C:\\Users\\Dovydas\\Documents\\Programming\\3_Semestras\\JavaApplication2\\src\\javaapplication2\\bus_stops_data.txt");
        
        System.out.println(1 + " " + arr2.get(1).getRouteName());
        for (int i = 0; i < arr2.get(1).getRouteList().size(); i++) {
            System.out.println(arr2.get(1).getRouteList().get(i));
        }
        
        for (int i = 0; i < 70; i++) {
            if (arr2.get(i) != null)
                System.out.println(i+"=" + arr2.get(i).getRouteName());
        }*/
    }
}
