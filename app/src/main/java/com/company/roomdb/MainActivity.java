package com.company.roomdb;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.company.roomdb.RoomDB.Database;
import com.company.roomdb.RoomDB.ListViewModel;
import com.company.roomdb.RoomDB.Repository;
import com.company.roomdb.RoomDB.User;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private ListViewModel listViewModel;
private Button button;
private UserListAdapter userListAdapter;

private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository.initDataBase(getApplication());
        button=findViewById(R.id.show);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,add_new_user.class),100);
            }
        });

        listViewModel=new ViewModelProvider(this).get(ListViewModel.class);
        initRecyclerView();
        loadUserList();


    }
    private void loadUserList(){
       //Не рабочий вариант почему то
        LiveData<List<User>> liveUser;

       liveUser =listViewModel.getListFromViewModel();
        Log.d(TAG,"Переменная живойдаты "+liveUser.getValue());
       liveUser.observe(this, new Observer<List<User>>() {
           @Override
           public void onChanged(List<User> users) {
               Log.d(TAG,"Переменная живойдаты после обзервера "+liveUser);

               userListAdapter.setUserList(users);
           }
       });


//        listViewModel.getListFromViewModel().observe(this,(List<User> user)->{
//            userListAdapter.setUserList(user);
//        });

    }
    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        userListAdapter=new UserListAdapter(getApplicationContext());
        recyclerView.setAdapter(userListAdapter);
        //почему то это говно заработало...
        Log.d(TAG,"Переменная живойдаты  "+listViewModel.getListFromViewModel().getValue());
        listViewModel.getListFromViewModel().observe(this,(List<User> user)->{
            userListAdapter.setUserList(user);
        });

        //Database database = Database.getInstance(this.getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listViewModel.deleteParty(((UserListAdapter)recyclerView.getAdapter()).UserList.get(position));
               // database.userDAO().DeleteUser(((UserListAdapter)recyclerView.getAdapter()).UserList.get(position));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100){
            loadUserList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}