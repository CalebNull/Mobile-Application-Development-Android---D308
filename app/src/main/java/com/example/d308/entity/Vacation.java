package com.example.d308.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vacations")
public class Vacation {

    @PrimaryKey(autoGenerate = true)
    private int vacationId;
    private String title;

    public Vacation(String title) {
        this.title = title;
    }

    public int getVacationId() { return vacationId; }
    public void setVacationId(int vacationId) { this.vacationId = vacationId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
