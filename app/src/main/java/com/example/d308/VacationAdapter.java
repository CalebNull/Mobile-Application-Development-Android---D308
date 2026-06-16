package com.example.d308;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.d308.entity.Vacation;
import java.util.List;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {
    private List<Vacation> vacations;
    private final Context context;

    public VacationAdapter(Context context, List<Vacation> vacations) {
        this.context = context;
        this.vacations = vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vacation, parent, false);
        return new VacationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationViewHolder holder, int position) {
        Vacation v = vacations.get(position);
        holder.titleText.setText(v.title);
        holder.datesText.setText(v.startDate + " - " + v.endDate);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, VacationDetailActivity.class);
            intent.putExtra("vacationId", v.vacationId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return vacations == null ? 0 : vacations.size(); }

    static class VacationViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, datesText;
        VacationViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_vacation_title);
            datesText = itemView.findViewById(R.id.text_vacation_dates);
        }
    }
}