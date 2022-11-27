package com.example.cinerama.UnitClass;

import java.util.TreeMap;

public class Ticket {
    private TreeMap<Session,Seat> treeMap;
    private Float price;

    public Ticket(TreeMap<Session, Seat> treeMap, Float price) {
        this.treeMap = treeMap;
        this.price = price;
    }

    public TreeMap<Session, Seat> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<Session, Seat> treeMap) {
        this.treeMap = treeMap;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
