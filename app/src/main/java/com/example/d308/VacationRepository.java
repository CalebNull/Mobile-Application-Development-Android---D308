package com.example.d308;

import android.content.Context;
import com.example.d308.database.VacationDatabase;
import com.example.d308.entity.Excursion;
import com.example.d308.entity.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VacationRepository {
    private final VacationDatabase db;
    private final ExecutorService executor =  Executors.newSingleThreadExecutor();

    public VacationRepository(Context context) {
        db = VacationDatabase.getInstance(context);
    }

    public List<Vacation> getAllVacations() {
        try {
            return executor.submit(() -> db.vacationDAO().getAllVacations()).get();
        } catch (Exception e) { return null; }
    }

    public Vacation getVacationById(int id) {
        try {
            return executor.submit(() -> db.vacationDAO().getVacationById(id)).get();
        } catch (Exception e) { return null; }
    }

    public void insert(Vacation vacation) { executor.execute(() -> db.vacationDAO().insert(vacation)); }
    public void update(Vacation vacation) { executor.execute(() -> db.vacationDAO().update(vacation)); }
    public void delete(Vacation vacation) { executor.execute(() -> db.vacationDAO().delete(vacation)); }

    public List<Excursion> getAllExcursionsForVacation(int vacationId) {
        try {
            return executor.submit(() -> db.excursionDAO().getExcursionsForVacation(vacationId)).get();
        } catch (Exception e) { return null; }
    }
}
