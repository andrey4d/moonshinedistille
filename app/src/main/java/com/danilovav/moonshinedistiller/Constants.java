package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 */

public class Constants {

    public static final String KEY_TEST = "TEST";

    final static public int DEF_ACL_RAW = 60;
    final static public int DEF_VAL_ACL_RAW = 1000;
    final static public int DEF_VAL_WATER = 1000;
    final static public int DEF_INT_NULL = 0;
    final static public String DEF_VAL_HEADS = "3";
    final static public String DEF_VAL_AHEADS= "7";
    final static public String DEF_VAL_BODY  = "70";
    final static public String DEF_VAL_BTAILS= "5";
    final static public String DEF_VAL_TAILS = "10";

    final static public String KEY_MAIN_ACTIVITY = "MAIN_KEY";
    final static public String ALC= "ALC";
    final static public String RAWVOL = "RAWVOL";
    final static public String WATERVOL = "WATERVOL";

    final static public int REQUEST_CODE_HEADS = 1;
    final static public int REQUEST_CODE_AFTER_HEADS = 2;
    final static public int REQUEST_CODE_BODY = 3;
    final static public int REQUEST_CODE_BEFORE_TAILS = 4;
    final static public int REQUEST_CODE_TAILS = 5;

    final static public String HEADS = "Heads";
    final static public String AHEADS = "AfterHeads";
    final static public String BODY = "Body";
    final static public String BTAILS = "BeforeTails";
    final static public String TAILS = "Tails";

    final static public String FRACTION_VOLUME = "FractionVolume";
    final static public String FRACTION_ALC = "FractionAlc";



    public static String formatFloatToString(final float f)
    {
                final int i=(int)f;
        if(f==i)
            return Integer.toString(i);
        return Float.toString(f);
    }
}
