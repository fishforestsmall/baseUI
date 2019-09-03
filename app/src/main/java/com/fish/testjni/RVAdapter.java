package com.fish.testjni;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVHolder>{

    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.second, viewGroup, false);
        RVHolder rvHolder = new RVHolder(view);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolder rvHolder, int i) {
        int a = i;
    }

    @Override
    public int getItemCount() {
        return 100;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class RVHolder extends RecyclerView.ViewHolder {
        public RVHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
