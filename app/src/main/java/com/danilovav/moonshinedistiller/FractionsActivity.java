package com.danilovav.moonshinedistiller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FractionsActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private TextView tvTitle;

    private EditText edtFractionPercent;

    private EditText edtVolume01;
    private EditText edtVolume02;
    private EditText edtVolume03;
    private EditText edtVolume04;
    private EditText edtVolume05;

    private EditText edtAlc01;
    private EditText edtAlc02;
    private EditText edtAlc03;
    private EditText edtAlc04;
    private EditText edtAlc05;

    private int mVolume;
    private float mVolAA;
    private int mPctAA;

    private String mAction;

    private String mDefaultVolFraction;

    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fractions_heads);
        mAction = getIntent().getAction();
        InitLayoutObject();

    }

    @Override
    public void onClick(View v) {
        fraction();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData(mAction); // Сохраняем данные
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(mAction);  // востанавливаем данные
    }


    private void fraction(){
        Intent intent = new Intent();

        getFraction(); //Расчет объема фракции и аобъема абсалютного спирта

        intent.putExtra(Constants.FRACTION_VOLUME, mVolume);
        intent.putExtra(Constants.FRACTION_ALC, mVolAA);
        intent.putExtra(Constants.FRACTION_PCT,mPctAA);
        setResult(RESULT_OK, intent);
    }

    // расчет объема
    private void getFraction(){
      int mVol =0;
      float mVolAbsAlc = 0;
      String mVolStr;
      String mAlcStr;

        mPctAA = Integer.valueOf(edtFractionPercent.getText().toString()); // получаем % фракции от АС

        mVolStr = edtVolume01.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc01.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Float.valueOf(mAlcStr));
         }
        mVolStr = edtVolume02.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc02.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Float.valueOf(mAlcStr));
        }
        mVolStr = edtVolume03.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc03.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Float.valueOf(mAlcStr));
        }
        mVolStr = edtVolume04.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc04.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Float.valueOf(mAlcStr));
        }
        mVolStr = edtVolume05.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc05.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Float.valueOf(mAlcStr));
        }


        mVolume         =mVol;
        mVolAA          =mVolAbsAlc;
  }

    //Расчет абсолютного алкоголя
    private float getAbsAlcogol(int vol, float alc){
        return (vol*alc/100);
    }




     private   void InitLayoutObject(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

      edtFractionPercent = (EditText)findViewById(R.id.edtFractionPercent);
      edtFractionPercent.setOnFocusChangeListener(this);

      edtVolume01 = (EditText)findViewById(R.id.etdVolume01);
         edtVolume01.requestFocus();
      edtVolume02 = (EditText)findViewById(R.id.etdVolume02);
      edtVolume03 = (EditText)findViewById(R.id.etdVolume03);
      edtVolume04 = (EditText)findViewById(R.id.etdVolume04);
      edtVolume05 = (EditText)findViewById(R.id.etdVolume05);

      edtAlc01 = (EditText)findViewById(R.id.edtAlc01);
      edtAlc02 = (EditText)findViewById(R.id.edtAlc02);
      edtAlc03 = (EditText)findViewById(R.id.edtAlc03);
      edtAlc04 = (EditText)findViewById(R.id.edtAlc04);
      edtAlc05 = (EditText)findViewById(R.id.edtAlc05);

      setActivitityTitle(mAction);
  }

    private void setActivitityTitle(String action){
     switch(action) {
         case Constants.HEADS:
             tvTitle.setText(R.string.strHeadsFraction);
             mDefaultVolFraction=Constants.DEF_VAL_HEADS;
             break;
         case Constants.AHEADS:
             tvTitle.setText(R.string.strAHeadsFraction);
             mDefaultVolFraction=Constants.DEF_VAL_AHEADS;
             break;
         case Constants.BODY:
             tvTitle.setText(R.string.strBodyFraction);
             edtFractionPercent.setEnabled(false);
             mDefaultVolFraction=Constants.DEF_VAL_BODY;
             break;
         case Constants.BTAILS:
             tvTitle.setText(R.string.strBeforeTailFraction);
             mDefaultVolFraction=Constants.DEF_VAL_BTAILS;
             break;
         case Constants.TAILS:
             tvTitle.setText(R.string.strTailsFraction);
             mDefaultVolFraction=Constants.DEF_VAL_TAILS;
             break;

     }
}

    private void saveData(String key) {
        sPref = getSharedPreferences(Constants.FRACTION_PREFERENCE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor outState = sPref.edit();
        outState.putString(key+"%",edtFractionPercent.getText().toString());

        outState.putString(key+"V1", edtVolume01.getText().toString());
        outState.putString(key+"V2", edtVolume02.getText().toString());
        outState.putString(key+"V3", edtVolume03.getText().toString());
        outState.putString(key+"V4", edtVolume04.getText().toString());
        outState.putString(key+"V5", edtVolume05.getText().toString());

        outState.putString(key+"A1",edtAlc01.getText().toString());
        outState.putString(key+"A2",edtAlc02.getText().toString());
        outState.putString(key+"A3",edtAlc03.getText().toString());
        outState.putString(key+"A4",edtAlc04.getText().toString());
        outState.putString(key+"A5",edtAlc05.getText().toString());
        outState.apply();
    }

    private void loadData(String key) {
        sPref = getSharedPreferences(Constants.FRACTION_PREFERENCE_NAME,MODE_PRIVATE);
        edtFractionPercent.setText(sPref.getString(key+"%",mDefaultVolFraction));

        edtVolume01.setText(sPref.getString(key+"V1",""));
        edtVolume02.setText(sPref.getString(key+"V2",""));
        edtVolume03.setText(sPref.getString(key+"V3",""));
        edtVolume04.setText(sPref.getString(key+"V4",""));
        edtVolume05.setText(sPref.getString(key+"V5",""));

        edtAlc01.setText(sPref.getString(key+"A1",""));
        edtAlc02.setText(sPref.getString(key+"A2",""));
        edtAlc03.setText(sPref.getString(key+"A3",""));
        edtAlc04.setText(sPref.getString(key+"A4",""));
        edtAlc05.setText(sPref.getString(key+"A5",""));

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
        //Проверка на привышение 100% процентов по объему АС
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fraction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()){
            case R.id.fractionClear:
                clearFractionData();
                break;
            case R.id.fractionSave:
                fraction();
                hidekeybord();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearFractionData() {
        getSharedPreferences(Constants.FRACTION_PREFERENCE_NAME,MODE_PRIVATE).edit().clear().apply();
        loadData(mAction);
    }

    private void hidekeybord() {
        View nView= this.getCurrentFocus();

        if (nView != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(nView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
