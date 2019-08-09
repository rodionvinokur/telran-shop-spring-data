package com.telran.telranshopspringdata.app.additional;

import java.util.ArrayList;
import java.util.List;

public class Maximizer<K extends Comparable<K>, V> {
    private K key;
    private List<V> vList = new ArrayList<>();

    public Maximizer(K minKey) {
        this.key = minKey;
    }

    public void put(K key, V value) {
        if (this.key.compareTo(key) == 0) {
            vList.add(value);
        }
        if (this.key.compareTo(key) < 0) {
            vList.clear();
            this.key = key;
            vList.add(value);
        }
    }

    public List<V> get() {
        return vList;
    }
}
