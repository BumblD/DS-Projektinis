package SparseArray;

public interface SparseArrayInterface<E> {

    /**
     * Creates and returns a copy of this object.
     * @return
     */
    SparseArrayFixed<E> clone();

    /**
     * Puts a key/value pair into the array, optimizing for the case where the key is greater than all existing keys in the array.
     * @param key
     * @param value
     */
    void append(int key, E value);

    /**
     * Removes all key-value mappings from this SparseArray.
     */
    void clear();

    /**
     * Removes the mapping from the specified key, if there was any.
     * @param key
     */
    void delete(int key);

    /**
     * Gets the Object mapped from the specified key, or null if no such mapping has been made.
     * @param key
     * @return
     */
    E get(int key);

    /**
     * Gets the Object mapped from the specified key, or the specified Object if no such mapping has been made.
     * @param key
     * @param valueIfKeyNotFound
     * @return
     */
    E get(int key, E valueIfKeyNotFound);

    /**
     * Adds a mapping from the specified key to the specified value, replacing the previous mapping from the specified key if there was one.
     * @param key
     * @param value
     */
    void put(int key, E value);

    /**
     * Alias for delete(int).
     * @param key
     */
    void remove(int key);

    /**
     * Given an index in the range 0...size()-1, sets a new value for the indexth key-value mapping that this SparseArray stores.
     * @param index
     * @param value
     */
    void setValueAt(int index, E value);

    /**
     * Returns the number of key-value mappings that this SparseArray currently stores.
     * @return
     */
    int size();

    /**
     * Given an index in the range 0...size()-1, returns the key from the indexth key-value mapping that this SparseArray stores.
     * @param index
     * @return
     */
    int keyAt(int index);

    /**
     * Given an index in the range 0...size()-1, returns the value from the indexth key-value mapping that this SparseArray stores.
     * @param index
     * @return
     */
    E valueAt(int index);

    /**
     * This implementation composes a string by iterating over its mappings.
     * @return a string representation of the object.
     */
    String toString();
}
