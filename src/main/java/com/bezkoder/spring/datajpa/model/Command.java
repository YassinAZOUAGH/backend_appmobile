package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "commande")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "table_number")
    private String table;

    @Column(name = "price")
    private double price;

    public Command() {

    }

    public Command(String contents, String date, String time, String table, double price) {
        this.contents = contents;
        this.date = date;
        this.time = time;
        this.table = table;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Command [id=" + id + ", contents=" + contents + ", date=" + date + ", time=" + time + ", table=" + table + ", price=" + price + "]";
    }

}
