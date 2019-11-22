package adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.students.R;
import com.example.students.ViewPagerActivity;
import com.google.android.gms.common.util.Base64Utils;

import java.util.List;

import helper.MyHelper;
import model.Student;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListViewHolder>{

    Context mContext;
    List<Student> studentList;

    public StudentListAdapter(Context mContext, List<Student> studentList) {
        this.mContext = mContext;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list,parent,false);
        return new StudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, final int position) {
        final Student student = studentList.get(position);
        holder.tvName.setText(student.getName());
        holder.tvAge.setText(student.getAge());
        holder.tvGender.setText(student.getGender());
        holder.tvAddress.setText(student.getAddress());

        if (student.getGender().equals("Male")){
            holder.ivImage.setImageResource(R.drawable.man);
        }else if(student.getGender().equals(("Female"))){
            holder.ivImage.setImageResource(R.drawable.women);

        }
        else {
            holder.ivImage.setImageResource(R.drawable.six);
        }
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHelper myHelper = new MyHelper(mContext);
                final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                myHelper.deleteStudent(student);

                Toast.makeText(mContext,"Deleted",Toast.LENGTH_LONG).show();

                studentList.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentListViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvAge, tvGender, tvAddress;

        ImageView ivImage;

        Button btnDelete;

        public StudentListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            ivImage = itemView.findViewById(R.id.imgStudent);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
