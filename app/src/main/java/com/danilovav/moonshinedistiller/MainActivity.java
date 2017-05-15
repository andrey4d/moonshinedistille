package com.danilovav.moonshinedistiller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

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
    private EditText edtHeads;
    private EditText edtAfterHeads;
    private EditText edtBody;
    private EditText edtBeforTails;
    private EditText edtTails;

    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = new Calculate(); // Создание объекта из класа калькулятор
        InitLayoutObject(); // Инициализация ТекстВью и ЕдитТекст на разметке
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(Constants.KEY_MAIN_ACTIVITY);
        onCalculate();  // расчет параметров
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData(Constants.KEY_MAIN_ACTIVITY);
    }

    public void onCalculate() {

        hidekeybord();
        calculate.setAlcRAW(edtAlc.getText().toString()); //получение данных от активити
        calculate.setValRAW(edtAlcV.getText().toString());
        calculate.setValWater(edtWaterV.getText().toString());

        calculate.onCalculate();

        tvAlcInCubeOut.setText(String.valueOf(calculate.getAlcInCude()));
        tvVolAbsAlcOut.setText(String.valueOf(calculate.getAbsAlc()));


    }

    public void onClick3dotButton(View view) {
        Intent intent = new Intent(this, HeadsActivity.class);

        switch (view.getId()) {
            case R.id.dotbtnHeads:
                intent.setAction(Constants.HEADS);
                startActivityForResult(intent, Constants.REQUEST_CODE_HEADS);
                break;
            case R.id.dotbtnAfterHeads:
                intent.setAction(Constants.AHEADS);
                startActivityForResult(intent, Constants.REQUEST_CODE_AFTER_HEADS);
                break;
            case R.id.dotbtnBody:
                intent.setAction(Constants.BODY);
                startActivityForResult(intent, Constants.REQUEST_CODE_BODY);
                break;
            case R.id.dotbtnBeforeTails:
                intent.setAction(Constants.BTAILS);
                startActivityForResult(intent, Constants.REQUEST_CODE_BEFORE_TAILS);
                break;
            case R.id.dotbtnTails:
                intent.setAction(Constants.TAILS);
                startActivityForResult(intent, Constants.REQUEST_CODE_TAILS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        int mIntentExtraVoluem = data.getIntExtra(Constants.FRACTION_VOLUME, Constants.DEF_INT_NULL);  // Объем фракции
        int mIntentExtraAlc = data.getIntExtra(Constants.FRACTION_ALC, Constants.DEF_INT_NULL);         // абсолютный спирт

        String strOutFraction = String.valueOf(mIntentExtraVoluem) + " " + getString(R.string.strMl) + " "
                + String.valueOf(calculate.getAlcFraction(mIntentExtraVoluem, mIntentExtraAlc)) +
                " " + getString(R.string.strPercent);

        switch (requestCode) {
            case Constants.REQUEST_CODE_HEADS:
                tvHeadsOut.setText(strOutFraction);

                break;
            case Constants.REQUEST_CODE_AFTER_HEADS:
                tvAfterHeadsOut.setText(strOutFraction);
                break;
            case Constants.REQUEST_CODE_BODY:
                tvBodyOut.setText(strOutFraction);
                break;
            case Constants.REQUEST_CODE_BEFORE_TAILS:
                tvBeforeTailsOut.setText(strOutFraction);
                break;
            case Constants.REQUEST_CODE_TAILS:
                tvTailsOut.setText(strOutFraction);
                break;
        }
        onCalculate(); // Расчет
    }


    void InitLayoutObject() {
        tvAlcInCubeOut = (TextView) findViewById(R.id.tvAlcInCubeOut);
        tvVolAbsAlcOut = (TextView) findViewById(R.id.tvVolAbsAlcOut);
        tvHeadsOut = (TextView) findViewById(R.id.tvHeadsOut);
        tvAfterHeadsOut = (TextView) findViewById(R.id.tvAfterHeadsOut);
        tvBodyOut = (TextView) findViewById(R.id.tvBodyOut);
        tvBeforeTailsOut = (TextView) findViewById(R.id.tvBeforeTailsOut);
        tvTailsOut = (TextView) findViewById(R.id.tvTailsOut);

        edtAlc          = (EditText) findViewById(R.id.edtAlc01);
        edtAlc.setOnFocusChangeListener(this);
        edtAlcV         = (EditText) findViewById(R.id.edtAlcV);
        edtAlcV.setOnFocusChangeListener(this);
        edtWaterV       = (EditText) findViewById(R.id.edtWaterV);
        edtWaterV.setOnFocusChangeListener(this);

        edtHeads        = (EditText) findViewById(R.id.edtHeads);
        edtAfterHeads   = (EditText) findViewById(R.id.edtAfterHeads);
        edtBody         = (EditText) findViewById(R.id.edtBody);
        edtBeforTails   = (EditText) findViewById(R.id.edtBeforTails);
        edtTails        = (EditText) findViewById(R.id.edtTails);


    }

    public void hidekeybord() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(new View(this).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    private void saveData(String key) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sPref.edit();
        spEditor.putString(key+Constants.ALC,       edtAlc.getText().toString());
        spEditor.putString(key+Constants.RAWVOL,    edtAlcV.getText().toString());
        spEditor.putString(key+Constants.WATERVOL,  edtWaterV.getText().toString());

        spEditor.putString(key+Constants.HEADS,     edtHeads.getText().toString());
        spEditor.putString(key+Constants.AHEADS,    edtAfterHeads.getText().toString());
        spEditor.putString(key+Constants.BODY,      edtBody.getText().toString());
        spEditor.putString(key+Constants.BTAILS,    edtBeforTails.getText().toString());
        spEditor.putString(key+Constants.TAILS,     edtTails.getText().toString());
        spEditor.commit();
    }

    void loadData(String key) {
        sPref = getPreferences(MODE_PRIVATE);
        edtAlc.setText      (sPref.getString(key+Constants.ALC,        String.valueOf(Constants.DEF_ACL_RAW)));
        edtAlcV.setText     (sPref.getString(key+Constants.RAWVOL,     String.valueOf(Constants.DEF_VAL_ACL_RAW)));
        edtWaterV.setText   (sPref.getString(key+Constants.WATERVOL,   String.valueOf(Constants.DEF_VAL_WATER)));

        edtHeads.setText(     sPref.getString(key+Constants.HEADS,     Constants.DEF_VAL_HEADS));
        edtAfterHeads.setText(sPref.getString(key+Constants.AHEADS,    Constants.DEF_VAL_AHEADS));
        edtBody.setText(      sPref.getString(key+Constants.BODY,      Constants.DEF_VAL_BODY));
        edtBeforTails.setText(sPref.getString(key+Constants.BTAILS,    Constants.DEF_VAL_BTAILS));
        edtTails.setText(     sPref.getString(key+Constants.TAILS,     Constants.DEF_VAL_TAILS));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            onCalculate();
            Toast.makeText(getApplicationContext(), "Calculate!", Toast.LENGTH_SHORT).show();
        }
    }
}

