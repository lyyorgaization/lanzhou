package com.lucien.dap.framework.core.utils;

import java.util.Arrays;
import java.util.List;

public class PhoneUtil {

    public static List<String> phoneNumberSplit(String mutiphone){
        String[] phones = mutiphone.split("、|\\/|,");
        return Arrays.asList(phones);
    }

    public static boolean phoneNumberMatch(String phone1, String phone2){
        phone1 = phone1.replaceAll("-|\\D","");
        phone2 = phone2.replaceAll("-|\\D","");
        return phone1.equals(phone2);
    }

}
