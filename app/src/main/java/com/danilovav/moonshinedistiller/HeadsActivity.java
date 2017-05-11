package com.danilovav.moonshinedistiller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class HeadsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private Button btnSave;

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
    private String mAction;


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

        mVolume = calcVolume();
        intent.putExtra(Constants.FRACTION_VOLUME, mVolume);

        setResult(RESULT_OK, intent);
        finish();
    }

  private int calcVolume(){
      int mVol =0;
      String mText = "0";

      mText = etdVolume01.getText().toString();
          if(!mText.isEmpty()) mVol+=Integer.valueOf(mText);
      mText = etdVolume02.getText().toString();
          if(!mText.isEmpty()) mVol+=Integer.valueOf(mText);
      mText = etdVolume03.getText().toString();
          if(!mText.isEmpty()) mVol+=Integer.valueOf(mText);
      mText = etdVolume04.getText().toString();
          if(!mText.isEmpty()) mVol+=Integer.valueOf(mText);
      mText = etdVolume05.getText().toString();
          if(!mText.isEmpty()) mVol+=Integer.valueOf(mText);

      return(mVol);
  }



  private   void InitLayoutObject(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        setActivitityTitle(mAction);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

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
             break;
         case Constants.AHEADS:
             tvTitle.setText(R.string.strAHeadsFraction);
             break;
         case Constants.BODY:
             tvTitle.setText(R.string.strBodyFraction);
             break;
         case Constants.BTAILS:
             tvTitle.setText(R.string.strBeforeTailFraction);
             break;
         case Constants.TAILS:
             tvTitle.setText(R.string.strTailsFraction);
             break;

     }
}
}
