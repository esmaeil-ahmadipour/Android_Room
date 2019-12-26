package ir.sample.roomsample.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.sample.roomsample.MainActivity;
import ir.sample.roomsample.R;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    Context context;
    List<UserEntity> usersList;

    //Create Constructor for Get List of data.
    public MainAdapter(List<UserEntity> usersList , Context context) {
        //Get Data.
        this.usersList = usersList;
        this.context=context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Make contact RecyclerView with model_layout by Return View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_layout, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        //Get Position for ViewAllData From Database
        final UserEntity users = usersList.get(position);

        //Set Value  ModelItems
        holder.txtTitle.setText(users.name);
        holder.cardMain.setBackgroundResource(R.drawable.background_model);
        holder.imgIcon.setImageResource(R.drawable.ic_edit_user);

        //Set Listeners
        holder.imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent EditMode
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", users.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Detect Qty News in NewsList.
        return usersList.size();
    }

    //Define RecyclerView .
    public class MainViewHolder extends RecyclerView.ViewHolder {

        //set ModelItems
        CardView cardMain;
        TextView txtTitle;
        ImageView imgIcon;


        public MainViewHolder(@NonNull final View itemView) {
            super(itemView);

            //Get View From ViewHolder
            context = itemView.getContext();

            //Initialize & Cast ModelItems
            cardMain = (CardView) itemView.findViewById(R.id.card_model_main);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_model_title);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_model_main);

        }
    }
}
