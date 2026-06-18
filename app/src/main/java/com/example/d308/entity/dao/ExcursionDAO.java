package com.example.d308.entity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.example.d308.entity.Excursion;

import java.util.List;

@Dao
public interface ExcursionDAO {
    @Query("SELECT * FROM excursions WHERE vacationId = :vacationId")
    LiveData<List<Excursion>> getAllExcursionsForVacation(int vacationId);
}
