package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.model.Calculator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private Calculator calculator;

//    UI Controls
    TextView tvMainHistory;
    TextView tvMainCurrent;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();

//        TO HIDE THE ACTION BAR ON TOP
        getSupportActionBar().hide();
    }

    private void initialization() {
        calculator = new Calculator();
        tvMainCurrent = findViewById(R.id.tvMainCurrent);
    }

    public void buttonTap(View view) {
        String value = ((Button)view).getText().toString();
//        view.setBackgroundColor(Color.DKGRAY);
        Log.i(TAG,value);
        calculator.buttonClicked(value);

//        THIS WILL SET THE CURRENT NUMBER TEXTVIEW
        setCurrentTextView();
    }

    private void setCurrentTextView() {
        tvMainCurrent.setText(calculator.getCurrent());
    }
}
