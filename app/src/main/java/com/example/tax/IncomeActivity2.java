package com.example.tax;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class IncomeActivity2 extends AppCompatActivity {

    int A;


    EditText i;
    EditText i2;
    EditText i3;
    EditText i4;
    EditText d1;
    EditText d2;
    EditText d3;
    EditText d4;
    TextView tx1;
    Button b1;
    Button view;
    Button add;
    EditText b;
    EditText age;
    DataBaseHelper mDatabasehelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income2);
        age = (EditText) findViewById(R.id.b);//age
        i = (EditText) findViewById(R.id.edit1);  //income from salary
        i2 = (EditText) findViewById(R.id.edit2); //income from house property
        i3 = (EditText) findViewById(R.id.edit3); //income from other source
        i4 = (EditText) findViewById(R.id.edit4); //income from capital gain
        d1 = (EditText) findViewById(R.id.edit5); //deduction 1
        d2 = (EditText) findViewById(R.id.edit6); //2
        d3 = (EditText) findViewById(R.id.edit7); //3
        d4 = (EditText) findViewById(R.id.edit8); //4
        tx1 = (TextView) findViewById(R.id.text2); //result
        b1 = (Button) findViewById(R.id.button3); //calculate
        view = (Button) findViewById(R.id.button4);
        add = (Button) findViewById(R.id.button5);

        mDatabasehelper = new DataBaseHelper(this);
        AddData();
        viewAll();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String Age = age.getText().toString();
                if (TextUtils.isEmpty(Age)) {
                    age.setError("Enter Your Age");
                    age.requestFocus();
                    return;
                }

                String I1 = i.getText().toString();
                if (TextUtils.isEmpty(I1)) {
                    i.setError("You Miss Something");
                    i.requestFocus();
                    return;
                }
                String I2 = i2.getText().toString();
                if (TextUtils.isEmpty(I2)) {
                    i2.setError("You Miss Something");
                    i2.requestFocus();
                    return;
                }
                String I3 = i3.getText().toString();
                if (TextUtils.isEmpty(I3)) {
                    i3.setError("You Miss Something");
                    i3.requestFocus();
                    return;
                }
                String I4 = i4.getText().toString();
                if (TextUtils.isEmpty(I4)) {
                    i4.setError("You Miss Something");
                    i4.requestFocus();
                    return;
                }
                String D1 = d1.getText().toString();
                if (TextUtils.isEmpty(D1)) {
                    d1.setError("You Miss Something");
                    d1.requestFocus();
                    return;
                }
                String D2 = d2.getText().toString();
                if (TextUtils.isEmpty(D2)) {
                    d2.setError("You Miss Something");
                    d2.requestFocus();
                    return;
                }
                String D3 = d3.getText().toString();
                if (TextUtils.isEmpty(D3)) {
                    d3.setError("You Miss Something");
                    d3.requestFocus();
                    return;
                }
                String D4 = d4.getText().toString();
                if (TextUtils.isEmpty(D4)) {
                    d4.setError("You Miss Something");
                    d4.requestFocus();
                    return;

                }


                try {
                    Calculate();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "You have exceeded the Input Limit!", Toast.LENGTH_LONG).show();
                    tx1.setText("");
                }


            }
        });

    }


    @SuppressLint("SetTextI18n")
    public void Calculate() {
        long Total = 0;
        long Tax = 0;
        double Cess = 0;
        double TTL = 0;
        // double Surcharge = 0;
        double Age = Integer.parseInt(age.getText().toString());//age//
        double I1 = Integer.parseInt(i.getText().toString());//income 1//
        double I2 = Integer.parseInt(i2.getText().toString());//income 2//
        double I3 = Integer.parseInt(i3.getText().toString());//income 3//
        double I4 = Integer.parseInt(i4.getText().toString());//income 4//
        double ti = I1+I2+I3+I4;                              //adding all incomes//
        double D1 = Integer.parseInt(d1.getText().toString());//deduction 1//
        double D2 = Integer.parseInt(d2.getText().toString());//deduction 2//
        double D3 = Integer.parseInt(d3.getText().toString());//deduction 3//
        double D4 = Integer.parseInt(d4.getText().toString());//deduction 4//
        double di = D1+D2+D3+D4;                              //total deduction//
        double TI = ti-di;                                    //total income//

        if (Age < 18) {
            Toast.makeText(getApplicationContext(), "You are not eligible", Toast.LENGTH_LONG).show();
            return;
        }
        if (Age >= 18 && Age <= 60) if (TI >= 250000 && TI <= 500000) {
            A = (int) ( TI-250000 );
            Tax = ( A * 5 ) / 100;
            Total = Tax;
            Cess = ( Tax * 4 ) / 100;
            TTL = Total+Cess;
        } else if (TI > 500000 && TI <= 1000000) {

            A = (int) ( TI-500000 );
            Tax = ( A * 20 ) / 100;
            Total = Tax+12500;
            Cess = ( Total * 4 ) / 100;
            TTL = Total+Cess;
        } else if (TI > 1000000 && TI <= 5000000) {
            A = (int) ( TI-1000000 );
            Tax = ( A * 30 ) / 100;
            Total = 112500+Tax;
            Cess = ( Total * 4 ) / 100;
            TTL = Total+Cess;
        }

        //income tax slab for senior citizens 60 to 80 //

        if (Age >= 60 && Age <= 80) if (TI > 300000 && TI <= 500000) {

            A = (int) ( TI-300000 );
            Tax = ( A * 5 ) / 100;
            Total = Tax;
            Cess = ( Total * 4 ) / 100;
            TTL = Total+Cess;

        } else if (TI > 500000 && TI <= 1000000) {
            A = (int) ( TI-500000 );
            Tax = ( A * 20 ) / 100;
            Total = Tax+10000;
            Cess = ( Total * 4 ) / 100;
            TTL = Total+Cess;
        } else if (TI > 1000000) {
            A = (int) ( TI-1000000 );
            Tax = ( A * 30 ) / 100;
            Total = Tax+110000;
            Cess = ( Total * 4 ) / 100;
            TTL = Total+Cess;
        }

        //tax slab for above 80 years//

        if (Age > 80 && Age <= 100)
            if (TI > 500000 && TI <= 1000000) {
                A = (int) ( TI-500000 );
                Tax = ( A * 20 ) / 100;
                Total = Tax;
                Cess = ( Total * 4 ) / 100;
                TTL = Total+Cess;

            } else if (TI > 1000000) {
                A = (int) ( TI-1000000 );
                Tax = ( A * 30 ) / 100;
                Total = Tax+100000;
                Cess = ( Total * 4 ) / 100;
                TTL = Total+Cess;
            }
        if (Age > 100) {
            Toast.makeText(getApplicationContext(), "Can't Calculate your tax ", Toast.LENGTH_LONG).show();
            tx1.setText("");
            return;

        }


        //output//
        tx1.setText("Total Taxable Income"+"=\t"+TI+"\n\n"+"Income Tax after relief u/s 87A"+"= \t"+Total+"\n\n"+
                "Health and Education Cess"+"=\t"+Cess+"\n\n"+
                // "Surcharge"+"=\t"+Surcharge+"\n\n"+
                "Total Tax Liability"+"=\t"+TTL);


    }

    public void AddData() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = mDatabasehelper.insertData(tx1.getText().toString());
                if (isInserted = true)
                    Toast.makeText(IncomeActivity2.this, "Data is stored", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(IncomeActivity2.this, "Data is not stored", Toast.LENGTH_LONG).show();

            }

        });
    }

    public void viewAll() {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor data = mDatabasehelper.getListContents();
                        if (data.getCount() == 0) {
                            //show message
                            showMessage("Error", "No Data Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (data.moveToNext()) {
                            buffer.append("ID:"+data.getString(0)+"\n");
                            buffer.append("Name:"+data.getString(1)+"\n");
                            buffer.append("Income_tax:"+data.getString(2)+"\n");
                            buffer.append("ttl:"+data.getString(3)+"\n");
                        }
                        showMessage("Data", buffer.toString());


                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}




