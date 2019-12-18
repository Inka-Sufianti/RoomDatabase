package com.example.roomdatabase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.roomdatabase.R;
import com.example.roomdatabase.adapter.RecycleAdapter;
import com.example.roomdatabase.room.AppDatabase;
import com.example.roomdatabase.room.Mahasiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.roomdatabase.AppApplication.db;

public class DetailActivity extends AppCompatActivity {

    RecyclerView myRecyclerview;
    FloatingActionButton myFab;
    RecycleAdapter recycleAdapter;
    List<Mahasiswa> listMahasiswas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        myRecyclerview = findViewById(R.id.myRecyclerview);
        myFab = findViewById(R.id.fab);

        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }
    @Override
    public void onLongClick(int position) {
        FragmentManager fm = getSupportFragmentManager();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0; i < listMahasiswas.size(); i++) {
            Log.e("Aplikasi", listMahasiswas.get(i).getAlamat() + i);
            Log.e("Aplikasi", listMahasiswas.get(i).getKejuruan() + i);
            Log.e("Aplikasi", listMahasiswas.get(i).getNama() + i);
            Log.e("Aplikasi", listMahasiswas.get(i).getNim() + i);
        }
        Log.e("cek list", "" + listMahasiswas.size());
    }

    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        recycleAdapter = new RecycleAdapter(this, listMahasiswas);

    }

    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }
}
