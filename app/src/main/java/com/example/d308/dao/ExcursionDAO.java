package com.example.d308.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.d308.entity.Excursion;

import java.util.List;

@Dao
public interface ExcursionDAO {
    @Query("SELECT * FROM excursions WHERE vacationId = :vacationId")
    List<Excursion> getExcursionsForVacation(int vacationId);
}