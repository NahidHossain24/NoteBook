package com.example.nahid.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;

    TextView nametext, surname, marks;
    EditText editName, editSurname,editMarks, editTextid;
    Button btnAddData;
    Button btnViewall;
    Button buttondelete;
    Button buttondpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        editName= findViewById(R.id.editText_name);
        editSurname= findViewById(R.id.editText_surname);
        editMarks= findViewById(R.id.editText_marks);
        editTextid=findViewById(R.id.editTextid);

        btnAddData = findViewById(R.id.button_add);
        btnViewall= findViewById(R.id.button_viewall);
        buttondelete=findViewById(R.id.button_delete);
        buttondpdate=findViewById(R.id.button_update);
        AddData();
        viewAll();
        Delete();
        Update();

    }

    private void Update() {
        buttondpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = mydb.updateData(editTextid.getText().toString(),editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());

                if (isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Data is Updated", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "Data is not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void Delete() {
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows= mydb.deleteData(editTextid.getText().toString());

                if (deleteRows>0){
                    Toast.makeText(MainActivity.this, "Data is Deleted", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "Data is not Deleted", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    private void viewAll() {
        btnViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getAllData();
                if (res.getCount()== 0){
                    showMessage("Error","Nothing Found");

                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Serial:"+res.getString(0)+"\n");
                    buffer.append("Date:"+res.getString(3)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Note:"+res.getString(2)+"\n\n");

                }
                showMessage("Data",buffer.toString());

            }
        });
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    private void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted= mydb.insertData(editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                if (isInserted =true){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}