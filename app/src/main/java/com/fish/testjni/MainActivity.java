package com.fish.testjni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fish.calnum.Compute;

public class MainActivity extends AppCompatActivity {

    private Button btnOne;
    private Button btnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnOne = findViewById(R.id.test_jni_one);
        btnTwo = findViewById(R.id.test_jni_two);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num = Compute.add(2, 10);
                Toast.makeText(MainActivity.this, num + "", Toast.LENGTH_SHORT).show();
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num = Compute.add(20, 100);
                Toast.makeText(MainActivity.this, num + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
