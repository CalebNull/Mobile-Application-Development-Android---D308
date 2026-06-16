package com.example.d308.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.d308.entity.Vacation;

import java.util.List;

@Dao
public interface VacationDAO {
    @Insert
    void insert(Vacation vacation);

    @Update
    void update(Vacation vacation);

    @Delete
    void delete(Vacation vacation);

    @Query("SELECT * FROM vacations ORDER BY startDate ASC")
    List<Vacation> getAllVacations();

    @Query("SELECT * FROM vacations WHERE vacationId = :id")
    Vacation getVacationById(int id);
}