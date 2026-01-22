package com.green;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration => AppConfig 클래스는 환경 설정을 하는 클래스라는 것을 명령
// 여기서 객체를 생성하라는 의미
@Configuration
public class AppConfig {
	// MemberService를 Bean객체로 생성하세요.
//	@Bean
//	public MemberService memberService() {
//		// IoC 컨테이너에 MemberService 클래스를 탑재(생성)시켜라
//		return new MemberService();
//	}
}
