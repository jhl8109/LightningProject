package com.example.scedulealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.scedulealarm.item.Content;
import com.example.scedulealarm.item.MainAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Content> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        httpService init = new httpService();
        arrayList = new ArrayList<>();
        init.getEvent();
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.data_edit);
                Content content = new Content(edit.getText().toString(),false);
                init.postEvent(content);
                arrayList.add(content);
                mainAdapter.notifyDataSetChanged();
            }
        });

    }
    public class httpService {


        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.137.1:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        com.example.scedulealarm.Retrofit service1 = retrofit.create(com.example.scedulealarm.Retrofit.class);
        com.example.scedulealarm.Retrofit service2 = retrofit.create(com.example.scedulealarm.Retrofit.class);


        public void getEvent() {
            Call<List<SendData>> getCall = service1.getData();
            ArrayList<Content> retArray = new ArrayList<>();
            getCall.enqueue(new Callback<List<SendData>>() {
                @Override
                public void onResponse(Call<List<SendData>> call, Response<List<SendData>> response) {
                    if (response.isSuccessful()) {
                        List<SendData> receivedData = response.body();
                        Log.d("ddddddddddd : ", receivedData.toString());
                        Content content;
                        String sch;
                        Boolean b;
                        for (int i = 0; i < receivedData.size(); i++) {
                            sch = receivedData.get(i).getSchedule();
                            if (receivedData.get(i).getChecked() == 1) b = true;
                            else b = false;
                            content = new Content(sch, b);
                            arrayList.add(content);
                            mainAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<SendData>> call, Throwable t) {
                    t.printStackTrace();
                    Log.d("hello", "failllllllllllllllllll");
                }
            });
        }

        public void postEvent(Content content) {
            String schedule = content.getData();
            Boolean checked = content.isCheckBox();
            Call<SendData> postCall = service2.postData(schedule, checked);
            postCall.enqueue(new Callback<SendData>() {
                @Override
                public void onResponse(Call<SendData> call, Response<SendData> response) {
                    if (response.isSuccessful()) {
                        Log.d("okay : ", "Successe");
                    }
                }

                @Override
                public void onFailure(Call<SendData> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }
    }
}