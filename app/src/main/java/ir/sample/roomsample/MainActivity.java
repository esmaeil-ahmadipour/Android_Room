package ir.sample.roomsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import ir.sample.roomsample.roomLayer.AppDatabase;
import ir.sample.roomsample.roomLayer.dataAccessObjects.UserDao;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

public class MainActivity extends AppCompatActivity {
    private String alertMessage;
    private int userId = 0;
    Context context = this;
    private Button btnConfirm;
    private EditText edtNumber, edtEmail, edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        listeners();
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
                    sendData(name, email, number);
                    intentLoadData();

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
        if ((!(edtEmail.getText().toString().contains("@"))) && !(edtEmail.getText().toString()+"" == "") ) {
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
    private void sendData(String name, String email, String number) {
        UserDao userData = AppDatabase.getInstance(context).user();
        UserEntity user = new UserEntity(userId, name, email, number);
        userData.insert(user);
    }

    // Intent to LoadDataActivity
    private void intentLoadData() {
        Intent intent = new Intent(MainActivity.this, LoadDataActivity.class);
        startActivity(intent);
    }

    private void setViews() {
        btnConfirm = (Button) findViewById(R.id.btn_main_confirm);
        edtName = (EditText) findViewById(R.id.edt_main_name);
        edtEmail = (EditText) findViewById(R.id.edt_main_email);
        edtNumber = (EditText) findViewById(R.id.edt_main_number);

    }
}
