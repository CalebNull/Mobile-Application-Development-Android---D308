package com.example.d308;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.d308.adapter.VacationAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VacationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        VacationRepository repo = new VacationRepository(getApplication());
        VacationAdapter adapter = new VacationAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewVacations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        repo.getAllVacations().observe(this, adapter::setVacations);

        FloatingActionButton fab = findViewById(R.id.fabAddVacation);
        fab.setOnClickListener(v -> startActivity(new Intent(this, VacationDetailActivity.class)));
    }
}
