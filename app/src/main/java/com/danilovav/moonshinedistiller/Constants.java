package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 */

public class Constants {

    public static final String KEY_TEST = "TEST";

    final static public int DEF_ACL_RAW = 80;
    final static public int DEF_VAL_ACL_RAW = 1000;
    final static public int DEF_VAL_WATER = 1000;

    final static public int REQUEST_CODE_HEADS = 1;
    final static public int REQUEST_CODE_AFTER_HEADS = 2;
    final static public int REQUEST_CODE_BODY = 3;
    final static public int REQUEST_CODE_BEFORE_TAILS = 4;
    final static public int REQUEST_CODE_TAILS = 5;

    final static public String HAEDS = "Heads";


    public static String formatFloatToString(final float f)
    {
                final int i=(int)f;
        if(f==i)
            return Integer.toString(i);
        return Float.toString(f);
    }
}
