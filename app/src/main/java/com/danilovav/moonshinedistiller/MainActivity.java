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
    private TextView tvAfterHeadsOut;
    private TextView tvBodyOut;
    private TextView tvBeforeTailsOut;
    private TextView tvTailsOut;

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
                 intent.setAction(Constants.HEADS);
                startActivityForResult(intent, Constants.REQUEST_CODE_HEADS);
                break;
            case R.id.dotbtnAfterHeads :
                intent.setAction(Constants.AHEADS);
                startActivityForResult(intent, Constants.REQUEST_CODE_AFTER_HEADS);
                break;
            case R.id.dotbtnBody :
                intent.setAction(Constants.BODY);
                startActivityForResult(intent, Constants.REQUEST_CODE_BODY);
                break;
            case R.id.dotbtnBeforeTails :
                intent.setAction(Constants.BTAILS);
                startActivityForResult(intent, Constants.REQUEST_CODE_BEFORE_TAILS);
                break;
            case R.id.dotbtnTails :
                intent.setAction(Constants.TAILS);
                startActivityForResult(intent, Constants.REQUEST_CODE_TAILS);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}

        int mIntentExtraVoluem = data.getIntExtra(Constants.FRACTION_VOLUME, Constants.DEF_INT_NULL);  // Объем фракции
        int mIntentExtraAlc= data.getIntExtra(Constants.FRACTION_ALC, Constants.DEF_INT_NULL);         // % спирта в фракции

        switch (requestCode){
            case Constants.REQUEST_CODE_HEADS:
                 tvHeadsOut.setText(String.valueOf(mIntentExtraVoluem));
                break;
            case Constants.REQUEST_CODE_AFTER_HEADS:
                tvAfterHeadsOut.setText(String.valueOf(mIntentExtraVoluem));
                break;
            case Constants.REQUEST_CODE_BODY:
                tvBodyOut.setText(String.valueOf(mIntentExtraVoluem));
                break;
            case Constants.REQUEST_CODE_BEFORE_TAILS:
                tvBeforeTailsOut.setText(String.valueOf(mIntentExtraVoluem));
                break;
            case Constants.REQUEST_CODE_TAILS:
                tvTailsOut.setText(String.valueOf(mIntentExtraVoluem));
                break;
        }
    }
void InitLayoutObject(){
    tvAlcInCubeOut  = (TextView)findViewById(R.id.tvAlcInCubeOut);
    tvVolAbsAlcOut  = (TextView)findViewById(R.id.tvVolAbsAlcOut);
    tvHeadsOut      = (TextView)findViewById(R.id.tvHeadsOut);
    tvAfterHeadsOut = (TextView)findViewById(R.id.tvAfterHeadsOut);
    tvBodyOut       = (TextView)findViewById(R.id.tvBodyOut);
    tvBeforeTailsOut= (TextView)findViewById(R.id.tvBeforeTailsOut);
    tvTailsOut      = (TextView)findViewById(R.id.tvTailsOut);

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
