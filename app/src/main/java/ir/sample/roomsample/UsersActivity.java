package ir.sample.roomsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.sample.roomsample.roomLayer.AppDatabase;
import ir.sample.roomsample.roomLayer.entities.UserEntity;
import ir.sample.roomsample.utils.MainAdapter;

import android.view.View;

import java.util.List;

public class UsersActivity extends AppCompatActivity {
Context context = this;
    private String alertMessage;
    private  int userId;
    AppDatabase db ;
    private FloatingActionButton fabMain;
    private RecyclerView rcvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        db=AppDatabase.getInstance(context);

        setViews();
        viewInitializer();
        listeners();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        loadUsers();
    }

    void listeners(){
        // OnClick  Fab : fab_users_main
        /*
        */
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentLoadData();
            }
        });

    }
    private void viewInitializer() {
        initRecyclerView();
    }


    private void initRecyclerView() {
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        rcvMain.setItemAnimator(itemAnimator);
        //Configuration RecyclerView
        rcvMain.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        loadUsers();
    }

    private List<UserEntity> getUsers() {
        List<UserEntity> usersList=db.user().getAll();
        return usersList;
    }

    private void loadUsers() {
        List<UserEntity> usersList=getUsers();
        MainAdapter mainAdapter = new MainAdapter(usersList, context);
        rcvMain.setAdapter(new MainAdapter(usersList,context));
        mainAdapter.notifyDataSetChanged();
    }

    // Intent to LoadDataActivity
    private void intentLoadData() {
        Intent loadData = new Intent(context , MainActivity.class);
        startActivity(loadData);
    }

    void setViews(){
        fabMain= (FloatingActionButton) findViewById(R.id.fab_users_main);
        rcvMain=(RecyclerView) findViewById(R.id.rcv_contentUsers_main);
    }

    // Intent to LoadDataActivity
    private  void initentLoadData(){
        Intent intent = new Intent(context , MainActivity.class);
        startActivity(intent);
    }
}
