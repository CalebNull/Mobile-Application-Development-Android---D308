package com.example.d308.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "excursions")
public class Excursion {
    @PrimaryKey(autoGenerate = true)
    public int excursionId;
    public int vacationId;
    public String title;
}