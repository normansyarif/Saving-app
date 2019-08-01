package io.github.normansyarif.mysaving;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

//        insert();
        showAll();
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void addPiggy(View v) {
        openActivity(AddPiggyActivity.class);
    }

    public void movePiggy(View v) {
        openActivity(MovePiggyActivity.class);
    }

    public void minPiggy(View v) {
        openActivity(MinPiggyActivity.class);
    }

    public void addBank(View v) {
        openActivity(AddBankActivity.class);
    }

    public void minBank(View v) {
        openActivity(MinBankActivity.class);
    }

    public void setGoal(View v) {
        openActivity(GoalActivity.class);
    }


    private void showAll() {
        Cursor res = myDb.getFromDb("col_goal_desc, col_goal_target");
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Id :").append(res.getString(0)).append("\n");
            buffer.append("piggy :").append(res.getString(1)).append("\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }



    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void insert() {
        boolean isInserted = myDb.insertData(1, 1000, 2000, "Descript", 3000);
        if(isInserted == true)
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }
}
