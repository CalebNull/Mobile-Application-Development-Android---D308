package com.example.d308.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.d308.entity.Vacation;

import java.util.List;

@Dao
public interface VacationDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vacation vacation);

    @Update
    void update(Vacation vacation);

    @Delete
    void delete(Vacation vacation);

    @Query("SELECT * FROM vacations ORDER BY vacationId ASC")
    LiveData<List<Vacation>> getAllVacations();

    @Query("SELECT * FROM vacations WHERE vacationId = :id")
    LiveData<Vacation> getVacationById(int id);
}
