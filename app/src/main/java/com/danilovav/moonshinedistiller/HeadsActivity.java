package com.danilovav.moonshinedistiller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HeadsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private Button btnSave;

    private EditText edtFractionPercent;

    private EditText etdVolume01;
    private EditText etdVolume02;
    private EditText etdVolume03;
    private EditText etdVolume04;
    private EditText etdVolume05;

    private EditText edtAlc01;
    private EditText edtAlc02;
    private EditText edtAlc03;
    private EditText edtAlc04;
    private EditText edtAlc05;

    private int mVolume;
    private int mVolAbsAlcogol;
    private String mAction;

    private String mDefaultVolFraction;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads);
        mAction = getIntent().getAction();
        InitLayoutObject();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        calcFraction(); //Расчет объема фракции и аобъема абсалютного спирта

        intent.putExtra(Constants.FRACTION_VOLUME, mVolume);
        intent.putExtra(Constants.FRACTION_ALC, mVolAbsAlcogol);
        setResult(RESULT_OK, intent);
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



    // расчет объема
    private void calcFraction(){
      int mVol =0;
      int mVolAbsAlc = 0;
      String mVolStr = "0";
      String mAlcStr = "0";

        mVolStr = etdVolume01.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc01.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Integer.valueOf(mAlcStr));
         }
        mVolStr = etdVolume02.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc02.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Integer.valueOf(mAlcStr));
        }
        mVolStr = etdVolume03.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc03.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Integer.valueOf(mAlcStr));
        }
        mVolStr = etdVolume04.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc04.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Integer.valueOf(mAlcStr));
        }
        mVolStr = etdVolume05.getText().toString();
        if(!mVolStr.isEmpty()) {
            mVol += Integer.valueOf(mVolStr);
            mAlcStr = edtAlc05.getText().toString();
            if (!mAlcStr.isEmpty()) mVolAbsAlc += getAbsAlcogol(Integer.valueOf(mVolStr),Integer.valueOf(mAlcStr));
        }


        mVolume     =mVol;
        mVolAbsAlcogol =mVolAbsAlc;
  }

    //Расчет абсолютного алкоголя
    private int getAbsAlcogol(int vol, int alc){
        return (vol*alc/100);
    }




     private   void InitLayoutObject(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        setActivitityTitle(mAction);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

      edtFractionPercent = (EditText)findViewById(R.id.edtFractionPercent);

      etdVolume01 = (EditText)findViewById(R.id.etdVolume01);
      etdVolume02 = (EditText)findViewById(R.id.etdVolume02);
      etdVolume03 = (EditText)findViewById(R.id.etdVolume03);
      etdVolume04 = (EditText)findViewById(R.id.etdVolume04);
      etdVolume05 = (EditText)findViewById(R.id.etdVolume05);

      edtAlc01 = (EditText)findViewById(R.id.edtAlc01);
      edtAlc02 = (EditText)findViewById(R.id.edtAlc02);
      edtAlc03 = (EditText)findViewById(R.id.edtAlc03);
      edtAlc04 = (EditText)findViewById(R.id.edtAlc04);
      edtAlc05 = (EditText)findViewById(R.id.edtAlc05);
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
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor outState = sPref.edit();

        outState.putString(key+"%",edtFractionPercent.getText().toString());

        outState.putString(key+"V1",etdVolume01.getText().toString());
        outState.putString(key+"V2",etdVolume02.getText().toString());
        outState.putString(key+"V3",etdVolume03.getText().toString());
        outState.putString(key+"V4",etdVolume04.getText().toString());
        outState.putString(key+"V5",etdVolume05.getText().toString());

        outState.putString(key+"A1",edtAlc01.getText().toString());
        outState.putString(key+"A2",edtAlc02.getText().toString());
        outState.putString(key+"A3",edtAlc03.getText().toString());
        outState.putString(key+"A4",edtAlc04.getText().toString());
        outState.putString(key+"A5",edtAlc05.getText().toString());
        outState.commit();
    }

    void loadData(String key) {
        sPref = getPreferences(MODE_PRIVATE);
        edtFractionPercent.setText(sPref.getString(key+"%",mDefaultVolFraction));
        etdVolume01.setText(sPref.getString(key+"V1",""));
        etdVolume02.setText(sPref.getString(key+"V2",""));
        etdVolume03.setText(sPref.getString(key+"V3",""));
        etdVolume04.setText(sPref.getString(key+"V4",""));
        etdVolume05.setText(sPref.getString(key+"V5",""));

        edtAlc01.setText(sPref.getString(key+"A1",""));
        edtAlc02.setText(sPref.getString(key+"A2",""));
        edtAlc03.setText(sPref.getString(key+"A3",""));
        edtAlc04.setText(sPref.getString(key+"A4",""));
        edtAlc05.setText(sPref.getString(key+"A5",""));
    }

}
