package com.example.lec4demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radGroupConvType;
    TextView txtViewResult;
    EditText editTextInput;
    Button btnConvertWt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar  = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_round);




        radGroupConvType = findViewById(R.id.radGroupConvType);
        editTextInput = findViewById(R.id.editTextInputWt);
        btnConvertWt = findViewById(R.id.btnConvertWt);
        txtViewResult = findViewById(R.id.txtViewResult);






        btnConvertWt.setOnClickListener((View V )-> {
            if(radGroupConvType.getCheckedRadioButtonId() == -1){
                Toast.makeText(this,"Please Select Conversion Type",Toast.LENGTH_SHORT).show();
            }else if(editTextInput.getText().toString().isEmpty()){
                Toast.makeText(this,"Input weight needs to be entered", Toast.LENGTH_SHORT).show();
            }
            else{
                double inputWt = 0, outputWt = 0;
                try {
                    inputWt = Double.parseDouble(editTextInput.getText().toString());
                    if (inputWt < 0) {
                        Toast.makeText(this, "Input weight can't be negative", Toast.LENGTH_SHORT).show();
                    } else if(radGroupConvType.getCheckedRadioButtonId() == R.id.radBtnKgtoPounds){
                        if(inputWt > 500)
                        {
                            Toast.makeText(this, "baggage weight in kilos exceeded",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            outputWt = inputWt * 2.2;
                            txtViewResult.setText(String.format("Converted Wt : %.2f lbs",outputWt));
                        }
                    }else if(radGroupConvType.getCheckedRadioButtonId() == R.id.radBtnPoundstoKg)
                    {
                        if(inputWt > 1000)
                        {
                            Toast.makeText(this, "baggage weight in kilos exceeded",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            outputWt = inputWt / 2.2;
                            txtViewResult.setText(String.format("Converted Wt : %.2f kg",outputWt));
                        }
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    Toast.makeText(this, "Must be a valid number", Toast.LENGTH_SHORT).show();
                }
            }

        });

        radGroupConvType.setOnCheckedChangeListener((RadioGroup group, int checkedId)->{
            if(checkedId == R.id.radBtnKgtoPounds)
            {
                txtViewResult.setText("Option : Kilos to pounds, update input weight and click on buttons");
            }
            else if(checkedId == R.id.radBtnPoundstoKg){
                txtViewResult.setText("Option: Pounds to kilos, update input weight click on button");
            }
        });

    }
}