package com.example.demo.model;

import lombok.Data;

@Data
public class Flight {
    private String no;
    private int departure;
    private String from;
    private String to;
    private int duration;
}
