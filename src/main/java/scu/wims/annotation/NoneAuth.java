package scu.wims.annotation;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-06-03  21:50
 */
//表示不需要验证token的请求

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD})
//最重要的一个
@Retention(RetentionPolicy.RUNTIME)
public @interface NoneAuth {
}
