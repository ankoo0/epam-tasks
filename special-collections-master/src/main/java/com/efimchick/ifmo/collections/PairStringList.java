package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList implements List<String> {

    //:TODO neighbouring pairs can contain equal strings so set and add should use odd or even indexes where even - first pair element and odd - second one


    private String[] elements;
    private int pointer;

    public PairStringList() {
        this.elements = new String[10];
        this.pointer = 0;
    }


    private void grow() {
        String[] newArray = new String[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    @Override
    public boolean add(String s) {
        if (pointer + 1 >= elements.length) {
            grow();
        }
        elements[pointer] = s;
        elements[pointer + 1] = s;
        pointer += 2;
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public Iterator<String> iterator() {
        return new PairStringListIterator();
    }

    private class PairStringListIterator implements Iterator<String> {
        private int cursor = -1;

        @Override
        public boolean hasNext() {
            return (cursor != size() - 1);
        }

        @Override
        public String next() {

            cursor++;

            return elements[cursor];
        }
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public Object[] toArray() {
        String[] outArray = new String[pointer];
        System.arraycopy(elements, 0, outArray, 0, pointer);
        return outArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) toArray();
    }

    @Override
    public void add(int index, String element) {
        String[] newArr = new String[size() + 2];

        if (index == 0 || index == 1) {
            System.arraycopy(elements, 0, newArr, 2, size());
            newArr[index] = element;
            newArr[index + 1] = element;
            elements = newArr;
            pointer += 2;
        } else if (index % 2 == 0) {
            System.arraycopy(elements, 0, newArr, 0, index);
            newArr[index] = element;
            newArr[index + 1] = element;
            System.arraycopy(elements, index, newArr, index + 2, pointer - (index));
            elements = newArr;
            pointer += 2;
        } else if (index % 2 != 0) {
            System.arraycopy(elements, 0, newArr, 0, index + 1);
            newArr[index + 1] = element;
            newArr[index + 2] = element;
            System.arraycopy(elements, index + 1, newArr, index + 3, pointer - (index + 1));
            elements = newArr;
            pointer += 2;
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }


    @Override
    public boolean remove(Object o) {
        int index = 0;
        for (int i = 0; i < pointer; i++) {
            if (elements[i].equals(o)) {
                index = i;
                break;
            }
        }

        String[] newArray = new String[pointer - 2];
        if (index == 0) {
            System.arraycopy(elements, 2, newArray, 0, pointer - 2);
            pointer -= 2;
            elements = newArray;
            return true;
        } else {
            System.arraycopy(elements, 0, newArray, 0, index - 1);
            System.arraycopy(elements, index + 2, newArray, index, pointer - (index + 2));
            pointer -= 2;
            elements = newArray;
            return true;
        }

    }


    @Override
    public String remove(int index) {
        String el = elements[index];
        String[] newArray = new String[pointer - 2];
        if (index == 0 || index == 1) {
            System.arraycopy(elements, 2, newArray, 0, pointer - 2);
            pointer -= 2;
            elements = newArray;
            return el;
        }


        // it can be first or second element of pair on this index. not working if there is two pairs of equal strings like a a a a need to use odd/even indexes to detect first/last pair element
        if (elements[index - 1].equals(elements[index])) {
            System.arraycopy(elements, 0, newArray, 0, index - 2);
            System.arraycopy(elements, index + 1, newArray, index - 1, pointer - (index + 1));
            pointer -= 2;
            elements = newArray;
            return el;
        }

        if (elements[index].equals(elements[index + 1])) {
            System.arraycopy(elements, 0, newArray, 0, index - 1);
            System.arraycopy(elements, index + 2, newArray, index, pointer - (index + 1));
            pointer -= 2;
            elements = newArray;
            return el;
        }
        return el;
    }


    @Override
    public boolean addAll(Collection<? extends String> c) {
        String[] newValues = duplicateValues(convertToArray(c));
        String[] newElements = new String[pointer + (newValues.length)];
        System.arraycopy(elements, 0, newElements, 0, pointer);
        System.arraycopy(newValues, 0, newElements, pointer, newValues.length);
        elements = newElements;
        pointer += newValues.length;
        return true;
    }

    private String[] duplicateValues(String[] arr) {
        String[] incrArray = new String[arr.length * 2];
        for (int i = 0, tmp = 0; i < arr.length; i++, tmp += 2) {
            incrArray[tmp] = arr[i];
            incrArray[tmp + 1] = arr[i];
        }
        return incrArray;
    }

    private String[] convertToArray(Collection<? extends String> c) {
        return c.toArray(String[]::new);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        if (index == 0) {
            String[] newValues = duplicateValues(convertToArray(c));
            String[] newElements = new String[pointer + (newValues.length)];
            System.arraycopy(elements, index + 2, newElements, newElements.length + index + 2, pointer - (index + 2));
            System.arraycopy(newValues, 0, newElements, 0, newValues.length);
            pointer += newValues.length;
            elements = newElements;
            return true;
        } else if (index % 2 == 0) {
            String[] newValues = duplicateValues(convertToArray(c));
            String[] newElements = new String[pointer + (newValues.length)];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(newValues, 0, newElements, index, newValues.length);
            System.arraycopy(elements, index, newElements, newValues.length + (index), pointer - index);
            pointer += newValues.length;
            elements = newElements;
            return true;
        } else {
            String[] newValues = duplicateValues(convertToArray(c));
            String[] newElements = new String[pointer + (newValues.length)];
            System.arraycopy(elements, 0, newElements, 0, index + 1);
            System.arraycopy(newValues, 0, newElements, index + 1, newValues.length);
            System.arraycopy(elements, index + 1, newElements, newValues.length + index + 1, pointer - index - 1);
            pointer += newValues.length;
            elements = newElements;
            return true;
        }

    }


    @Override
    public String get(int index) {
        return elements[index];
    }

    @Override
    public String set(int index, String element) {
        if (index % 2 == 0) {
            elements[index] = element;
            elements[index + 1] = element;
            return element;
        } else {
            elements[index] = element;
            elements[index - 1] = element;
            return element;
        }
    }


    @Override
    public void clear() {
        elements = new String[10];
        pointer = 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<String> listIterator() {
        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
    }
}
