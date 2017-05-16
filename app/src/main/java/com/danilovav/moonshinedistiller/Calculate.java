package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 */

public class Calculate {
    private int mAlcRAW;    // Крепость СС
    private int mValRAW;    // Щбъем СС
    private int mValWater;  // Объем воды для разбавления СС
    private int mAlcInCude; // Количество спирта в кубе
    private int mAlcInCudeCur;// Количество спирта в кубе текущее
    private int mAbsAlc;   // Абсолютный спирт в кубе
    private int mAbsAlcCur;     // Текущие параметры абсальтно спирта
    private int mBottomBalance; // кубовой остаток

    private int mVolFraction; //объем фракции
    private int mAbsFraction; //абсалютный спирт фракции

    private int mHeadVol;
    private int mHeadAbs;
    private int mAHeadVol;
    private int mAHeadAbs;
    private int mBodyVol;
    private int mBodyAbs;
    private int mBTailsVol;
    private int mBTailsAbs;
    private int mTailsVol;
    private int mTailsAbs;

    Calculate(){
        mAlcRAW=Constants.DEF_ACL_RAW;
        mValRAW=Constants.DEF_VAL_ACL_RAW;
        mValWater=Constants.DEF_VAL_WATER;
    }




    public void onCalculate(){
        mAbsAlc = mAlcRAW*mValRAW/100;
        mAlcInCude =mAbsAlc*100/(mValRAW+mValWater);
    }

    public void onCalcFractions(String mVol, String mAcl){  //расчет объема фракции и абсальтного спирта фракции
        int vol=Integer.valueOf(mVol);
        int acl=Integer.valueOf(mAcl);

        mAbsFraction=mAbsAlc*vol/100;
        mVolFraction=100*mAbsFraction/acl;
    }

    public String getVolFraction() {
        return String.valueOf(mVolFraction);
    }

    public String getAbsFraction() {
        return String.valueOf(mAbsFraction);
    }

    public void setAlcRAW(int mAlcRAW) {
        this.mAlcRAW = mAlcRAW;
    }

    public void setValRAW(int mValRAW) {
        this.mValRAW = mValRAW;
    }

    public void setValWater(int mValWater) {
        this.mValWater = mValWater;
    }


    public void setAlcRAW(String mAlcRAW) {
        this.mAlcRAW = Integer.valueOf(addZero(mAlcRAW));
    }

    public void setValRAW(String mValRAW) {
        this.mValRAW = Integer.valueOf(addZero(mValRAW));
    }

    public void setValWater(String mValWater) {
        this.mValWater = Integer.valueOf(addZero(mValWater));
    }

    public int getHeadVol() {
        return mHeadVol;
    }

    public void setHeadVol(int mHeadVol) {
        this.mHeadVol = mHeadVol;
    }

    public int getHeadAlc() {
        return mHeadAbs;
    }

    public void setHeadAlc(int mHeadAbs) {
        this.mHeadAbs = mHeadAbs;
    }

    public int getAHeadVol() {
        return mAHeadVol;
    }

    public void setAHeadVol(int mAHeadVol) {
        this.mAHeadVol = mAHeadVol;
    }

    public int getAHeadAlc() {
        return mAHeadAbs;
    }

    public void setAHeadAlc(int mAHeadAbs) {
        this.mAHeadAbs = mAHeadAbs;
    }

    public int getBodyVol() {
        return mBodyVol;
    }

    public void setBodyVol(int mBodyVol) {
        this.mBodyVol = mBodyVol;
    }

    public int getBodyAlc() {
        return mBodyAbs;
    }

    public void setBodyAlc(int mBodyAbs) {
        this.mBodyAbs = mBodyAbs;
    }

    public int getBTailsVol() {
        return mBTailsVol;
    }

    public void setBTailsVol(int mBTailsVol) {
        this.mBTailsVol = mBTailsVol;
    }

    public int getBTailsAlc() {
        return mBTailsAbs;
    }

    public void setBTailsAlc(int mBTailsAbs) {
        this.mBTailsAbs = mBTailsAbs;
    }

    public int getTailsVol() {
        return mTailsVol;
    }

    public void setTailsVol(int mTailsVol) {
        this.mTailsVol = mTailsVol;
    }

    public int getTailsAlc() {
        return mTailsAbs;
    }

    public void setTailsAlc(int mTailsAbs) {
        this.mTailsAbs = mTailsAbs;
    }



    public int getAbsAlc() {
        return mAbsAlc;
    }
    public int getAlcInCude() {
        return mAlcInCude;
    }

    public String addZero(String mText){  // устанавливаем 0 если поле пустое
        if(mText.isEmpty()) {return "0";}
        return  mText;
    }
    //расчет объема фракции из абсалютного спирта фракции
    public int getVlolumeFractions(int volAbsAlcogolFraction, int fractionAlcogol) {
        return (volAbsAlcogolFraction/fractionAlcogol*100);
    }
   // % спирта во фракции
    public int getAlcFraction(int volFraction, int absAlcFraction){
        if(volFraction == 0) {return(0);}
        else {
            return (absAlcFraction * 100 / volFraction);
        }
    }

}


