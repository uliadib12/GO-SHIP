package com.adib.go_ship.AdapterRecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adib.go_ship.R;

public class MenungguAdapter extends RecyclerView.Adapter<MenungguAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView id, tanggal, layanan, nama;
        protected View view;

        public ViewHolder(View view) {
            super(view);
            id = (TextView)view.findViewById(R.id.id_pemesan);
            tanggal = (TextView)view.findViewById(R.id.tanggal);
            layanan = (TextView)view.findViewById(R.id.layanan);
            nama = (TextView)view.findViewById(R.id.nama_pemesan);

            this.view = view;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_history_menunggu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), String.valueOf(position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
