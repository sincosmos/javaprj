package com.sincosmos;

import org.apache.commons.codec.Charsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.Security;

/**
 * AES加密和解密工具,可以对字符串进行加密和解密操作  。
 * @author xiejun
 *
 */
public class AesUtils {

  /** 字符串默认键值     */
  private static String strDefaultKey = "national";
  private static String aes_IV = "12345678";

  /** 加密工具     */
  private Cipher encryptCipher = null;

  /** 解密工具     */
  private Cipher decryptCipher = null;

  
  public static void main(String[] args) throws Exception {
	  AesUtils aes = new AesUtils("0b79937a9608e64c","0b79937a9608e64c");
	  System.out.println(aes.decrypt("596d0754c0d37e16feb2a84d593961205fa17cbd727b6f2da3a3d8cea0af1da1") + "==");
	  System.out.println(aes.decrypt("ba98f34c80d0d18393426f6f3e5b6023") + "==");

    System.out.println(aes.decrypt("f57ef34626d384b4dcaf8d2bf3f5c9f5b96d63feb33c4f7fac809af4e5633268") + "==");

      System.out.println("==      " + "==");
  }
  
  /**
   * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
   * hexStr2ByteArr(String strIn) 互为可逆的转换过程
   *
   * @param arrB
   *            需要转换的byte数组
   * @return 转换后的字符串
   * @throws Exception
   *             本方法不处理任何异常，所有异常全部抛出
   */
  public static String byteArr2HexStr(byte[] arrB) throws Exception {
    int iLen = arrB.length;
    // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
    StringBuffer sb = new StringBuffer(iLen * 2);
    for (int i = 0; i < iLen; i++) {
      int intTmp = arrB[i];
      // 把负数转换为正数
      while (intTmp < 0) {
        intTmp = intTmp + 256;
      }
      // 小于0F的数需要在前面补0
      if (intTmp < 16) {
        sb.append("0");
      }
      sb.append(Integer.toString(intTmp, 16));
    }
    return sb.toString();
  }

  /**
   * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
   * 互为可逆的转换过程
   *
   * @param strIn
   *            需要转换的字符串
   * @return 转换后的byte数组
   * @throws Exception
   *             本方法不处理任何异常，所有异常全部抛出
   */
  public static byte[] hexStr2ByteArr(String strIn) throws Exception {
    byte[] arrB = strIn.getBytes(Charsets.UTF_8);
    int iLen = arrB.length;

    // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
    byte[] arrOut = new byte[iLen / 2];
    for (int i = 0; i < iLen; i = i + 2) {
      String strTmp = new String(arrB, i, 2,Charsets.UTF_8);
      arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
    }
    return arrOut;
  }

  /**
   * 默认构造方法，使用默认密钥
   *
   * @throws Exception
   */
  public AesUtils() throws Exception {
    this(strDefaultKey,aes_IV);
  }

  /**
   * 指定密钥构造方法
   *
   * @param strKey 指定的密钥
   * @throws Exception
   */
  public AesUtils(String strKey, String aes_iv) throws Exception {
    Security.addProvider(new com.sun.crypto.provider.SunJCE());
    Key key = getKey(strKey.getBytes(Charsets.UTF_8));

    encryptCipher = Cipher.getInstance("AES/CBC/NoPadding");
    encryptCipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(aes_iv.getBytes()));

    decryptCipher = Cipher.getInstance("AES/CBC/NoPadding");
    decryptCipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(aes_iv.getBytes()));
  }
  
  /**
   * 加密字节数组
   *
   * @param arrB 需加密的字节数组
   * @return 加密后的字节数组
   * @throws Exception
   */
  public byte[] encrypt(byte[] arrB) throws Exception {
    return encryptCipher.doFinal(arrB);
  }

  /**
   * 加密字符串
   *
   * @param strIn 需加密的字符串
   * @return 加密后的字符串
   * @throws Exception
   */
  public String encrypt(String strIn) throws Exception {
    return byteArr2HexStr(encrypt(strIn.getBytes(Charsets.UTF_8)));
  }

  /**
   * 解密字节数组
   *
   * @param arrB 需解密的字节数组
   * @return 解密后的字节数组
   * @throws Exception
   */
  public byte[] decrypt(byte[] arrB) throws Exception {
    return decryptCipher.doFinal(arrB);
  }

  /**
   * 解密字符串
   *
   * @param strIn 需解密的字符串
   * @return 解密后的字符串
   * @throws Exception
   */
  public String decrypt(String strIn) throws Exception {
    return new String(decrypt(hexStr2ByteArr(strIn)),Charsets.UTF_8);
  }

  /**
   * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
   *
   * @param arrBTmp 构成该字符串的字节数组
   * @return 生成的密钥
   * @throws Exception
   */
  private Key getKey(byte[] arrBTmp) throws Exception {
    // 创建一个空的8位字节数组（默认值为0）
    byte[] arrB = new byte[16];

    // 将原始字节数组转换为8位
    for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
      arrB[i] = arrBTmp[i];
    }

    // 生成密钥
    Key key = new javax.crypto.spec.SecretKeySpec(arrB, "AES");

    return key;
  }
}