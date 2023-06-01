package com.efimchick.ifmo.collections;

import java.util.*;
import java.util.stream.Collectors;

class SortedByAbsoluteValueIntegerSet implements Set<Integer> {

   private Map<Integer,Object> entries = new TreeMap<>(new AbsValueComparator());
   private static final Object PLACEHOLDER = new Object();

    private class AbsValueComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1<0){
               o1 = Integer.parseInt(trimSign(o1));
            }
            if (o2<0){
                o2 = Integer.parseInt(trimSign(o2));
            }
            return o1.compareTo(o2);
        }

        private String trimSign(Integer i){
            return String.valueOf(i).substring(1);
        }
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return entries.containsKey(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return entries.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        entries.put(integer,PLACEHOLDER);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        entries.remove(o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        c.forEach(i->entries.put(i,PLACEHOLDER));
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return this.entries.keySet().stream().map(Object::toString).sorted(Comparator.reverseOrder()).collect(Collectors.joining());
    }
}
