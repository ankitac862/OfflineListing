package com.anki.offlinelisting.remote.pojo;

public class Name {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    private String title;
    private String first;
    private String last;

    @Override
    public String toString() {
        return title + " " + first + " " + last;
    }
}
