package SparseArray;

//import com.sun.deploy.util.ArrayUtil;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class SparseArrayFixed<E> implements Cloneable, SparseArrayInterface<E>{

    private static final Object removed = new Object();
    private boolean isGarbage = false;

    private int size;
    private int[] keys;
    private Object[] values;

    public SparseArrayFixed() {
        this(10);
    }
    public SparseArrayFixed(int initialCapacity) {
        if (initialCapacity == 0) {
            keys = new int[0];
            values = new Object[0];
        } else {
            keys = new int[initialCapacity];
            values = new Object[initialCapacity];
        }
        size = 0;
    }

    @Override
    public SparseArrayFixed<E> clone() {
        SparseArrayFixed<E> clone = null;
        try {
            clone = (SparseArrayFixed<E>) super.clone();
            clone.keys = keys.clone();
            clone.values = values.clone();
        } catch (CloneNotSupportedException cnse){
            /* ignore */
        }
        return clone;
    }

    @Override
    public void append(int key, E value) {
        if (size != 0 && key <= keys[size-1]) {
            put(key, value);
            return;
        }

        if (isGarbage && size >= keys.length) {
            gc();
        }

        if (size > keys.length) {
            this.increaseKeysSize();
            this.increaseValuesSize();
        }

        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public void clear() {
        int n = size;
        Object[] allValues = values;

        for(int i = 0; i < n; i++) {
            allValues[i] = null;
        }

        size = 0;
        isGarbage = false;
    }

    @Override
    public void delete(int key) {
        int i = Arrays.binarySearch(keys, 0, size, key);

        if (i >= 0) {
            if (values[i] != removed) {
                values[i] = removed;
                isGarbage = true;
            }
        }
    }

    @Override
    public E get(int key) {
        return get(key, null);
    }

    @Override
    public E get(int key, E valueIfKeyNotFound) {
        int i = Arrays.binarySearch(keys, 0, size, key);

        if (i < 0 || values[i] == removed) {
            return  valueIfKeyNotFound;
        } else {
            return (E) values[i];
        }
    }

    @Override
    public void put(int key, E value) {
        //int i = this.findInsertingPlace(keys, key);
        int i = Arrays.binarySearch(keys, 0, size, key);

        if (i >= 0) {
            values[i] = value;
        } else {
            i = ~i;

            if (i < size && values[i] == removed) {
                keys[i] = key;
                values[i] = value;
                return;
            }

            if (isGarbage && size >= keys.length) {
                gc();

                // Search again because indices may have changed.
                i = ~Arrays.binarySearch(keys, 0, size, key);
            }

            keys = insertKey(keys, size, i, key);
            values = insertValue(values, size, i, value);
            //keys[size] = key;
            //values[size] = value;
            size++;
        }
    }

    public int[] insertKey(int[] arr, int currentSize, int index, int element) {
        if (currentSize + 1 <= arr.length) {
            System.arraycopy(arr, index, arr, index + 1, currentSize - index);
            arr[index] = element;
            return arr;
        }

        int[] newArr = new int[currentSize*2];
        System.arraycopy(arr, 0, newArr, 0, currentSize*2);
        return newArr;
    }

    public Object[] insertValue(Object[] arr, int currentSize, int index, Object element) {
        if (currentSize + 1 <= arr.length) {
            System.arraycopy(arr, index, arr, index + 1, currentSize - index);
            arr[index] = element;
            return arr;
        }

        Object[] newArr = new Object[currentSize*2];
        System.arraycopy(arr, 0, newArr, 0, currentSize*2);
        return newArr;
    }

    @Override
    public void remove(int key) {
        delete(key);
    }

    @Override
    public void setValueAt(int index, E value) {
        if(isGarbage) {
            gc();
        }

        values[index] = value;
    }

    @Override
    public int size() {
        if (isGarbage) {
            gc();
        }

        return size;
    }

    @Override
    public int keyAt(int index) {
        if (isGarbage) {
            gc();
        }
        return keys[index];
    }

    @Override
    public E valueAt(int index) {
        if (isGarbage) {
            gc();
        }
        return (E) values[index];
    }

    @Override
    public String toString()
    {
        if (size() <= 0) {
            return "{}";
        }

        StringBuilder buffer = new StringBuilder(size * 28);
        buffer.append('{');
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            int key = keyAt(i);
            buffer.append(key);
            buffer.append('=');
            Object value = valueAt(i);
            if (value != this) {
                buffer.append(value);
            } else {
                buffer.append("(this Map)");
            }
        }
        buffer.append('}');
        return buffer.toString();
    }

    private void increaseKeysSize() {
        int a = size++;
        int[] temp = new int[a];

        for (int i = 0; i < keys.length; i++) {
            temp[i] = keys[i];
        }
        keys = temp;
    }

    private void increaseValuesSize() {
        int a = size++;
        Object[] temp = new Object[a];

        for (int i = 0; i < values.length; i++) {
            temp[i] = values[i];
        }
        values = temp;
    }

    private int findInsertingPlace(int[] keys, int key) {
        int insertBefore = 0;
        for (int i = 0; i < size; i++) {
            if (key > i) {
                break;
            }
            insertBefore++;
        }
        return insertBefore;
    }

    private void gc() {
        int n = size;
        int o = 0;
        int[] allKeys = keys;
        Object[] allValues = values;

        for (int i = 0; i < n; i++) {
            Object value = allValues[i];

            if(value != removed) {
                if (i != o) {
                    allKeys[o] = allKeys[i];
                    allValues[o] = value;
                    allValues[i] = null;
                }
                o++;
            }
        }
        isGarbage = false;
        size = o;
    }
}
