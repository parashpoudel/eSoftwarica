package fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.students.R;

import java.util.ArrayList;
import java.util.List;

import adapter.StudentListAdapter;
import helper.MyHelper;
import model.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment {
private RecyclerView rvStudent;



    public StudentListFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        rvStudent = view.findViewById(R.id.rvStudent);





        final MyHelper myHelper = new MyHelper(this.getContext());
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Student> studentList = new ArrayList<>();
        studentList = myHelper.GetAllStudent(sqLiteDatabase);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this.getContext(),studentList);
        rvStudent.setAdapter(studentListAdapter);
        rvStudent.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));

        return view;
    }

}
