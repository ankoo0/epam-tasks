package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue implements Queue<Integer> {

    private List<Integer> items = new ArrayList<>();

    @Override
    public int size() {
        return items.size();
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
    public Iterator<Integer> iterator() {
        return items.iterator();
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
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
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
    public void clear() {

    }

    @Override
    public boolean offer(Integer integer) {
        items.add(integer);

        return true;
    }

    @Override
    public Integer remove() {
        return null;
    }

    @Override
    public Integer poll() {
        Integer i = getMedian();
        items.remove(i);

        return i;
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public Integer peek() {
        return getMedian();
    }

    private Integer getMedian (){
        List<Integer> tmp =items;
        tmp.sort(Comparator.naturalOrder());
        if (tmp.size()==1) return tmp.get(0);
        if (tmp.size()%2==0){
            Integer i1 =  tmp.get(tmp.size()/2-1);
            Integer i2 =  tmp.get(tmp.size()/2);
          return i1 > i2 ? i2:i1;
        } else {
            return tmp.get(tmp.size()/2);
        }
    }
}
