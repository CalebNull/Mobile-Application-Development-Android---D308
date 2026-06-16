package com.example.d308;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class VacationListActivity extends AppCompatActivity {
    private VacationRepository repository;
    private VacationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        repository = new VacationRepository(this);
        adapter = new VacationAdapter(this, new ArrayList<>());

        RecyclerView recyclerView = findViewById(R.id.recycler_vacations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_vacation);
        fab.setOnClickListener(v -> startActivity(new Intent(this, VacationDetailActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(() -> {
            var list = repository.getAllVacations();
            runOnUiThread(() -> adapter.setVacations(list));
        }).start();
    }
}