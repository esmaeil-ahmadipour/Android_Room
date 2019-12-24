package ir.sample.roomsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ir.sample.roomsample.roomLayer.AppDatabase;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    void test() {
        AppDatabase db = AppDatabase.getInstance(this);
// Test : Insert New Record To Database
/*
        UserEntity userSampleInsert = new UserEntity("User1","User1@gmail.com","09112223344");
        db.user().insert(userSampleInsert);
        List<UserEntity> dbGetAllData = db.user().getAll();
*/


// Test : Update One Record From Database
/*
        UserEntity userSampleUpdate = new UserEntity(1,"John","John@gmail.com","09012223344");
        db.user().update(userSampleUpdate);
        List<UserEntity> dbGetAllData = db.user().getAll();
 */

        UserEntity dbGetData=db.user().getById(1);
    }
}
