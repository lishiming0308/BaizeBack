package cn.hanwei.baize.daqserver.common.secure.util;

import cn.hanwei.baize.baizeutil.CheckcodeUtil;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author zhen
 * @description:
 * @date 2019-06-19 18:23
 */
public class PwdWrapper {


    public static String wraperPwd(String pwd, String userName){
        return wraperMd5Pwd(CheckcodeUtil.MD5Util.toMd5(pwd).toLowerCase(), userName);
    }

    public static String wraperMd5Pwd(String md5Pwd, String md5UserName){
        String md5 = new SimpleHash("MD5", md5Pwd, md5UserName, 11).toString();
        if(md5.length() > 20){
            md5 = md5.substring(md5.length() - 20);
        }
        return md5;
    }



    public static void main(String[] args) {
//        System.out.println(wraperPwd("123456", "guo"));

        System.out.println(wraperPwd("org", "org")); // ae78c184e05bb4cd6f7a
        System.out.println(wraperPwd("123456", "guo")); // ae78c184e05bb4cd6f7a
//        System.out.println(wraperMd5Pwd(MD5Util.toMd5("123456").toLowerCase(), MD5Util.toMd5("guo").toLowerCase())); // ae78c184e05bb4cd6f7a
//        String guo = MD5Util.toMd5(MD5Util.toMd5(MD5Util.toMd5("guo").toLowerCase() + "123456").toLowerCase() + "123456" ).toLowerCase();
//        System.out.println(guo);


    }
}
