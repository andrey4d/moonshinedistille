package com.danilovav.moonshinedistiller;

/**
 * Created by DanilovAV on 10.03.2017.
 */

public class Constants {


    final static public int DEF_ACL_RAW = 80;
    final static public int DEF_VAL_ACL_RAW = 1000;
    final static public int DEF_VAL_WATER = 1000;
    public static final String KEY_TEST = "TEST";

    public static String formatFloatToString(final float f)
    {
                final int i=(int)f;
        if(f==i)
            return Integer.toString(i);
        return Float.toString(f);
    }
}
