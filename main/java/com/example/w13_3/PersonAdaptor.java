package com.example.w13_3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w13_3.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class PersonAdaptor extends RecyclerView.Adapter<PersonAdaptor.PersonViewHolder> {

    ArrayList<Person> personArrayList;
    public PersonAdaptor(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.binding.rcvTxtName.setText(personArrayList.get(position).name);
        holder.binding.rcvTxtLocation.setText(personArrayList.get(position).location);
        String theName = personArrayList.get(position).name;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to detail activity
                Intent intent = new Intent(holder.itemView.getContext(), DBActivity.class);
                intent.putExtra("personName", theName);
                intent.putExtra("info", "old");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        RecyclerRowBinding binding;
        public PersonViewHolder(RecyclerRowBinding binding) {  //RecyclerRowBinding
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

