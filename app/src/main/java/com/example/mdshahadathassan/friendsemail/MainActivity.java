package com.example.mdshahadathassan.friendsemail;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,name,email;
    Button del,save,update,view;
    MySqlLite mySqlLite ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=(EditText)findViewById(R.id.Id);
        name=(EditText)findViewById(R.id.Name);
        email=(EditText)findViewById(R.id.Emial);
        save=(Button)findViewById(R.id.Save);
        view=(Button)findViewById(R.id.View);
        update=(Button)findViewById(R.id.Update);
        del=(Button)findViewById(R.id.Delete);
        mySqlLite= new MySqlLite(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean chk = mySqlLite.addToTable(id.getText().toString(), name.getText().toString(), email.getText().toString());
                if (chk == true) {
                    Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });



    }
    //working without setting On click listener
    public void viewData(View v){
        Cursor result = mySqlLite.display();
        if(result.getCount()==0){
            showMsg("Error !", "No DATA Found");
            return;
        }

        StringBuffer buffer= new StringBuffer();
        result.moveToFirst();
        do{
            buffer.append("ID: "+result.getString(0)+"\n");
            buffer.append("Name: "+result.getString(1)+"\n");
            buffer.append("Email Id: "+result.getString(2)+"\n");
        }while (result.moveToNext());

        showMsg("Data",buffer.toString());


    }
    public void showMsg(String title,String msg){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.show();
    }
}
