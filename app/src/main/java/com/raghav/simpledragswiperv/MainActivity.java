package com.raghav.simpledragswiperv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.raghav.library.DragSwipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DragSwipeAdapter.OnItemClick<UserModel> {

    RecyclerView recyclerView;
    RVAdapter listAdapter;
    List<UserModel> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        userList=new ArrayList<>();
        loadData();
        listAdapter = new RVAdapter(this,userList,R.layout.item_layout,
                recyclerView,
                true,false,RVAdapter.VERTICAL,
                16,this);
    }

    private void loadData(){
        userList.add(new UserModel("Raghav","8619146260","India"));
        userList.add(new UserModel("Jack","524575","UK"));
        userList.add(new UserModel("Tilak","995036482","India"));
        userList.add(new UserModel("Monica","514894","USA"));
        userList.add(new UserModel("Hayat","+1248546","Istanbul"));
    }

    @Override
    public void onClick(View view, int position, UserModel item) {
        Toast.makeText(this, position+"\n"+item.getName(), Toast.LENGTH_SHORT).show();
    }
}