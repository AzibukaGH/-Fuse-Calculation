package com.example.fusecalculation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    double coscounter = 0.97;
    public void cosCountChangeP(View v){
    TextView coscount = findViewById(R.id.coscount);
        if (coscounter>=1.0){
            coscount.setText("1.00");}
        else if(coscounter<0.01){
            coscounter = 0.01;
            coscount.setText(Double.toString((coscounter)).format("%.2f",coscounter));
        }
        else{
            coscounter = coscounter+0.01;
            coscount.setText(Double.toString((coscounter)).format("%.2f",coscounter));
        }
    }
    public void cosCountChangeM(View v){
        TextView coscount = findViewById(R.id.coscount);
        if (coscounter<0.02){
            coscount.setText("0.01");
            coscounter = 0.01;}
        else if(coscounter>0.99){
            coscounter = 0.99;
            coscount.setText(Double.toString((coscounter)).format("%.2f",coscounter));}
        else{
            coscounter = coscounter-0.01;
            coscount.setText(Double.toString((coscounter)).format("%.2f",coscounter));
        }
    }

    double per20 = 1.0;
    public void  onButtonClick (View v){

        int volt = 1;
        int watt =0;
        int exeption = 0;
        RadioGroup button12or220= findViewById(R.id.radioGryp12or220);
        int checkBUtton12or220 = button12or220.getCheckedRadioButtonId();
        switch (checkBUtton12or220){
            case R.id.radio12:
                volt=12;
                        break;
            case R.id.radio220:
                volt=220;
                break;
            case R.id.needPutVolt:
                EditText getvolt = (EditText)findViewById(R.id.putVolt);
                String checktextvolt = getvolt.getText().toString();
                if (checktextvolt.isEmpty()){
                    exeption++;
                }
                else  {
                    volt= Integer.parseInt(getvolt.getText().toString());
                }
                break;
        }
        Switch switchBut20per=  findViewById(R.id.switch20per);
        switchBut20per.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    per20 = 1.2;
                }
                else{per20 =1.0;}
            }
        });
        double voltWithCosf = Double.valueOf(volt)*coscounter;
        EditText getwat = (EditText)findViewById(R.id.putwatt);
        String checktextWat = getwat.getText().toString();
        if (checktextWat.isEmpty()){
            exeption++;
        } else {
             watt = Integer.parseInt(getwat.getText().toString());
        }
        TextView finalres = (TextView)findViewById(R.id.finalres);
        if(exeption<1){
        double frest = Math.ceil(Double.valueOf(watt)/voltWithCosf*per20) ;
        int resSum = (int)(frest);
        finalres.setText(Integer.toString(resSum)+ " A");}
        else{
            finalres.setText("Неверное значение");
        }
    }
}