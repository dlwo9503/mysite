package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // 이 어노테이션을 어디다가 붙일지를 정하는거임, 둘 다 넣어주면 TYPE에도 넣어줄 수 있음, 맨 위에 넣을 수 있다는 말
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
//	String value() default ""; // 벨류는 특별해서 따로 명시를 안해줘도 됨
	public String role() default "USER";
	public boolean test() default false;
}
