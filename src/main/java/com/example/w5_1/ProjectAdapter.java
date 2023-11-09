package com.example.w5_1;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private Project[] projects;

    public ProjectAdapter(Project[] projects) {
        this.projects = projects;
    }

    @Override
    public int getItemCount() {
        return projects.length;
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projects[position];
        holder.bind(project);
    }


    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDescription;

        public ProjectViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.iv_project_icon);
            textViewTitle = view.findViewById(R.id.tv_project_title);
            textViewDescription = view.findViewById(R.id.tv_project_description);
        }

        public void bind(Project project) {
            imageView.setImageResource(project.getImageResourceId());
            textViewTitle.setText(project.getTitle());
            textViewDescription.setText(project.getDescription());
        }
    }
}
