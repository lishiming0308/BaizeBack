package cn.hanwei.baize.baizeutil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JING
 *
 */
public class StringUtil {
    @SuppressWarnings("unused")
    private static Pattern pattern = null;
    static{
        pattern = Pattern.compile("[a-z]*");
    }
    public static String sqlJoin(String[] ls, String joinChar)
    {
        if (ls != null && ls.length != 0 && joinChar != null)
        {
            String temp = "";
            for (int i = 0; i < ls.length; i++)
            {
                if (!ls[i].trim().equals("")) {
                    if (i != ls.length - 1)
                    {
                        temp += "'" +  ls[i] + "'" + joinChar;
                    }
                    else
                    {
                        temp += "'" + ls[i] + "'";
                    }
                }
            }
            return temp;
        }
        return null;
    }

    public static String sqlJoin(List<String> ls, String joinChar)
    {
        if (ls != null && ls.size() != 0 && joinChar != null)
        {
            String temp = "";
            for (int i = 0; i < ls.size(); i++)
            {
                if (i == 0) {
                    temp += "'"+ls.get(i)+"'";
                    continue;
                }
                temp +=  ",'"+ls.get(i)+"'";
            }
            return temp;
        }
        return null;
    }
    public static String join(List<String> str_list, String joinStr) throws Exception {
        if (str_list != null && str_list.size() != 0) {
            String s = "";
            for (int i = 0; i < str_list.size(); i++) {
                if (i != str_list.size() - 1) {
                    s += str_list.get(i)+ joinStr;
                } else {
                    s += str_list.get(i);
                }
            }
            return s;
        }
        throw new Exception("参数1的值不能为空，且长度不能等于0");
    }

    public static String joinInteger(List<Integer> str_list, String joinStr) throws Exception {
        if (str_list != null && str_list.size() != 0) {
            String s = "";
            for (int i = 0; i < str_list.size(); i++) {
                if (i != str_list.size() - 1) {
                    s += str_list.get(i)+ joinStr;
                } else {
                    s += str_list.get(i);
                }
            }
            return s;
        }
        throw new Exception("参数1的值不能为空，且长度不能等于0");
    }

    public static String join(String[] str_list, String joinStr) throws Exception {
        if (str_list != null && str_list.length != 0) {
            String s = "";
            for (int i = 0; i < str_list.length; i++) {
                if (i != str_list.length - 1) {
                    s += str_list[i] + joinStr;
                } else {
                    s += str_list[i];
                }
            }
            return s;
        }
        throw new Exception("参数1的值不能为空，且长度不能等于0");
    }

    public static String join(int[] str_list, String joinStr) throws Exception {
        if (str_list != null && str_list.length != 0) {
            String s = "";
            for (int i = 0; i < str_list.length; i++) {
                if (i != str_list.length - 1) {
                    s += str_list[i] + joinStr;
                } else {
                    s += str_list[i];
                }
            }
            return s;
        }
        throw new Exception("参数1的值不能为空，且长度不能等于0");
    }

    public static String ufirst(String str){
        return  str.replace(str.charAt(0), str.toUpperCase().charAt(0));
    }

    public static boolean isEmpty(String str){
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }


    public static boolean isEmpty(Object obj){
        if (obj == null || obj.toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static  String[] toStringArray(Object[] os) {
        String[] strs = null;
        if (os == null || os.length== 0) {
            throw new IllegalArgumentException("参数不能为空且长度必须大于0");
        } else {
            strs = new String[os.length];
            int index = 0;
            for(Object o : os) {
                strs[index++] = o.toString();
            }
            return strs;
        }
    }


    public static boolean isExistsLowerStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLowerChar(char c) {
        return c >= 'a' && c <='z' ? true : false;
    }
    public static boolean isUpperChar(char c) {
        return c >= 'A' && c <='Z' ? true : false;
    }


    public static String camelToUnderLine(String str) {
        if (str == null) {
            return null;
        }
        for(int i=0; i < str.length();i++) {
            if (StringUtil.isUpperChar(str.charAt(i))) {
                return str.substring(0, i) + "_" + str.substring(i).toLowerCase();
            }
        }
        return null;
    }

    public static  List<String> convertToList(String[] ts){
        return Arrays.asList(ts);
    }

    public static String trimLeft(String s, char c) {
        if (StringUtil.isEmpty(s)) {
            return null;
        }

        for(int i=0; i < s.length();i++) {
            if (s.charAt(i) != c) {
                return s.substring(i);
            }
        }
        return s;
    }

    /**
     * @authro jingzhiqi
     * @param s
     * @param c
     * @param count
     * @return
     */
    public static String padLeft(String s, char c, int count) {
        if (StringUtil.isEmpty(s)) {
            return null;
        }

        int length = s.length();
        if (length > count) {
            return s;
        }

        if (length == count) {
            return s;
        }
        int tmp = count - length;//总共需要补的个数

        String padStr = "";

        for(int i=0; i < tmp;i++) {
            padStr += c;
        }
        return padStr + s;
    }
    public static boolean strIsNullOrEmpty(String s) {
        return (null == s || s.trim().length() < 1);
    }

    public static boolean isUrl(String url) {
        if (isEmpty(url)) {
            return false;
        }
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        return url.matches(regex);
    }
    /**
     * renguidong
     * 随机产生一个length长度的字符
     */
    public static String getRandomString(int length){
        //产生随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //循环length次
        for(int i=0; i<length; i++){
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result=Math.round(Math.random()*25+65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    //产生a-z的ASCII码
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf
                            (new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }
}
