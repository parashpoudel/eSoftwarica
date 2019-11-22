package fragment;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.students.R;
import com.example.students.ViewPagerActivity;

import helper.MyHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFormFragment extends Fragment {

    private EditText etName, etAge, etAddress;
    private RadioButton rbGender;
    private RadioGroup rgGender;
    private Button btnSave;
    private String Gender;
    public StudentFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_student_form, container, false);

        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etAddress = view.findViewById(R.id.etAddress);
        rgGender = view.findViewById(R.id.rgGender);


        btnSave = view.findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer gender = rgGender.getCheckedRadioButtonId();
                rbGender = view.findViewById(gender);


                saveStudent();

                emptyedittext();
            }
        });

        return view;


    }

    private void emptyedittext() {

        etName.setText("");
        etAge.setText("");
        etAddress.setText("");
        rbGender.setChecked(false);
    }

    private void saveStudent() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String address = etAddress.getText().toString();
        String gender = rbGender.getText().toString();


        if (name.isEmpty()) {

            etName.setError("Field is empty");
        }

        else if(age.isEmpty()) {
            etAge.setError("Field is empty");
        }
        else if(address.isEmpty()) {
            etAddress.setError("Field is empty");
        }
        else{

            final MyHelper myHelper = new MyHelper(this.getContext());
            final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

            boolean status = myHelper.InsertData(name, age, address, gender, sqLiteDatabase);


            if (status) {
                Toast.makeText(this.getActivity(), "Student Added Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this.getContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        }
    }

}
