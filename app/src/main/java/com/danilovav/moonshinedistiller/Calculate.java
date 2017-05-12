package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 */

public class Calculate {
    private int mAlcRAW;
    private int mValRAW;
    private int mValWater;
    private int mAlcInCude;
    private int mAlcInCudeCur;
    private int mAbsAlc;
    private int mAbsAlcCur;     // Текущие параметры абсальтно спирта
    private int mBottomBalance; // кубовой остаток

    private int mVolFraction; //объем фракции
    private int mAbsFraction; //абсалютный спирт фракции


    Calculate(){
        mAlcRAW=Constants.DEF_ACL_RAW;
        mValRAW=Constants.DEF_VAL_ACL_RAW;
        mValWater=Constants.DEF_VAL_WATER;
    }




    public void onCalculate(){
        mAbsAlc=   mAlcRAW*mValRAW/100;
        mAlcInCude =mAbsAlc*100/(mValRAW+mValWater);
    }

    public void onCalcFractions(String mVol, String mAcl){  //расчет объема фракции и абсальтного спирта фракции
        int vol=Integer.valueOf(mVol);
        int acl=Integer.valueOf(mAcl);

        mAbsFraction=mAbsAlc*vol/100;
        mVolFraction=100*mAbsFraction/acl;
    }

    public String getmVolFraction() {
        return String.valueOf(mVolFraction);
    }

    public String getmAbsFraction() {
        return String.valueOf(mAbsFraction);
    }

    public void setmAlcRAW(int mAlcRAW) {
        this.mAlcRAW = mAlcRAW;
    }

    public void setmValRAW(int mValRAW) {
        this.mValRAW = mValRAW;
    }

    public void setmValWater(int mValWater) {
        this.mValWater = mValWater;
    }


    public void setmAlcRAW(String mAlcRAW) {
        this.mAlcRAW = Integer.valueOf(mAlcRAW);
    }

    public void setmValRAW(String mValRAW) {
        this.mValRAW = Integer.valueOf(mValRAW);
    }

    public void setmValWater(String mValWater) {
        this.mValWater = Integer.valueOf(mValWater);
    }


    public int getmAbsAlc() {
        return mAbsAlc;
    }
    public int getmAlcInCude() {
        return mAlcInCude;
    }

    public String addZero(String mText){
        if(mText.isEmpty()) {return "0";}
        return  mText;
    }
    //расчет объема фракции из абсалютного спирта фракции
    public int getVlolumeFractions(int volAbsAlcogolFraction, int fractionAlcogol) {
        return (volAbsAlcogolFraction/fractionAlcogol*100);
    }
   // % спирта во фракции
    public int getAlcFraction(int volFraction, int absAlcFraction){
        return(absAlcFraction*100/volFraction);
    }

}


