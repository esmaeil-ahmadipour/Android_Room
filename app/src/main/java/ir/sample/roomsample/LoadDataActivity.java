package ir.sample.roomsample;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import ir.sample.roomsample.roomLayer.AppDatabase;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

public class LoadDataActivity extends AppCompatActivity {
    Context context = this;
    private TextView txtDataViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        setViews();
        loadingData();
    }

    //GetAllData from Database And Show Result inside a TextView
    private void loadingData() {
        AppDatabase db = AppDatabase.getInstance(context);
        List<UserEntity> dbGetAllDatabase = db.user().getAll();
        StringBuilder builder = new StringBuilder();
        for (UserEntity item : dbGetAllDatabase) {
            builder.append(item.id + " , " + item.name + " , " + item.email + " , " + item.number + "\n");
        }
        txtDataViewer.setText(builder.toString());
    }

    private void setViews() {
        txtDataViewer = (TextView) findViewById(R.id.txt_load_data);
    }
}
