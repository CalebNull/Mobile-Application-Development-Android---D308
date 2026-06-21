package com.example.d308;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.d308.entity.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class VacationDetailActivity extends AppCompatActivity {

    private VacationRepository repository;
    private EditText editTitle,  editHotelName, editStartDate, editEndDate;
    private Vacation currentVacation;

    // 3Bc date format
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");


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

                    // 3Bc date format validation
                    editStartDate.setOnClickListener(v -> showDatePicker(editStartDate));
                    editEndDate.setOnClickListener(v -> showDatePicker(editEndDate));
                }
            });
        }

        findViewById(R.id.buttonSaveVacation).setOnClickListener(v -> saveVacation());
        findViewById(R.id.buttonDeleteVacation).setOnClickListener(v -> deleteVacation());
    }

    // 3Bc date format validation
    private void showDatePicker(EditText editDate) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            calendar.set(year, month, day);
            editDate.setText(DATE_FORMAT.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    private void saveVacation() {
        // B3b Enter, Edit, and Delete vacation
        String title = editTitle.getText().toString().trim();
        String hotelName = editHotelName.getText().toString().trim();
        String start = editStartDate.getText().toString().trim();
        String end = editEndDate.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show();
            return;
        }

        Date startDate = parseDate(start);
        Date endDate = parseDate(end);
        if (startDate == null || endDate == null) {
            Toast.makeText(this, "Dates must be in MM/dd/yyyy format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!endDate.after(startDate)) {
            Toast.makeText(this, "End date must be after start date", Toast.LENGTH_SHORT).show();
        }

        if (currentVacation == null) {
            repository.insert(new Vacation(title, hotelName, start, end));
        } else {
            currentVacation.setTitle(title);
            currentVacation.setHotelName(hotelName);
            currentVacation.setStartDate(start);
            currentVacation.setEndDate(end);
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