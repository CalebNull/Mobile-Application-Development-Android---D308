package com.example.d308;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.d308.dao.ExcursionDAO;
import com.example.d308.dao.VacationDAO;
import com.example.d308.database.VacationDatabase;
import com.example.d308.entity.Excursion;
import com.example.d308.entity.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VacationRepository {

    private final VacationDAO vacationDAO;
    private final ExcursionDAO excursionDAO;
    private final LiveData<List<Vacation>> allVacations;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public VacationRepository(Application application) {
        VacationDatabase db = VacationDatabase.getInstance(application);
        vacationDAO = db.vacationDAO();
        excursionDAO = db.excursionDAO();
        allVacations = vacationDAO.getAllVacations();
    }

    public LiveData<List<Vacation>> getAllVacations() { return allVacations; }
    public LiveData<Vacation> getVacationById(int vacationId) {
        return vacationDAO.getVacationById(vacationId);
    }
    public LiveData<List<Excursion>> getExcursionsForVacation(int vacationId) {
        return excursionDAO.getExcursionsForVacation(vacationId);
    }

    public void insert(Vacation v) { executor.execute(() -> vacationDAO.insert(v)); }
    public void update(Vacation v) { executor.execute(() -> vacationDAO.update(v)); }
    public void delete(Vacation v) { executor.execute(() -> vacationDAO.delete(v)); }
}
