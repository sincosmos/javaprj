package com.sincosmos;

/*
* 说明：密匙key的长度必须是8的倍数
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DesTest {

   // 默认密匙
   private static String key = "19491001";
   // 缓冲区大小
   private static final int BufferSize = 1024 * 1024 * 10;

   public static void main(String[] args) {
	   DesTest.test();
   }
   
   // 加密解密测试测试
   public static void test() {
       String text = "111测试asdY^&*NN!__s some plaintext!";
       System.out.println("加密前的明文:" + text);

       String cryperText = "";
       try {
           cryperText = toHexString(encrypt(text));
           System.out.println("加密后的明文:" + cryperText);
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

       try {
           System.out.println("解密后的明文:"
                   + new String(decrypt(convertHexString(cryperText), key),
                           "UTF-8"));
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       try {
           System.in.read();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }

   // 设置默认密匙
   public static void setKey(String k) {
       key = k;
   }

   // 用指定密匙对密文message解密并返回解密后byte[]
   public static byte[] decrypt(String message, String key) throws Exception {
       return decrypt(message.getBytes("UTF-8"), key);
   }

   // 用默认密匙对密文message解密并返回解密后byte[]
   public static byte[] decrypt(String message) throws Exception {
       return decrypt(message, key);
   }

   // 用指定密匙对密文数据data解密并返回解密后byte[]
   public static byte[] decrypt(byte[] data, String key) throws Exception {
       Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
       DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
       SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
       IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
       cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
       return cipher.doFinal(data);
   }

   // 用指定密匙对密文data从offset开始长度为len的数据解密并返回解密后byte[]
   public static byte[] decrypt(byte[] data, int offset, int len, String key)
           throws Exception {
       Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
       DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
       SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
       IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
       cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
       return cipher.doFinal(data, offset, len);
   }

   // 用指定密匙对字符串message加密并返回加密后byte[]
   public static byte[] encrypt(String message, String key) throws Exception {
       return encrypt(message.getBytes("UTF-8"), key);
   }

   // 用默认密匙对字符串message加密并返回加密后byte[]
   public static byte[] encrypt(String message) throws Exception {
       return encrypt(message, key);
   }

   // 用指定密匙对数据data加密并返回加密后byte[]
   public static byte[] encrypt(byte[] data, String key) throws Exception {
       return encrypt(data, 0, data.length, key);
   }

   // 用指定密匙对密文data从offset开始长度为len的数据加密并返回加密后byte[]
   public static byte[] encrypt(byte[] data, int offset, int len, String key)
           throws Exception {
       Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

       DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
       SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
       IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
       cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
       return cipher.doFinal(data, offset, len);
   }


   public static byte[] convertHexString(String ss) {
       byte digest[] = new byte[ss.length() / 2];
       for (int i = 0; i < digest.length; i++) {
           String byteString = ss.substring(2 * i, 2 * i + 2);
           int byteValue = Integer.parseInt(byteString, 16);
           digest[i] = (byte) byteValue;
       }

       return digest;
   }

   public static String toHexString(byte b[]) {
       StringBuffer hexString = new StringBuffer();
       for (int i = 0; i < b.length; i++) {
           String plainText = Integer.toHexString(0xff & b[i]);
           if (plainText.length() < 2)
               plainText = "0" + plainText;
           hexString.append(plainText);
       }

       return hexString.toString();
   }

   //用指定密匙key对路径为path的文件加密并写入路径为out的文件中 
   public static void fileEncrypt(String path, String out, String key)
           throws Exception {
       File file = new File(path);
       FileInputStream in = new FileInputStream(file);
       file = new File(out);
       if (file.exists())
           file.delete();
       file.createNewFile();
       FileOutputStream fos = new FileOutputStream(file);
       byte[] buffer = new byte[BufferSize];
       byte[] outbuf;
       int size = in.available();
       int done = 0;
       while (in.available() > 0) {
           int len = in.read(buffer);
           outbuf = DesTest.encrypt(buffer, 0, len, key);
           fos.write(outbuf, 0, outbuf.length);
           done += len;
           System.out.print(100 * (long) done / size);
           System.out.println('%');

       }
       fos.close();
       in.close();
   }

   //用指定密匙key对路径为path的文件加密并写入路径为out的文件中，而且不使用缓冲区
   public static void $fileEncrypt(String path, String out, String key)
           throws Exception {
       File file = new File(path);
       FileInputStream in = new FileInputStream(file);
       file = new File(out);
       if (file.exists())
           file.delete();
       file.createNewFile();
       FileOutputStream fos = new FileOutputStream(file);
       byte[] buffer = new byte[in.available()];
       in.read(buffer);
       buffer = DesTest.encrypt(buffer, key);
       fos.write(buffer, 0, buffer.length);
       fos.close();
       in.close();
   }

   //用指定密匙key将字符串message加密并写入路劲为out的文件中
   public static void StringEncryptIntoFile(String message, String out,
           String key) throws Exception {
       File file = new File(out);
       if (file.exists())
           file.delete();
       file.createNewFile();
       FileOutputStream fos = new FileOutputStream(file);
       byte[] data = encrypt(message, key);
       fos.write(data);
       fos.close();
   }

   //用指定密匙key对路径为path的密文文件解密并写入路径为out的文件中 
   public static void fileDecrypt(String path, String out, String key)
           throws Exception {
       File file = new File(path);
       FileInputStream in = new FileInputStream(file);
       file = new File(out);
       if (file.exists())
           file.delete();
       file.createNewFile();
       FileOutputStream fos = new FileOutputStream(file);
       byte[] buffer = new byte[BufferSize + 8];
       byte[] outbuf;
       int size = in.available();
       int done = 0;
       while (in.available() > 0) {
           int len = in.read(buffer);
           outbuf = DesTest.decrypt(buffer, 0, len, key);
           fos.write(outbuf, 0, outbuf.length);
           done += len;
           System.out.print(100 * (long) done / size);
           System.out.println('%');
       }
       fos.close();
       in.close();
   }

   //用指定密匙key对路径为path的密文文件解密并写入路径为out的文件中 ，且不使用缓冲区
   public static void $fileDecrypt(String path, String out, String key)
           throws Exception {
       File file = new File(path);
       FileInputStream in = new FileInputStream(file);
       file = new File(out);
       if (file.exists())
           file.delete();
       file.createNewFile();
       FileOutputStream fos = new FileOutputStream(file);
       byte[] buffer = new byte[in.available()];
       in.read(buffer);
       buffer = DesTest.decrypt(buffer, key);
       fos.write(buffer, 0, buffer.length);
       fos.close();
       in.close();
   }

   //用指定密匙key对路径为path的密文文件解密得到明文字符串并返回
   public static String fileDecrypt(String path, String key) throws Exception {
       File file = new File(path);
       FileInputStream in = new FileInputStream(file);
       byte[] buffer = new byte[BufferSize + 8];
       StringBuffer sb = new StringBuffer();
       int size = in.available();
       int done = 0;
       byte[] outbuf;
       while (in.available() > 0) {
           int len = in.read(buffer);
           outbuf = DesTest.decrypt(buffer, 0, len, key);
           sb.append(new String(outbuf, 0, outbuf.length, "UTF-8"));
           done += len;
           System.out.print(100 * (long) done / size);
           System.out.println('%');
       }
       in.close();
       return sb.toString();
   }
}