package com.example.d308.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.d308.dao.ExcursionDAO;
import com.example.d308.dao.VacationDAO;
import com.example.d308.entity.Excursion;
import com.example.d308.entity.Vacation;

@Database(entities = {Vacation.class, Excursion.class}, version = 2, exportSchema = false)
public abstract class VacationDatabase extends RoomDatabase {

    private static VacationDatabase instance;

    public abstract VacationDAO vacationDAO();
    public abstract ExcursionDAO excursionDAO();

    public static synchronized VacationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    VacationDatabase.class,
                    "vacation_database"
            ).fallbackToDestructiveMigration(true).build();
        }
        return instance;
    }
}
