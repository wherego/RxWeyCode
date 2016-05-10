
package cn.wey.rxweycode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * Created by wey on 2016/2/16.
 */
public class StringUtils {

    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * 判断给定字符串是否空白串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0) && isBlank(str);
    }

    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 字符串长度
     *
     * @param str
     * @return
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * null转空字符串
     *
     * @param str
     * @return
     */
    public static String nullStringToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int StrToInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEquals(String str1, String str2) {
        if (isEmpty(str1) && isEmpty(str2)) {
            return true;
        }
        if (isEmpty(str1) && !isEmpty(str2)) {
            return false;
        }
        if (!isEmpty(str1) && isEmpty(str1)) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 判断两个字符串是否相等,忽略大小写
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEqualsIgnoreCase(String str1, String str2) {
        return isEquals(str1 == null ? str1 : str1.toLowerCase(),
                str2 == null ? str2 : str2.toLowerCase());
    }

    /**
     * 数据流转字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /**
     * 1989-02-04
     *
     * @param birthdayStr 生日字段 "yyyy-mm-dd"
     * @return 年龄
     */
    public static String birthdayToAge(String birthdayStr) {
        if (birthdayStr == null || birthdayStr.length() < 6) {
            return "";
        } else {
            int birthYear = Integer.parseInt(birthdayStr.substring(0, 4)); //1-4位
            int birthMonth = Integer.parseInt(birthdayStr.substring(5, 7)); //
            int birthDay = Integer.parseInt(birthdayStr.substring(8, 10));
            Calendar cal = new GregorianCalendar();
            int year = cal.get(Calendar.YEAR) - birthYear;
            int month = cal.get(Calendar.MONTH) + 1 - birthMonth;
            int day = cal.get(Calendar.DAY_OF_MONTH) - birthDay;
            //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减
            if (day < 0) {
                month -= 1;
                cal.add(Calendar.MONTH, -1);//得到上一个月，用来得到上个月的天数。   
                day = day + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (month < 0) {
                month = (month + 12) % 12;
                year--;
            }
            return "" + year;
        }
    }

    /*
     * 判断是否是正确的电话号码格式
	 */
    public static boolean isPhoneNum(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 字符转义
     *
     * @param keyWord
     * @return
     */
    public static String stringEscape(String keyWord) {
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&", "/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        return keyWord;
    }

    /**
     * ArrayList<String> 转 String[]
     *
     * @param als
     * @return
     */
    public static String transToString(ArrayList<String> als) {
        if (als == null || als.size() == 0)
            return null;
        // String[] sa = new String[als.size()];
        // return TextUtils.join(",", als.toArray(sa));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < als.size(); i++) {
            sb.append(als.get(i)).append(",");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    /**
     * String[] 转 ArrayList<String>
     *
     * @param str
     * @return
     */
    public static ArrayList<String> transToArray(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String[] sa = str.split(",");
        ArrayList<String> als = new ArrayList<String>();
        for (int i = 0; i < sa.length; i++) {
            als.add(sa[i]);
        }
        return als;
    }
}