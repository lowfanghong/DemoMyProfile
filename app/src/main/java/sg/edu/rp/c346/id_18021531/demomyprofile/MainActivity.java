package sg.edu.rp.c346.id_18021531.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
       RadioGroup rgGender;
    EditText etName, etGPA;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        rgGender = findViewById(R.id.RadioGroupGender);
        etGPA = findViewById(R.id.editTextGPA);
        btnSave = findViewById(R.id.buttonSave);

      btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String StrName  = etName.getText().toString();
              Float gpa  = Float.parseFloat(etGPA.getText().toString());
              int intGenderId = rgGender.getCheckedRadioButtonId();
              SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences
                      (MainActivity.this);
              SharedPreferences.Editor prefEdit = prefs.edit();
              prefEdit.putString("Name", StrName);
              prefEdit.putFloat("gpa",gpa);
              prefEdit.putInt("GenderId",intGenderId);

              prefEdit.commit();
          }
      });

    }

    @Override
    protected void onPause() {
        super.onPause();
   String StrName  = etName.getText().toString();
   Float gpa  = Float.parseFloat(etGPA.getText().toString());
   int intGenderId = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("Name", StrName);
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putInt("GenderId",intGenderId);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String msg = prefs.getString("Name","");
          Float gpa = prefs.getFloat("gpa",0);
          int GenderId = prefs.getInt("GenderId",R.id.radioButtonGenderMale);

       etName.setText(msg);
       etGPA.setText(gpa +"");
       rgGender.check(GenderId);

        Toast toast = Toast.makeText(MainActivity.this,msg + gpa,Toast.LENGTH_LONG);
        toast.show();

    }


}
