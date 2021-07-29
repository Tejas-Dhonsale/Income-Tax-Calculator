package com.example.tax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.BreakIterator;

public class EmiActivity extends AppCompatActivity {
    EditText p;
    EditText r;
    EditText m;
    EditText t;
    TextView result;
    Switch switchbutton;

    Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);
        p=(EditText) findViewById(R.id.number1);
       r=(EditText) findViewById(R.id.number2);
       m=(EditText)findViewById(R.id.number3);
        result=(TextView) findViewById(R.id.text1);
        calc=(Button)findViewById(R.id.button);
        switchbutton= findViewById(R.id.switch1);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sp = p.getText().toString();
                String sr = r.getText().toString();
                String sm = m.getText().toString();
                if (TextUtils.isEmpty(sp)) {
                    p.setError("Enter Principal Amount");
                    p.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(sr)) {
                    r.setError("Enter Interest Rate");
                    r.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(sm)) {
                    m.setError("Enter Duration");
                    m.requestFocus();
                    return;
                }

                float P = Float.parseFloat(sp);
                float R = Float.parseFloat(sr);
                float M = Float.parseFloat(sm);

                float principal = calpric(P);
                float rate = calint(R);
                float month = calmonth(M);


                float Dvdnt = calDvdnt(rate, month);


                float FD = calFinalDvdnt(principal, rate, Dvdnt);

                float D = calDivider(Dvdnt);

                float emi = calemi(FD, D);
                float TA = calTa(emi, month);
                float ti = calTotalInt(TA, principal);


                result.setText("Your EMI"+"=\t"+emi+" \n \n"+"Total Amount with interest"+"=\t"+TA);

            }



            public float calpric(float P) {
                return (P);
            }


            public float calint(float R)
           {
                return (R/12/100);
            }

            private float calmonth(float M) {
                if (switchbutton.isChecked())
                    return ( M * 12 );
                else
                    return (M*1);
            }


            private float calDvdnt(float rate, float month) {
                return (float) (Math.pow(1+rate,month));
            }

            private float calFinalDvdnt(float principal, float rate, float dvdnt) {
                return (principal*rate*dvdnt);
            }

            private float calDivider(float dvdnt) {
                return (dvdnt-1);
            }

            private float calemi(float fd, float d) {
                return (fd/d);
            }
            public  float calTa(float emi, Float Months) {

                return (emi*Months);

            }
            public  float calTotalInt(float TA, float Principal) {

                return (TA - Principal);

            }


        });
    }
}