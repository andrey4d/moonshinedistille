package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 */

public class Calculate {
    private int mAlcRAW;        // Крепость СС
    private int mVolRAW;        // Oбъем СС
    private int mVolWater;      // Объем воды для разбавления СС
    private int mAlcInCude;     // Количество спирта в кубе
    private int mAlcInCudeCur;  // Количество спирта в кубе текущее
    private int mVolAbsAlc;         // Абсолютный спирт в кубе
    private int mVolAACur;     // Текущие параметры абсальтно спирта
    private int mBottomBalance; // кубовой остаток

    private int mVolFraction; //объем фракции
    private int mAbsFraction; //абсалютный спирт фракции

    private int mPctHead; //Процент голов от АС
    private int mPctAHead;
    private int mPctBody;
    private int mPctBTails;
    private int mPctTails;

    private int mHeadVol;
    private int mHeadVolAA;
    private int mAHeadVol;
    private int mAHeadVolAA;
    private int mBodyVol;
    private int mBodyVolAA;
    private int mBTailsVol;
    private int mBTailsVolAA;
    private int mTailsVol;
    private int mTailsVolAA;

    Calculate(){ // Конструктор
        this.mAlcRAW     =Constants.DEF_ACL_RAW;
        this.mVolRAW     =Constants.DEF_VAL_ACL_RAW;
        this.mVolWater   =Constants.DEF_VAL_WATER;

        this.mPctHead   = Integer.valueOf(Constants.DEF_VAL_HEADS );
        this.mPctAHead  = Integer.valueOf(Constants.DEF_VAL_AHEADS);
        this.mPctBody   = Integer.valueOf(Constants.DEF_VAL_BODY  );
        this.mPctBTails = Integer.valueOf(Constants.DEF_VAL_BTAILS);
        this.mPctTails  = Integer.valueOf(Constants.DEF_VAL_TAILS );
    }

    public void onCalculate(){
        this.mVolAbsAlc      = mAlcRAW* mVolRAW /100;                                                       //Объем обсолютного спирта
        this.mAlcInCude      = mVolAbsAlc *100/(mVolRAW + mVolWater);                                       //спиртуозность в кубе
        this.mBottomBalance  = (mVolRAW + mVolWater)-(mHeadVol+mAHeadVol+mBodyVol+mBTailsVol+mTailsVol);    //Расчет кубового остатка
        this.mVolAACur       = mVolAbsAlc-(mHeadVolAA+mAHeadVolAA+mBodyVolAA+mBTailsVolAA+mTailsVolAA);//Объем обсолютного спирта в кубе оостаток
        this.mAlcInCudeCur   = 100*mVolAACur/mBottomBalance;
    }

    public int getCubeBottom(){
        return(mBottomBalance);
    }

    public String addZero(String mText){  // устанавливаем 0 если поле пустое
        if(mText.isEmpty()) {return "0";}
        return  mText;
    }

    //расчет объема фракции из абсалютного спирта фракции
    public int getVlolumeFractions(int volAbsAlcogolFraction, int fractionAlcogol) {
        return (volAbsAlcogolFraction/fractionAlcogol*100);
    }

    public int getAlcFraction(int volFraction, int volAAFraction){ // % спирта во фракции
        if(volFraction == 0) {return(0);}
        else {
            return (volAAFraction * 100 / volFraction);
        }
    }

    //------Getter & Setter---------------------------------------------------------------------------
    public void setAlcRAW(int mAlcRAW) {
        this.mAlcRAW = mAlcRAW;
    }

    public void setValRAW(int mValRAW) {
        this.mVolRAW = mValRAW;
    }

    public void setValWater(int mValWater) {
        this.mVolWater = mValWater;
    }

    public int getmVolAACur() {
        return mVolAACur;
    }
    public int AlcInCudeCur(){
        return(mAlcInCudeCur);
    }

    public void setAlcRAW(String mAlcRAW) {
        this.mAlcRAW = Integer.valueOf(addZero(mAlcRAW));
    }

    public void setValRAW(String mValRAW) {
        this.mVolRAW = Integer.valueOf(addZero(mValRAW));
    }

    public void setValWater(String mValWater) {
        this.mVolWater = Integer.valueOf(addZero(mValWater));
    }

    public int getHeadVol() {
        return mHeadVol;
    }

    public void setHeadVol(int mHeadVol) {
        this.mHeadVol = mHeadVol;
    }

    public int getHeadVolAA() {
        return mHeadVolAA;
    }

    public void setHeadVolAA(int mHeadAbs) {
        this.mHeadVolAA = mHeadAbs;
    }

    public int getAHeadVol() {
        return mAHeadVol;
    }

    public void setAHeadVol(int mAHeadVol) {
        this.mAHeadVol = mAHeadVol;
    }

    public int getAHeadVolAA() {
        return mAHeadVolAA;
    }

    public void setAHeadVolAA(int mAHeadAbs) {
        this.mAHeadVolAA = mAHeadAbs;
    }

    public int getBodyVol() {
        return mBodyVol;
    }

    public void setBodyVol(int mBodyVol) {
        this.mBodyVol = mBodyVol;
    }

    public int getBodyVolAA() {
        return mBodyVolAA;
    }

    public void setBodyVolAA(int mBodyAbs) {
        this.mBodyVolAA = mBodyAbs;
    }

    public int getBTailsVol() {
        return mBTailsVol;
    }

    public void setBTailsVol(int mBTailsVol) {
        this.mBTailsVol = mBTailsVol;
    }

    public int getBTailsVolAA() {
        return mBTailsVolAA;
    }

    public void setBTailsVolAA(int mBTailsAbs) {
        this.mBTailsVolAA = mBTailsAbs;
    }

    public int getTailsVol() {
        return mTailsVol;
    }

    public void setTailsVol(int mTailsVol) {
        this.mTailsVol = mTailsVol;
    }

    public int getTailsVolAA() {
        return mTailsVolAA;
    }

    public void setTailsVolAA(int mTailsAbs) {
        this.mTailsVolAA = mTailsAbs;
    }

    public int getmPctHead() {
        return mPctHead;
    }

    public void setmPctHead(String mPctHead) {
        this.mPctHead = Integer.valueOf(mPctHead);
    }

    public int getmPctAHead() {
        return mPctAHead;
    }

    public void setmPctAHead(String mPctAHead) {
        this.mPctAHead = Integer.valueOf(mPctAHead);
    }

    public int getmPctBody() {
        return mPctBody;
    }

    public void setmPctBody(String mPctBody) {
        this.mPctBody = Integer.valueOf(mPctBody);
    }

    public int getmPctBTails() {
        return mPctBTails;
    }

    public void setmPctBTails(String mPctBTails) {
        this.mPctBTails = Integer.valueOf(mPctBTails);
    }

    public int getmPctTails() {
        return mPctTails;
    }

    public void setmPctTails(String mPctTails) {
        this.mPctTails = Integer.valueOf(mPctTails);
    }

    public void setmPctHead(int mPctHead) {
        this.mPctHead = mPctHead;
    }

    public void setmPctAHead(int mPctAHead) {
        this.mPctAHead = mPctAHead;
    }

    public void setmPctBody(int mPctBody) {
        this.mPctBody = mPctBody;
    }

    public void setmPctBTails(int mPctBTails) {
        this.mPctBTails = mPctBTails;
    }

    public void setmPctTails(int mPctTails) {
        this.mPctTails = mPctTails;
    }

    public int getAbsAlc() {
        return mVolAbsAlc;
    }
    public int getAlcInCude() {
        return mAlcInCude;
    }


}


