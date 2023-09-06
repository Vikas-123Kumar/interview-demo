package com.infyu.interview_task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.infyu.interview_task.login.LoginActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView, logout;
    Context context;
    String[] name = {"Sonu", "Sohan", "Mohan", "Rohan", "Ram", "Arjun", "Moris", "Steven", "Kumar", "Singh"};
    String[] city = {"Ghaziabad", "Amroha", "Moradabad", "Hyderabad", "Siliguri", "Mumbai", "Kolkata", "GandhiNagar", "Delhi", "Bareilly"};
    int[] age = {21, 25, 15, 18, 35, 32, 45, 54, 18, 22};
    ArrayList<UserDataBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        init();
    }

    public void init() {
        recyclerView = findViewById(R.id.user_data);
        imageView = findViewById(R.id.menu_image);
        logout = findViewById(R.id.logout);
        arrayList = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            UserDataBean userDataBean = new UserDataBean();
            userDataBean.setName(name[i]);
            userDataBean.setAge(age[i]);
            userDataBean.setCity(city[i]);
            arrayList.add(userDataBean);
        }
        DataAdapter dataAdapter = new DataAdapter(context, arrayList, "");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(dataAdapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, imageView);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.data_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        if (menuItem.getTitle().toString().contains("name")) {
                            sortByNames();
                            DataAdapter dataAdapter = new DataAdapter(context, arrayList, "name");
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else if (menuItem.getTitle().toString().contains("age")) {
                            Collections.sort(arrayList, new SORT_BY_ID());
                            DataAdapter dataAdapter = new DataAdapter(context, arrayList, "age");
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else if (menuItem.getTitle().toString().contains("city")) {
                            sortByCity();
                            DataAdapter dataAdapter = new DataAdapter(context, arrayList, "city");
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Logout");
                alertDialog.setMessage("Do you want to logout?");
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences SM = getSharedPreferences("userLogin", 0);
                        SharedPreferences.Editor edit = SM.edit();
                        edit.putBoolean("userlogin", false);
                        edit.clear();
                        edit.commit();
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
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


    class SORT_BY_ID implements Comparator<UserDataBean> {
        public int compare(UserDataBean userDataBean, UserDataBean userDataBean2) {
            return userDataBean.getAge() - userDataBean2.getAge();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
