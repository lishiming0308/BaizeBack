package cn.hanwei.baize.baizeutil;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author zhen
 * @description:
 * @date 2019-06-05 18:42
 */
public class CheckcodeUtil {
    public static void main(String[] args) throws IOException {
        String shenzhenguo = createBase64Str("aabbcc");
        System.out.println(shenzhenguo);
    }

    /**
     * @author  shenzhen
     * @description: 随机生成字符串
     * @return java.lang.String
     * @date 2019-06-06 9:15
     */
    public static final String createText(){
        String text = getProducer().createText();
        return text;
    }

    /**
     * @author  shenzhen
     * @description: 根据字符串生成对应的base64图片
     * @param text
     * @return java.lang.String
     * @date 2019-06-06 9:16
     */
    public static final String createBase64Str(String text){
        try {
            return imageToBase64(getProducer().createImage(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把缓存图像转换为 base64 字符串
     * @param image
     * @return
     * @throws IOException
     */
    public static String imageToBase64(BufferedImage image) throws IOException {
        byte[] imagedata = null;
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        ImageIO.write(image,"png",bao);
        imagedata=bao.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String BASE64IMAGE=encoder.encodeBuffer(imagedata).trim();
        BASE64IMAGE = BASE64IMAGE.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        return BASE64IMAGE;
    }


    private static final DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
    static {
        Properties properties=new Properties();
//        properties.setProperty("kaptcha.border", "yes");
//        properties.setProperty("kaptcha.border.color", "105,179,90");
//        properties.setProperty("kaptcha.textproducer.font.color", "blue");
//        properties.setProperty("kaptcha.image.width", "125");
//        properties.setProperty("kaptcha.image.height", "45");
//        properties.setProperty("kaptcha.session.key", "code");
//        properties.setProperty("kaptcha.textproducer.char.length", "4");
//        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
    }
    private static final Producer getProducer(){
        return defaultKaptcha;
    }

    public static class ByteUtil {
        /**
         * hex字符串转换为byte[]
         *
         * @param hex
         * @return
         */
        public static byte[] hex2byte(String hex) {
            byte[] bts = new byte[hex.length() / 2];
            for (int i = 0, j = 0; j < bts.length; j++) {
                bts[j] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
                i += 2;
            }
            return bts;
        }

        /**
         * byte[]转换为hex字符串,从低位开始
         *
         * @param b
         * @return
         */
        public static String byte2hex(byte[] b) {
            if (b == null) return null;
            StringBuffer buf = new StringBuffer(b.length * 2);
            for (int i = 0; i < b.length; i++) {
                String hex = Integer.toHexString(b[i] & 0xFF);
                if (hex.length() == 1)
                    buf.append('0');
                buf.append(hex.toUpperCase());
            }
            return buf.toString();
        }

        /**
         * 字节数组转化为十六进制字符串，但是可以排除从某个字节开始，偏移多大长度并不转换
         *
         * @param b
         * @param excludeIndex
         * @param offset
         * @return
         * @author jingzhiqi
         */
        public static String byte2hex(byte[] b, int excludeIndex, int offset) throws UnsupportedEncodingException {
            if (b == null) return null;
            StringBuffer buf = new StringBuffer(b.length * 2);
            int maxIndex = b.length - 1;
            for (int i = 0; i < b.length; ) {
                if (i == excludeIndex) {
                    byte[] temp = new byte[offset];
                    System.arraycopy(b, excludeIndex, temp, 0, offset);
                    buf.append(new String(temp));
                    i += offset;
                    if (i > maxIndex) {
                        break;
                    }
                } else {
                    String hex = Integer.toHexString(b[i] & 0xFF);
                    if (hex.length() == 1)
                        buf.append('0');
                    buf.append(hex.toUpperCase());
                    i++;
                }

            }
            return buf.toString();
        }


        /**
         * byte[]转换为hex字符串,从高位开始
         *
         * @param
         * @return
         */
        public static String byte2hex1(byte[] msg) {
            StringBuffer result = new StringBuffer();
            for (int i = msg.length - 1; i >= 0; i--) {
                byte b = msg[i];
                int a = b & 0xff;
                if (a < 0x10) {
                    result.append(0 + Integer.toHexString(a) + "");
                } else {
                    result.append(Integer.toHexString(a) + "");
                }
            }
            return result.toString().toUpperCase();
        }

        public static byte[] settime(byte[] message) {
            byte[] v = new byte[24];
            v[0] = (byte) (message[0] & 0xff);
            v[1] = (byte) (message[1] & 0xff);
            v[2] = (byte) (message[2] & 0xff);
            v[3] = (byte) (message[3] & 0xff);
            v[4] = (byte) (message[4] & 0xff);
            v[5] = (byte) (message[5] & 0xff);
            v[6] = (byte) (message[6] & 0xff);
            v[7] = (byte) (message[7] & 0xff);
            v[8] = (byte) (message[8] & 0xff);
            v[9] = (byte) (message[9] & 0xff);
            v[10] = (byte) (message[10] & 0xff);
            v[11] = (byte) (0x11 & 0xff);
            v[12] = (byte) (0x01 & 0xff);

            v[13] = (byte) (0x30 & 0xff);
            v[14] = (byte) (0x00 & 0xff);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int year = cal.get(Calendar.YEAR);
            v[15] = (byte) (cal.get(Calendar.SECOND) & 0xff);
            v[16] = (byte) (cal.get(Calendar.MINUTE) & 0xff);
            v[17] = (byte) (cal.get(Calendar.HOUR_OF_DAY) & 0xff);
            v[18] = (byte) (cal.get(Calendar.DAY_OF_MONTH) & 0xff);
            v[19] = (byte) ((cal.get(Calendar.MONTH)) & 0xff);
            v[20] = (byte) ((year % 100) & 0xff);

            byte[] crc = new byte[20];
            System.arraycopy(v, 1, crc, 0, 20);
            int crcvalue = CRC16.Crc16(crc);
            v[21] = (byte) (crcvalue & 0xff);
            v[22] = (byte) ((crcvalue >> 8) & 0xff);
            v[23] = 0x0D;
            return transfer(v);
        }

        public static byte[] echoByte(byte[] message) {
            byte[] v = new byte[16];
            v[0] = (byte) (message[0] & 0xff);
            v[1] = (byte) (message[1] & 0xff);
            v[2] = (byte) (message[2] & 0xff);
            v[3] = (byte) (message[3] & 0xff);
            v[4] = (byte) (message[4] & 0xff);
            v[5] = (byte) (message[5] & 0xff);
            v[6] = (byte) (message[6] & 0xff);
            v[7] = (byte) (message[7] & 0xff);
            v[8] = (byte) (message[8] & 0xff);
            v[9] = (byte) (message[9] & 0xff);
            v[10] = (byte) (message[10] & 0xff);
            v[11] = (byte) (message[11] & 0xff);
            v[12] = (byte) (message[12] & 0xff);
            byte[] crc = new byte[12];
            System.arraycopy(message, 1, crc, 0, 12);
            int crcvalue = CRC16.Crc16(crc);
            v[13] = (byte) (crcvalue & 0xff);
            v[14] = (byte) ((crcvalue >> 8) & 0xff);
            v[15] = (byte) (message[message.length - 1] & 0xff);
            return transfer(v);
        }

        /**
         * 将字节数组中的特殊字节进行转义
         * 0x3A->0x7E0x3B
         * 0x0D->0x7E0x0E
         * 0x7E->0x7E0x7F
         *
         * @param rawMessage
         * @return
         */
        public static byte[] transfer(byte[] rawMessage) {
            byte[] tmp = null;
            Map<Integer, Integer> changecount = new HashMap<>();
            for (int i = 1; i <= rawMessage.length - 2; i++) {
                if (rawMessage[i] == 0x3A || rawMessage[i] == 0x0D || rawMessage[i] == 0x7E)
                    changecount.put(i, i);
            }

            if (changecount.size() != 0) {
                tmp = new byte[rawMessage.length + changecount.size()];
                for (int i = 0, j = 0; i < rawMessage.length; i++, j++) {
                    if (changecount.get(i) == null) {
                        tmp[j] = rawMessage[i];
                    } else {
                        if (rawMessage[i] == 0x3A) {
                            tmp[j] = 0x7E;
                            tmp[j + 1] = 0x3B;
                            j++;
                        } else if (rawMessage[i] == 0x0D) {
                            tmp[j] = 0x7E;
                            tmp[j + 1] = 0x0E;
                            j++;
                        } else if (rawMessage[i] == 0x7E) {
                            tmp[j] = 0x7E;
                            tmp[j + 1] = 0x7F;
                            j++;
                        }
                    }
                }

            }
            if (tmp != null)
                return tmp;
            else
                return rawMessage;
        }

        /**
         * 将转义过字节数组中的恢复成原始的字节
         * 0x7E0x3B->0x3A
         * 0x7E0x0E->0x0D
         * 0x7E0x7F->0x7E
         *
         * @param rawdata
         * @return
         */
        public static byte[] untransfer(byte[] rawdata) {
            byte[] data = null;
            Map<Integer, Integer> changecount = new HashMap<Integer, Integer>();
            for (int i = 1; i < rawdata.length - 1; i++) {
                if (rawdata[i] == 0x7E)
                    changecount.put(i, i);
            }
            if (changecount.size() != 0) {
                data = new byte[rawdata.length - changecount.size()];
                for (int i = 0, j = 0; i < rawdata.length; i++, j++) {
                    if (changecount.get(i) == null) {
                        data[j] = rawdata[i];
                    } else {
                        if (rawdata[i + 1] == 0x3B) {
                            data[j] = 0x3A;
                            i++;
                        } else if (rawdata[i + 1] == 0x0E) {
                            data[j] = 0x0D;
                            i++;
                        } else if (rawdata[i + 1] == 0x7F) {
                            data[j] = 0x7E;
                            i++;
                        }
                    }
                }
            }
            if (data != null)
                return data;
            else
                return rawdata;
        }

        public static int hilowdata(byte highByte, byte lowByte) {
            return (highByte & 0xff) << 8 | (lowByte & 0xff);
        }

        public static byte[] reverse(byte[] datas) {
            int len = datas.length;
            byte[] rs = new byte[len];
            for (int i = 0; i < len; i++) {
                rs[i] = datas[len - i - 1];
            }
            return rs;
        }

        public static int hilowdata(byte[] vs) {
            int[] ints = new int[vs.length];
            for (int i = 0; i < vs.length; i++) {
                ints[i] = (vs[i] & 0xff) << i * 8;
            }
            int value = ints[0];
            for (int a = 1; a < ints.length; a++) {
                value = value | ints[a];
            }
            return value;
        }

        public static byte[] arraycopy(byte[] src, int offset, int len) {
            byte[] buf = new byte[len];
            System.arraycopy(src, offset, buf, 0, len);
            return buf;
        }

        public static String encodeBase64(byte[] input) throws Exception {
            return new BASE64Encoder().encode(input);
        }

        public static byte[] decodeBase64(String input) throws Exception {
            byte[] bt = null;
            try {
                sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
                bt = decoder.decodeBuffer(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bt;
        }

        public static byte[] toArray(List<Byte> bytes) {
            byte[] byteArr = new byte[bytes.size()];
            for (int i = 0; i < bytes.size(); i++) {
                byteArr[i] = bytes.get(i);
            }
            return byteArr;
        }

        public static short shortOfBigEndian(byte lowByte, byte highByte) {
            return (short) ((lowByte & 0xFF) << 8 | (highByte & 0xFF));
        }

        public static byte[] shortOfBigEndian(short data) {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) (data >> 8);
            bytes[1] = (byte) (data & 0xFF);
            return bytes;
        }


        public static int intOfBigEndian(byte[] bytes) {
            if (bytes == null) {
                throw new NullPointerException();
            }
            if (bytes.length != 4) {
                throw new InvalidParameterException("长度必须为4");
            }
            int len = bytes.length;
            int offset = 24;
            int rs = 0;
            for (int i = 0; i < len; i++) {
                rs |=   (bytes[i] & 0xFF) << offset ;
                offset -= 8;
            }
            return rs;

        }

        public static byte[] intOfBigEndian(int data) {
            byte[] bytes = new byte[4];
            bytes[0] = (byte) (data >> 24);
            bytes[1] = (byte) (data >> 16 & 0xFF);
            bytes[2] = (byte) (data >> 8 & 0xFF);
            bytes[3] = (byte) (data & 0xFF);
            return bytes;
        }

        public static long longOfBigEndian(byte[] bytes) {
            if (bytes == null) {
                throw new NullPointerException();
            }
            if (bytes.length != 8) {
                throw new InvalidParameterException("长度必须为8");
            }
            int len = bytes.length;
            int offset = 56;
            long rs = 0;
            for (int i = 0; i < len; i++) {
                rs |=  (bytes[i] & 0xFF) << offset ;
                offset -= 8;
            }
            return rs;

        }

        public static byte[] longOfBigEndian(long data) {
            byte[] bytes = new byte[8];
            bytes[0] = (byte) (data >> 56);
            bytes[1] = (byte) (data >> 48 & 0xFF);
            bytes[2] = (byte) (data >> 40 & 0xFF);
            bytes[3] = (byte) (data >> 32 & 0xFF);
            bytes[4] = (byte) (data >> 24 & 0xFF);
            bytes[5] = (byte) (data >> 16 & 0xFF);
            bytes[6] = (byte) (data >> 8 & 0xFF);
            bytes[7] = (byte) (data & 0xFF);
            return bytes;
        }

        /**
         * 连接多个字节数组得到一个新的字节数组。
         *
         * @param arrays
         * @return
         */
        public static byte[] concat(byte[]... arrays) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                for (byte[] arr : arrays) {
                    baos.write(arr);
                }
                return baos.toByteArray();
            } catch (IOException e) {
                throw new UnsupportedOperationException(e);
            }
        }

    }

    /**
     *
     * @author: 田平
     *
     * @Date: 下午 2:39 2017/10/17 0017
     */
    public static class MD5Util {
        private static final String AES="AES";
        private static final String UTF8="UTF-8";
        /**
         * 将源字符串使用MD5加密为字节数组
         * @param source
         * @return
         */
        public static byte[] encode2bytes(String source) {
            byte[] result = null;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(source.getBytes("UTF-8"));
                result = md.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return result;
        }

        public static String toMd5(String source) {
            byte[] result = null;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(source.getBytes("UTF-8"));
                result = md.digest();
                return ByteUtil.byte2hex(result);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 将源字符串使用MD5加密为32位16进制数
         * @param source
         * @return
         */
        public static String encode2hex(String source) {
            byte[] data = encode2bytes(source);

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                String hex = Integer.toHexString(0xff & data[i]);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        }

        /**
         * 验证字符串是否匹配
         * @param unknown 待验证的字符串
         * @param okHex 使用MD5加密过的16进制字符串
         * @return  匹配返回true，不匹配返回false
         */
        public static boolean validate(String unknown , String okHex) {
            return okHex.equals(encode2hex(unknown));
        }
        /**
         * AES加密
         * @param content
         * @param pkey
         * @return
         * @throws DecoderException
         */
        private static byte[] encrypt(String content, String pkey) throws DecoderException {
            try {
                String private_key=pkey;
                byte[] encodeFormat=null;
                try {
                    //秘钥 Hex解码为什么秘钥要进行解码，因为秘钥是某个秘钥明文进行了Hex编码后的值，所以在使用的时候要进行解码
                    encodeFormat = Hex.decodeHex(private_key.toCharArray());
                } catch (DecoderException e) {
                    e.printStackTrace();
                }
                SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);
                // Cipher对象实际完成加密操作
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                // 加密内容进行编码
                byte[] byteContent = content.getBytes(UTF8);
                // 用密匙初始化Cipher对象
                cipher.init(Cipher.ENCRYPT_MODE, key);
                // 正式执行加密操作
                byte[] result = cipher.doFinal(byteContent);
                return result;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * AES解密
         * @param contents
         * @param password
         * @return
         * @throws DecoderException
         */
        private static byte[] decrypt(String contents, String password) throws DecoderException {
            try {
                //密文使用Hex解码
                byte[]content = Hex.decodeHex(contents.toCharArray());
                //秘钥 Hex解码为什么秘钥要进行解码，因为秘钥是某个秘钥明文进行了Hex编码后的值，所以在使用的时候要进行解码
                byte[] encodeFormat = Hex.decodeHex(password.toCharArray());
                SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);
                // Cipher对象实际完成加密操作
                Cipher cipher = Cipher.getInstance(AES);
                // 用密匙初始化Cipher对象
                cipher.init(Cipher.DECRYPT_MODE, key);
                // 正式执行解密操作
                byte[] result = cipher.doFinal(content);
                return result;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * Aes加密
         * @param context 明文
         * @param private_key 秘钥
         * @return
         * @throws DecoderException
         */
        public static String encryption(String context,String private_key) throws DecoderException{
            //加密后的明文也就变成了密文
            byte[] encryptResult = encrypt(context, private_key);
            //密码文Hex编码
            String encryptResultStr = Hex.encodeHexString(encryptResult);
            return encryptResultStr;
        }
        /**
         * Aes解密
         * @param context 密文
         * @param private_key 秘钥
         * @return
         * @throws DecoderException
         * @throws UnsupportedEncodingException
         */
        public static String decryption(String context,String private_key) throws DecoderException, UnsupportedEncodingException{
            //这里的密文解密前先进行了Hex解码
            byte[] decryptResult = decrypt(context, private_key);
            String result = new String(decryptResult, UTF8);
            return result;
        }
        public static String addSalt(String password, String time) {
            String newPass = encode2hex(encode2hex(password)+time);
            return newPass;
        }

        public static String addSaltWithMd5(String md5Pwd, String time) {
            String newPass = encode2hex(md5Pwd + time);
            return newPass;
        }

        public static void main(String[] args) {
    //        System.out.println(encode2hex("shenzhenguo"));
            System.out.println(addSalt("aaaaaa", "2019-07-23 10:37:52"));
        }

    }
}
