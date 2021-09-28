package com.company.roomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.company.roomdb.RoomDB.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private Context context;
    List<User> UserList;

    public UserListAdapter(Context context){
        this.context=context;
    }

    public void setUserList(List<User> userList){
        this.UserList=userList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_element,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(this.UserList.get(position).firstName);
        holder.lastname.setText(this.UserList.get(position).lastName);

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView lastname;
        public UserViewHolder(View view){
            super(view);
          name = view.findViewById(R.id.Name);
           lastname=view.findViewById(R.id.LastName);
        }
    }
}
