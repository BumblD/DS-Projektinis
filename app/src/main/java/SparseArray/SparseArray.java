package SparseArray;

import java.util.Arrays;

public class SparseArray<E> implements Cloneable, SparseArrayInterface<E>{

    private static final Object removed = new Object();
    private boolean isGarbage = false;

    private int size;
    private int[] keys;
    private Object[] values;

    /**
     * Creates a new SparseArray containing no mappings
     */
    public SparseArray() {
        this(10);
    }

    /**
     * Creates a new SparseArray containing no mappings
     */
    public SparseArray(int initialCapacity) {
        if (initialCapacity == 0) {
            keys = new int[0];
            values = new Object[0];
        } else {
            keys = new int[initialCapacity];
            values = new Object[initialCapacity];
        }
        size = 0;
    }

    /**
     * Clones SparseArray
     * @return cloned array
     */
    @Override
    public SparseArray<E> clone() {
        SparseArray<E> clone = null;
        try {
            clone = (SparseArray<E>) super.clone();
            clone.keys = keys.clone();
            clone.values = values.clone();
        } catch (CloneNotSupportedException cnse){
            /* ignore */
        }
        return clone;
    }

    /**
     * Puts a key/value pair into the array, optimizing for the case where
     * the key is greater than all existing keys in the array.
     *
     * @param key
     * @param value
     */
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

    /**
     * Removes all elements from SparseArray
     */
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

    /**
     * Deletes an element from array
     *
     * @param key - deleted elements key
     */
    @Override
    public void delete(int key) {
        int i = Arrays.binarySearch(keys, 0, size, key);

        if (i >= 0 && values[i] != null) {
            if (values[i] != removed) {
                values[i] = removed;
                isGarbage = true;
            }
        }
    }

    /**
     * Gets element by its key
     *
     * @param key
     * @return
     */
    @Override
    public E get(int key) {
        return get(key, null);
    }

    @Override
    public E get(int key, E valueIfKeyNotFound) {
        int i = Arrays.binarySearch(keys, 0, size, key);

        if (i < 0 || values[i] == removed || values[i] == null) {
            return  valueIfKeyNotFound;
        } else {
            return (E) values[i];
        }
    }

    /**
     * Puts element to array
     *
     * @param key
     * @param value
     */
    @Override
    public void put(int key, E value) {
        if (key < 0 || value == null) { return; }

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
            size++;
        }
    }

    /**
     * Finds place to insert key
     *
     * @param arr - key array
     * @param currentSize - size
     * @param index - position index
     * @param element - key
     * @return
     */
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

    /**
     * Finds place to insert value
     *
     * @param arr - values array
     * @param currentSize - size
     * @param index - position index
     * @param element - value
     * @return
     */
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

    /**
     * Removes element from array
     *
     * @param key
     */
    @Override
    public void remove(int key) {
        delete(key);
    }

    /**
     * Sets elements value
     *
     * @param index
     * @param value
     */
    @Override
    public void setValueAt(int index, E value) {
        if(isGarbage) {
            gc();
        }

        values[index] = value;
    }

    /**
     * Returns array size
     *
     * @return array size
     */
    @Override
    public int size() {
        if (isGarbage) {
            gc();
        }

        return size;
    }

    /**
     * Gets key at given place
     *
     * @param index
     * @return
     */
    @Override
    public int keyAt(int index) {
        if (isGarbage) {
            gc();
        }
        return keys[index];
    }

    /**
     * Gets value at given place
     *
     * @param index
     * @return
     */
    @Override
    public E valueAt(int index) {
        if (isGarbage) {
            gc();
        }
        return (E) values[index];
    }

    /**
     * ToString formatting
     *
     * @return String
     */
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

    /**
     * Increases keys array size
     */
    private void increaseKeysSize() {
        int a = size++;
        int[] temp = new int[a];

        for (int i = 0; i < keys.length; i++) {
            temp[i] = keys[i];
        }
        keys = temp;
    }

    /**
     * Increases values array size
     */
    private void increaseValuesSize() {
        int a = size++;
        Object[] temp = new Object[a];

        for (int i = 0; i < values.length; i++) {
            temp[i] = values[i];
        }
        values = temp;
    }

    /**
     * Garbage collector from Androids documentation
     */
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
