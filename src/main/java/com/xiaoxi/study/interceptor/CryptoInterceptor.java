package com.xiaoxi.study.interceptor;

import com.xiaoxi.study.common.annotation.Decrypt;
import com.xiaoxi.study.common.annotation.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.lang.reflect.Field;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;

/**
 * @author liuteng
 * 自定义存储加解密拦截器
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class CryptoInterceptor implements Interceptor {

    private static final String DES = "DES";

    @Value("base64.encryptKey")
    private String cryptKey;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];
        encrypt(parameter);
        Object result = invocation.proceed();
        decrypt(result);
        return result;
    }

    private void encrypt(Object obj) {

        if (null == obj || obj.getClass().isPrimitive()) {
            return;
        }
        if (Collection.class.isAssignableFrom(obj.getClass())) {
            Collection<?> collection = (Collection<?>) obj;
            collection.forEach(this::encrypt);
        }
        if (Map.class.isAssignableFrom(obj.getClass())) {
            Map<?,?> map = (Map<?,?>) obj;
            Collection<?> values = map.values();
            values.forEach(this::encrypt);
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType().equals(String.class) && field.isAnnotationPresent(Encrypt.class)) {
                // 加密
                try {
                    String data = (String) field.get(obj);
                    KeyGenerator generator = KeyGenerator.getInstance(DES);
                    generator.init(new SecureRandom(cryptKey.getBytes()));
                    Key key = generator.generateKey();
                    Cipher cipher = Cipher.getInstance(DES);
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] byteFinal = cipher.doFinal(data.getBytes());
                    field.set(obj, Hex.encodeHexString(byteFinal));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private void decrypt(Object obj) {

        if (null == obj || obj.getClass().isPrimitive()) {
            return;
        }
        if (Collection.class.isAssignableFrom(obj.getClass())) {
            Collection<?> collection = (Collection<?>) obj;
            collection.forEach(this::decrypt);
        }
        if (Map.class.isAssignableFrom(obj.getClass())) {
            Map<?,?> map = (Map<?,?>) obj;
            Collection<?> values = map.values();
            values.forEach(this::decrypt);
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType().equals(String.class) && field.isAnnotationPresent(Decrypt.class)) {
                // 解密
                try {
                    String data = (String) field.get(obj);
                    KeyGenerator generator = KeyGenerator.getInstance(DES);
                    generator.init(new SecureRandom(cryptKey.getBytes()));
                    Key key = generator.generateKey();
                    Cipher cipher = Cipher.getInstance(DES);
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] byteFinal = cipher.doFinal(Hex.decodeHex(data));
                    field.set(obj, new String(byteFinal));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
