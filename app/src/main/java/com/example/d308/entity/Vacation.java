package com.example.d308.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vacations")
public class Vacation {

    @PrimaryKey(autoGenerate = true)
    private int vacationId;
    private String title;
    private String hotelName;
    private String startDate;
    private String endDate;

    public Vacation(String title, String hotelName, String startDate, String endDate) {
        this.title = title;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getVacationId() { return vacationId; }
    public void setVacationId(int vacationId) { this.vacationId = vacationId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
