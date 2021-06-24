package com.example.notes;

public class Products {
    private  int id;
    private String title;
    private String note;

    public Products(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public Products(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public Products() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
