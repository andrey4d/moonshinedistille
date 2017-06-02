package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 * Расчет параметров
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

    private int mPctHead; //Процент голов от АС
    private int mPctAHead;
    private int mPctBody;
    private int mPctBTails;
    private int mPctTails;

    private int mHeadVol;
    private float mHeadVolAA;
    private int mAHeadVol;
    private float mAHeadVolAA;
    private int mBodyVol;
    private float mBodyVolAA;
    private int mBTailsVol;
    private float mBTailsVolAA;
    private int mTailsVol;
    private float mTailsVolAA;

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

    public void doCalculate(){
        this.mVolAbsAlc      = Math.round(mAlcRAW* mVolRAW /100);                                                       //Объем обсолютного спирта
        this.mAlcInCude      = Math.round(mVolAbsAlc *100/(mVolRAW + mVolWater));                                       //спиртуозность в кубе
        this.mBottomBalance  = (mVolRAW + mVolWater)-(mHeadVol+mAHeadVol+mBodyVol+mBTailsVol+mTailsVol);    //Расчет кубового остатка
        this.mVolAACur       = mVolAbsAlc-Math.round(mHeadVolAA+mAHeadVolAA+mBodyVolAA+mBTailsVolAA+mTailsVolAA);     //Объем обсолютного спирта в кубе оостаток
        this.mAlcInCudeCur   = Math.round(100*mVolAACur/mBottomBalance);
    }

    public int getCubeBottom(){
        return(mBottomBalance);
    }

    public String addZero(String mText){  // устанавливаем 0 если поле пустое
        if(mText.isEmpty()) {return "0";}
        return  mText;
    }

//    //расчет объема фракции из абсалютного спирта фракции
//    public int getVolumeFractions(int volAbsAlcogolFraction, int fractionAlcogol) {
//        int vol = Math.round(100*volAbsAlcogolFraction/fractionAlcogol);
//        return (vol);
//    }
//
//    public int getAlcFraction(int volFraction, int volAAFraction){ // % спирта во фракции
//        if(volFraction == 0) {return(0);}
//        else {
//            return (Math.round(volAAFraction * 100 / volFraction));
//        }
//    }

    public float getfAlcFraction(int volFraction, float volAAFraction) { // % спирта во фракции
        if(volFraction == 0) {return(0);}
        else {
            return (volAAFraction * 100 / volFraction);
        }
    }

    // Расчет АС фракции от объема АС
    public String baseFractionAA(int pctFraction){
        int baseAA=Math.round(mVolAbsAlc/100*pctFraction);
        return (String.valueOf(baseAA));
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

    public float getHeadVolAA() {
        return mHeadVolAA;
    }

    public void setHeadVolAA(float mHeadAbs) {
        this.mHeadVolAA = mHeadAbs;
    }

    public int getAHeadVol() {
        return mAHeadVol;
    }

    public void setAHeadVol(int mAHeadVol) {
        this.mAHeadVol = mAHeadVol;
    }

    public float getAHeadVolAA() {
        return mAHeadVolAA;
    }

    public void setAHeadVolAA(float mAHeadAbs) {
        this.mAHeadVolAA = mAHeadAbs;
    }

    public int getBodyVol() {
        return mBodyVol;
    }

    public void setBodyVol(int mBodyVol) {
        this.mBodyVol = mBodyVol;
    }

    public float getBodyVolAA() {
        return mBodyVolAA;
    }

    public void setBodyVolAA(float mBodyAbs) {
        this.mBodyVolAA = mBodyAbs;
    }

    public int getBTailsVol() {
        return mBTailsVol;
    }

    public void setBTailsVol(int mBTailsVol) {
        this.mBTailsVol = mBTailsVol;
    }

    public float getBTailsVolAA() {
        return mBTailsVolAA;
    }

    public void setBTailsVolAA(float mBTailsAbs) {
        this.mBTailsVolAA = mBTailsAbs;
    }

    public int getTailsVol() {
        return mTailsVol;
    }

    public void setTailsVol(int mTailsVol) {
        this.mTailsVol = mTailsVol;
    }

    public float getTailsVolAA() {
        return mTailsVolAA;
    }

    public void setTailsVolAA(float mTailsAbs) {
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


