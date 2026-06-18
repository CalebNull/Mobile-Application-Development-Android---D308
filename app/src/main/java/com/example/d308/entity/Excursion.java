package com.example.d308.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "excursions")
public class Excursion {

    @PrimaryKey(autoGenerate = true)
    private int excursionId;
    private int vacationId;
    private String title;

    public Excursion(int vacationId, String title) {
        this.vacationId = vacationId;
        this.title = title;
    }

    public int getExcursionId() { return excursionId; }
    public void setExcursionId(int excursionId) {this.excursionId = excursionId; }
    public int getVacationId() { return vacationId; }
    public void getVacationId(int vacationId) { this.vacationId = vacationId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
