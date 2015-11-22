package com.example.franciscogutierrez.hackathon;

public class FlashCard {
    private String front;
    private String back;

    public FlashCard(String front, String back)
    {
        this.front = front;//front side
        this.back = back;//back side
    }

    public String getFront(){
        return front;
    }

    public String getBack(){
        return back;
    }
}
