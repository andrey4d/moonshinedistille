package com.danilovav.moonshinedistiller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private TextView tvCubeBottomOut;
    private TextView tvHeadsBase;
    private TextView tvAHeadsBase;
    private TextView tvBodyBase;
    private TextView tvBTailsBase;
    private TextView tvTailsBase;

    private EditText edtAlc;
    private EditText edtAlcV;
    private EditText edtWaterV;


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
        loadFractionData(Constants.KEY_MAIN_ACTIVITY);
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

        calculate.doCalculate();
        outCubeParametr();// вывод параметров куба
        outAllFraction(); // вывод параметров фракций на экран
    }

    public void onClick3dotButton(View view) {
        Intent intent = new Intent(this, FractionsActivity.class);

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

        int mIntentExtraVoluem =        data.getIntExtra(Constants.FRACTION_VOLUME, Constants.DEF_INT_NULL); // Объем фракции
        float mIntentExtraVoluemAA =      data.getFloatExtra(Constants.FRACTION_ALC,    Constants.DEF_INT_NULL); // Объем абсолютный спирт фракции
        int mIntentExtraPct      =      data.getIntExtra(Constants.FRACTION_PCT,    Constants.DEF_INT_NULL); // % фракции от АС

        switch (requestCode) {
            case Constants.REQUEST_CODE_HEADS:
                 calculate.setHeadVol(mIntentExtraVoluem); //записываем данные о объему фракции
                 calculate.setHeadVolAA(mIntentExtraVoluemAA);    //записываем данные о абсалютном спирти
                 calculate.setmPctHead(mIntentExtraPct);         //записываем данные % от АС
                 break;
            case Constants.REQUEST_CODE_AFTER_HEADS:
                 calculate.setAHeadVol(mIntentExtraVoluem);
                 calculate.setAHeadVolAA(mIntentExtraVoluemAA);
                 calculate.setmPctAHead(mIntentExtraPct);
                break;
            case Constants.REQUEST_CODE_BODY:
                 calculate.setBodyVol(mIntentExtraVoluem);
                 calculate.setBodyVolAA(mIntentExtraVoluemAA);
                 calculate.setmPctBody(mIntentExtraPct);
                break;
            case Constants.REQUEST_CODE_BEFORE_TAILS:
                 calculate.setBTailsVol(mIntentExtraVoluem);
                 calculate.setBTailsVolAA(mIntentExtraVoluemAA);
                 calculate.setmPctBTails(mIntentExtraPct);
                break;
            case Constants.REQUEST_CODE_TAILS:
                 calculate.setTailsVol(mIntentExtraVoluem);
                 calculate.setTailsVolAA(mIntentExtraVoluemAA);
                 calculate.setmPctTails(mIntentExtraPct);
                break;
        }

        saveFractionData(Constants.KEY_MAIN_ACTIVITY + requestCode,mIntentExtraVoluem,mIntentExtraVoluemAA,mIntentExtraPct);

        onCalculate(); // Расчет
//        outCubeParametr(); // вывод параметров куба
//        outAllFraction(); //Вывод данных о фракциях

    }

    private String mkSelectedFractionOutString(int fractionVol, float fractinVolAA){

        String AlcF = String.format("%.2f",calculate.getfAlcFraction(fractionVol,fractinVolAA));

        return(getString(R.string.strSelect)+getString(R.string.strSpace)+ String.valueOf(fractionVol) +
                        getString(R.string.strMl) + getString(R.string.strComma)+getString(R.string.strSpace)
                        + AlcF + getString(R.string.strPercent) // крепость фракции
                        + getString(R.string.strComma)+getString(R.string.strSpace)
                        + getString(R.string.strAbsAlcohol)+getString(R.string.strSpace)+String.valueOf(fractinVolAA) +getString(R.string.strMl));
    }

    private String mkBaseFractionOutString(String titleFraction, int pctFraction){

        calculate.setmPctBody(100 -(calculate.getmPctHead()+calculate.getmPctAHead()+calculate.getmPctBTails()+calculate.getmPctTails())); //расчет тела

        if(titleFraction.equals(getString(R.string.strBody))) {
            pctFraction = calculate.getmPctBody();
        }

        return(titleFraction+getString(R.string.strComma)+ String.valueOf(pctFraction)+getString(R.string.strPercent)+getString(R.string.strComma)+getString(R.string.strSpace)+
                getString(R.string.strAbsAlcohol)+getString(R.string.strSpace)+calculate.baseFractionAA(pctFraction)+getString(R.string.strMl));
    }



    void InitLayoutObject() {
        tvAlcInCubeOut = (TextView) findViewById(R.id.tvAlcInCubeOut);
        tvVolAbsAlcOut = (TextView) findViewById(R.id.tvVolAbsAlcOut);
        tvHeadsOut = (TextView) findViewById(R.id.tvHeadsOut);
        tvAfterHeadsOut = (TextView) findViewById(R.id.tvAfterHeadsOut);
        tvBodyOut = (TextView) findViewById(R.id.tvBodyOut);
        tvBeforeTailsOut = (TextView) findViewById(R.id.tvBeforeTailsOut);
        tvTailsOut = (TextView) findViewById(R.id.tvTailsOut);
        tvCubeBottomOut = (TextView) findViewById(R.id.tvCubeBottomOut);

        tvHeadsBase  =(TextView) findViewById(R.id.tvHeads);
        tvAHeadsBase =(TextView) findViewById(R.id.tvAfterHeads);
        tvBodyBase   =(TextView) findViewById(R.id.tvBody);
        tvBTailsBase =(TextView) findViewById(R.id.tvBeforeTails);
        tvTailsBase  =(TextView) findViewById(R.id.tvTails);

        edtAlc          = (EditText) findViewById(R.id.edtAlc01);
        edtAlc.setOnFocusChangeListener(this);
        edtAlcV         = (EditText) findViewById(R.id.edtAlcV);
        edtAlcV.setOnFocusChangeListener(this);
        edtWaterV       = (EditText) findViewById(R.id.edtWaterV);
        edtWaterV.setOnFocusChangeListener(this);


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
//        spEditor.putString(Constants.BODY+"%",String.valueOf(calculate.getmPctBody()));
        spEditor.apply();
    }

    void loadData(String key) {
        sPref = getPreferences(MODE_PRIVATE);
        edtAlc.setText      (sPref.getString(key+Constants.ALC,        String.valueOf(Constants.DEF_ACL_RAW)));
        edtAlcV.setText     (sPref.getString(key+Constants.RAWVOL,     String.valueOf(Constants.DEF_VAL_ACL_RAW)));
        edtWaterV.setText   (sPref.getString(key+Constants.WATERVOL,   String.valueOf(Constants.DEF_VAL_WATER)));
    }

    private void saveFractionData(String key, int dataVol, float dataAlc, int dataPct){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sPref.edit();
        spEditor.putInt(key+Constants.VOL,dataVol);
        spEditor.putFloat(key+Constants.ALC,dataAlc);
        spEditor.putInt(key+Constants.PCT, dataPct);
        spEditor.commit();

    }
    private void loadFractionData(String key){
        sPref = getPreferences(MODE_PRIVATE);
        //key= Constants.KEY_MAIN_ACTIVITY + requestCode+Constants.VOL||+Constants.ALC
        // requestCode 1-5

        calculate.setHeadVol(sPref.getInt(key+1+Constants.VOL, Constants.DEF_INT_NULL)); //записываем данные о объему фракции
        calculate.setHeadVolAA(sPref.getFloat(key+1+Constants.ALC, Constants.DEF_INT_NULL));    //записываем данные о абсалютном спирти
        calculate.setmPctHead(sPref.getInt(key+1+Constants.PCT,Integer.valueOf(Constants.DEF_VAL_HEADS)));

        calculate.setAHeadVol(sPref.getInt(key+2+Constants.VOL, Constants.DEF_INT_NULL));
        calculate.setAHeadVolAA(sPref.getFloat(key+2+Constants.ALC, Constants.DEF_INT_NULL));
        calculate.setmPctAHead(sPref.getInt(key+2+Constants.PCT,Integer.valueOf(Constants.DEF_VAL_AHEADS)));

        calculate.setBodyVol(sPref.getInt(key+3+Constants.VOL, Constants.DEF_INT_NULL));
        calculate.setBodyVolAA(sPref.getFloat(key+3+Constants.ALC, Constants.DEF_INT_NULL));
        calculate.setmPctBody(sPref.getInt(key+3+Constants.PCT,Integer.valueOf(Constants.DEF_VAL_BODY)));

        calculate.setBTailsVol(sPref.getInt(key+4+Constants.VOL, Constants.DEF_INT_NULL));
        calculate.setBTailsVolAA(sPref.getFloat(key+4+Constants.ALC, Constants.DEF_INT_NULL));
        calculate.setmPctBTails(sPref.getInt(key+4+Constants.PCT,Integer.valueOf(Constants.DEF_VAL_BTAILS)));

        calculate.setTailsVol(sPref.getInt(key+5+Constants.VOL, Constants.DEF_INT_NULL));
        calculate.setTailsVolAA(sPref.getFloat(key+5+Constants.ALC, Constants.DEF_INT_NULL));
        calculate.setmPctTails(sPref.getInt(key+5+Constants.PCT,Integer.valueOf(Constants.DEF_VAL_TAILS)));
    }

    private void outAllFraction(){
        tvHeadsOut.setText(mkSelectedFractionOutString(calculate.getHeadVol(),calculate.getHeadVolAA()));
        tvAfterHeadsOut.setText(mkSelectedFractionOutString(calculate.getAHeadVol(),calculate.getAHeadVolAA()));
        tvBodyOut.setText(mkSelectedFractionOutString(calculate.getBodyVol(),calculate.getBodyVolAA()));
        tvBeforeTailsOut.setText(mkSelectedFractionOutString(calculate.getBTailsVol(),calculate.getBTailsVolAA()));
        tvTailsOut.setText(mkSelectedFractionOutString(calculate.getTailsVol(),calculate.getTailsVolAA()));

        tvHeadsBase.setText(mkBaseFractionOutString(getString(R.string.strHeads),calculate.getmPctHead()));
        tvAHeadsBase.setText(mkBaseFractionOutString(getString(R.string.strAfterHeads),calculate.getmPctAHead()));
        tvBodyBase.setText(mkBaseFractionOutString(getString(R.string.strBody),calculate.getmPctBody()));
        tvBTailsBase.setText(mkBaseFractionOutString(getString(R.string.strBeforeTails),calculate.getmPctBTails()));
        tvTailsBase.setText(mkBaseFractionOutString(getString(R.string.strTails),calculate.getmPctTails()));
    }
    private void outCubeParametr(){
        tvAlcInCubeOut.setText(String.valueOf(calculate.AlcInCudeCur())+getString(R.string.strSpace)+getString(R.string.strPercent));
        tvVolAbsAlcOut.setText(String.valueOf(calculate.getmVolAACur())+getString(R.string.strSpace)+getString(R.string.strMl));
        tvCubeBottomOut.setText(String.valueOf(calculate.getCubeBottom())+getString(R.string.strSpace)+getString(R.string.strMl));
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            onCalculate();
            Toast.makeText(getApplicationContext(), "Calculate!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.menuClear:
                clearData();
                break;
            case R.id.menuExit:
                finish();
                break;

        }
       return super.onOptionsItemSelected(item);
    }

    private void clearData(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sPref.edit();
        spEditor.clear();
        spEditor.apply();

        loadData(Constants.KEY_MAIN_ACTIVITY);
        loadFractionData(Constants.KEY_MAIN_ACTIVITY);
        onCalculate();
    }
}

