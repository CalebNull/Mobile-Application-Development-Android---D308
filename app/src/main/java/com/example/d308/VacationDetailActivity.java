package com.example.d308;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.d308.entity.Excursion;
import com.example.d308.entity.Vacation;
import java.util.List;

public class VacationDetailActivity extends AppCompatActivity {
    private EditText editTitle, editHotel, editStart, editEnd;
    private VacationRepository repository;
    private Vacation currentVacation;
    private int vacationId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_detail);

        repository = new VacationRepository(this);
        editTitle = findViewById(R.id.edit_title);
        editHotel = findViewById(R.id.edit_hotel);
        editStart = findViewById(R.id.edit_start_date);
        editEnd = findViewById(R.id.edit_end_date);

        vacationId = getIntent().getIntExtra("vacationId", -1);
        if (vacationId != -1) {
            new Thread(() -> {
                currentVacation = repository.getVacationById(vacationId);
                runOnUiThread(() -> {
                    editTitle.setText(currentVacation.title);
                    editHotel.setText(currentVacation.hotelName);
                    editStart.setText(currentVacation.startDate);
                    editEnd.setText(currentVacation.endDate);
                });
            }).start();
        }

        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(v -> saveVacation());

        Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(v -> deleteVacation());
    }

    private void saveVacation() {
        if (currentVacation == null) currentVacation = new Vacation();
        currentVacation.title = editTitle.getText().toString().trim();
        currentVacation.hotelName = editHotel.getText().toString().trim();
        currentVacation.startDate = editStart.getText().toString().trim();
        currentVacation.endDate = editEnd.getText().toString().trim();

        if (vacationId == -1) {
            repository.insert(currentVacation);
        } else {
            repository.update(currentVacation);
        }
        finish();
    }

    private void deleteVacation() {
        if (currentVacation == null) { finish(); return; }
        new Thread(() -> {
            List<Excursion> excursions = repository.getAllExcursionsForVacation(currentVacation.vacationId);
            runOnUiThread(() -> {
                if (excursions != null && !excursions.isEmpty()) {
                    Toast.makeText(this,
                            "Cannot delete: vacation has associated excursions.",
                            Toast.LENGTH_LONG).show();
                } else {
                    repository.delete(currentVacation);
                    finish();
                }
            });
        }).start();
    }
}