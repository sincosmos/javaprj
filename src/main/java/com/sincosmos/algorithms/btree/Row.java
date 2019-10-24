package com.sincosmos.algorithms.btree;

public class Row implements Comparable<Row>{
    private String key;
    private String data;

    @Override
    public int compareTo(Row o) {
        return this.key.compareTo(o.getKey());
    }

    private String getKey() {
        return this.key;
    }
}
