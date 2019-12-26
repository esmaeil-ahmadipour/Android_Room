package ir.sample.roomsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import ir.sample.roomsample.roomLayer.AppDatabase;
import ir.sample.roomsample.roomLayer.dataAccessObjects.UserDao;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    private boolean isEditMode = false;
    private String alertMessage;
    private AppDatabase db;
    private int getIntentData ;
    private Bundle bundle;
    private Button btnConfirm;
    private EditText edtNumber, edtEmail, edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        db = AppDatabase.getInstance(context);
        bundle=getIntent().getExtras();
        if (bundle != null) {
            getIntentData=bundle.getInt("id");
            isEditMode = true;
            prepareEditMode();
        } else {
            isEditMode = false;
        }

        listeners();
    }

    private void prepareEditMode() {
        UserEntity dbGetData = db.user().getById(getIntentData);
        edtName.setText(dbGetData.name);
        edtEmail.setText(dbGetData.email);
        edtNumber.setText(String.valueOf(dbGetData.number));
    }

    private void listeners() {
        // OnClick btn_main_confirm :
        /*
           1-Check Validate Data ,
           2-Show AlertDialog For Errors ,
           3-Send Data To Database & Intent Other Activity
       */
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validator()) {
                    String name = edtName.getText().toString();
                    String email = edtEmail.getText().toString();
                    String number = edtNumber.getText().toString();
                    if (isEditMode) {
                        editData(getIntentData, name, email, number);
                    } else {
                        addData(name, email, number);
                    }
                    // Load Data in TextView (ForTest)
                    /*
                       intentLoadData()
                    */
                    finish();
                } else {
                    Toast.makeText(context, alertMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Check Validate Data Entries And Set Acceptance for them.
    private boolean validator() {
        boolean validate = true;
        if (edtName.getText().toString() == "") {
            validate = false;
            alertMessage = context.getResources().getString(R.string.string_alert_edtUsername);
        }
        if ((!(edtEmail.getText().toString().contains("@"))) && !(edtEmail.getText().toString() + "" == "")) {
            validate = false;
            alertMessage = context.getResources().getString(R.string.string_alert_edtEmail);
        }
        if (edtNumber.getText().toString() == "") {
            validate = false;
            alertMessage = context.getResources().getString(R.string.string_alert_edtNumber);
        }
        return validate;
    }

    //Pass Received Data To New UserEntity Object And Use This Object To Insert Data.
    private void addData(String name, String email, String number) {
        UserDao userData = AppDatabase.getInstance(context).user();
        UserEntity user = new UserEntity(name, email, number);
        userData.insert(user);
    }

    private void editData(int id, String name, String email, String number) {
        UserDao userData = AppDatabase.getInstance(context).user();
        UserEntity user = new UserEntity(id, name, email, number);
        userData.update(user);
    }

    // Intent to LoadDataActivity
    /*
    private void intentLoadData() {
        Intent intent = new Intent(MainActivity.this, LoadDataActivity.class);
        startActivity(intent);
    }
    */
    private void setViews() {
        btnConfirm = (Button) findViewById(R.id.btn_main_confirm);
        edtName = (EditText) findViewById(R.id.edt_main_name);
        edtEmail = (EditText) findViewById(R.id.edt_main_email);
        edtNumber = (EditText) findViewById(R.id.edt_main_number);

    }
}
