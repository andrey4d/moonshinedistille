package com.danilovav.moonshinedistiller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Calculate calculate;

    private TextView tvAlcInCubeOut;
    private TextView tvVolAbsAlcOut;
    private TextView tvHeadsOut;

    private EditText edtAlc;
    private EditText edtAlcV;
    private EditText edtWaterV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = new Calculate(); // Создание объекта из класа калькулятор
        InitLayoutObject(); // Инициализация ТекстВью и ЕдитТекст на разметке


    }

   public void onClickCalculate(View view){

       hidekeybord();
       calculate.setmAlcRAW(calculate.addZero(edtAlc.getText().toString())); //получение данных от активити
       calculate.setmValRAW(calculate.addZero(edtAlcV.getText().toString()));
       calculate.setmValWater(calculate.addZero(edtWaterV.getText().toString()));

       calculate.onCalculate();

       tvAlcInCubeOut.setText(String.valueOf(calculate.getmAlcInCude()));
       tvVolAbsAlcOut.setText(String.valueOf(calculate.getmAbsAlc()));
       Toast.makeText(getApplicationContext(),"Calculate!",Toast.LENGTH_SHORT).show();
    }

    public void onClick3dotButton(View view){
        Intent intent = new Intent(this, HeadsActivity.class);

        switch (view.getId()){
            case R.id.dotbtnHeads :
                startActivityForResult(intent, Constants.REQUEST_CODE_HEADS);
                break;
            case R.id.dotbtnAfterHeads :
                break;
            case R.id.dotbtnBody :
                break;
            case R.id.dotbtnBeforeTails :
                break;
            case R.id.dotbtnTails :
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}

        switch (requestCode){
            case Constants.REQUEST_CODE_HEADS:
                int mHeads = data.getIntExtra(Constants.HAEDS, 0);
                tvHeadsOut.setText(Integer.toString(mHeads));
                break;
        }
    }
void InitLayoutObject(){
    tvAlcInCubeOut = (TextView)findViewById(R.id.tvAlcInCubeOut);
    tvVolAbsAlcOut = (TextView)findViewById(R.id.tvVolAbsAlcOut);
    tvHeadsOut     = (TextView)findViewById(R.id.tvHeadsOut);

    edtAlc    = (EditText)findViewById(R.id.edtAlc01);
    edtAlcV   = (EditText)findViewById(R.id.edtAlcV);
    edtWaterV = (EditText)findViewById(R.id.edtWaterV);



}

public void hidekeybord(){
    InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_TEST, 40);
    }
}
