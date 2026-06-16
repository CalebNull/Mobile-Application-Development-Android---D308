package com.example.d308.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vacations")
public class Vacation {
    @PrimaryKey(autoGenerate = true)
    public int vacationId;
    public String title;
    public String hotelName;
    public String startDate;
    public String endDate;
}