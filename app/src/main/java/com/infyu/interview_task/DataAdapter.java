package com.infyu.interview_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserDataBean> arrayList;
    String type;

    public DataAdapter(Context context, ArrayList<UserDataBean> arrayList, String type) {
        this.context = context;
        this.arrayList = arrayList;
        this.type = type;
    }

    @NonNull
    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_data_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.city.setText(arrayList.get(position).getCity());
        holder.age.setText(arrayList.get(position).getAge() + "");
//        if (type.equals("name")) {
//            sortByNames();
//            notifyDataSetChanged();
//        } else if (type.equals("age")) {
//            sortByAge();
//            notifyDataSetChanged();
//        } else if (type.equals("city")) {
//            sortByCity();
//            notifyDataSetChanged();
//        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void sortByNames() {
        Collections.sort(arrayList, new Comparator<UserDataBean>() {
            @Override
            public int compare(UserDataBean person1, UserDataBean person2) {
                return person1.getName().compareTo(person2.getName());
            }
        });
//        notifyDataSetChanged(); // Notify the RecyclerView of the data change
    }

    public void sortByCity() {
        Collections.sort(arrayList, new Comparator<UserDataBean>() {
            @Override
            public int compare(UserDataBean city1, UserDataBean city2) {
                return city1.getCity().compareTo(city2.getCity());
            }
        });
//        notifyDataSetChanged(); // Notify the RecyclerView of the data change
    }

    public void sortByAge() {
        Collections.sort(arrayList, new Comparator<UserDataBean>() {
            @Override
            public int compare(UserDataBean age1, UserDataBean age2) {
                return String.valueOf(age1.getAge()).compareTo(String.valueOf(age1.getAge()));
            }
        });
//        notifyDataSetChanged(); // Notify the RecyclerView of the data change
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, city;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            city = itemView.findViewById(R.id.city);

        }
    }
}
