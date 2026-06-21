package com.example.d308;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.d308.entity.Vacation;

import java.util.Date;


public class VacationDetailActivity extends AppCompatActivity {

    private VacationRepository repository;
    private EditText editTitle,  editHotelName, editStartDate, editEndDate;
    private Vacation currentVacation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_detail);

        repository = new VacationRepository(getApplication());
        editTitle = findViewById(R.id.editVacationTitle);

        // B3a Displays detailed view of vacation information
        int vacationId = getIntent().getIntExtra("vacationId", -1);
        if (vacationId != -1) {
            repository.getVacationById(vacationId).observe(this, vacation -> {
                if (vacation != null) {
                    currentVacation = vacation;
                    editTitle.setText(vacation.getTitle());
                    editHotelName.setText(vacation.getHotelName());
                    editStartDate.setText(vacation.getStartDate());
                    editEndDate.setText(vacation.getEndDate());
                }
            });
        }

        findViewById(R.id.buttonSaveVacation).setOnClickListener(v -> saveVacation());
        findViewById(R.id.buttonDeleteVacation).setOnClickListener(v -> deleteVacation());
    }

    private void saveVacation() {
        // B3b Enter, Edit, and Delete vacation
        String title = editTitle.getText().toString().trim();
        String hotelName = editHotelName.getText().toString().trim();
        String startDate = editStartDate.getText().toString().trim();
        String endDate = editEndDate.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentVacation == null) {
            repository.insert(new Vacation(title, hotelName, startDate, endDate));
        } else {
            currentVacation.setTitle(title);
            currentVacation.setHotelName(hotelName);
            currentVacation.setStartDate(startDate);
            currentVacation.setEndDate(endDate);
            repository.update(currentVacation);
        }
        finish();
    }

    private void deleteVacation() {
        if (currentVacation == null) { finish(); return; }

        // B1b — block delete if excursions exist
        repository.getExcursionsForVacation(currentVacation.getVacationId())
                .observe(this, excursions -> {
                    if (excursions != null && !excursions.isEmpty()) {
                        Toast.makeText(this,
                                "Cannot delete: remove all excursions first",
                                Toast.LENGTH_LONG).show();
                    } else {
                        repository.delete(currentVacation);
                        finish();
                    }
                });
    }
}