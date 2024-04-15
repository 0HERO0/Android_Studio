package com.example.baccalulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.CheckBox;


public class Caluator extends AppCompatActivity {
    Button Test_Button;
    EditText Weight, Height, Volume, Amount;
    TextView Bac, Metaboltime, Informatoin;
    String num_Gender, num_Weight, num_Height, num_Volume, num_Amount, num_Alchor, tmp;
    Float result, result2;
    double Alchor = 0.7894;
    double Bodyabs = 0.7;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caluator);

        Test_Button = (Button)findViewById(R.id.Test_Button);
        Button Gophoto = (Button)findViewById(R.id.Gophoto);
        Button Table = (Button)findViewById(R.id.Table);
        
        Weight = (EditText)findViewById(R.id.Weight);
        Height = (EditText)findViewById(R.id.Hegiht);
        Volume = (EditText)findViewById(R.id.Volume);
        Amount = (EditText)findViewById(R.id.Amount);

        Bac = (TextView)findViewById(R.id.Bac);
        Metaboltime = (TextView)findViewById(R.id.Metaboltime);
        Informatoin = (TextView)findViewById(R.id.Informatoin);

        CheckBox Man = (CheckBox) findViewById(R.id.Man);
        CheckBox Woman = (CheckBox) findViewById(R.id.Woman);

        Gophoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Caluator.this,Illegal.class);
                startActivity(intent);
            }
        });

        Table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Caluator.this,AlchorTable.class);
                startActivity(intent);
            }
        });

        Test_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_Weight = Weight.getText().toString();
                num_Height = Height.getText().toString();
                num_Volume = Volume.getText().toString();
                num_Amount = Amount.getText().toString();

                boolean isMan = ((CheckBox)findViewById(R.id.Man)).isChecked();
                boolean isWoman = ((CheckBox)findViewById(R.id.Woman)).isChecked();
                float genderFactor;

                if(num_Weight.trim().isEmpty() == true || num_Height.trim().isEmpty() == true || num_Volume.trim().isEmpty() == true || num_Amount.trim().isEmpty() == true)
                {
                    Toast.makeText(getApplicationContext(), "입력하지 않은 값이 존재합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isMan && isWoman)
                {
                    Toast.makeText(getApplicationContext(), "하나의 성별만 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isMan)
                {
                    genderFactor = 0.86f;
                } else
                    if (isWoman) {
                    genderFactor = 0.64f;
                    } else {
                    Toast.makeText(getApplicationContext(), "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                    }

                float bac = (Float.parseFloat(num_Amount) * (Float.parseFloat(num_Volume) * 0.01f) * (float) Bodyabs * (float) Alchor) / (Float.parseFloat(num_Weight) * genderFactor * 10);
                Bac.setText("혈중 알코올 농도 : " + String.format("%.3f", bac));

                float metabol = (bac / 0.03f) + 1.5f;
                Metaboltime.setText("알코올 분해 최소 시간 : " + String.format("%.1f", metabol));


                if (bac >= 0.00f && bac < 0.029f) {
                    Informatoin.setText("해당 사항 없음.");
                } else
                if (bac >= 0.03f && bac < 0.08f) {
                    Informatoin.setText("적발 시 처벌 : 면허정지 100일");
                } else
                if (bac >= 0.08f && bac <= 0.2f){
                    Informatoin.setText("적발 시 처벌 : 면허취소(1년)");
                } else
                    {
                        Informatoin.setText("적발 시 처벌 : 면허취소(2년)");
                    }
            }
        });
    }
}
