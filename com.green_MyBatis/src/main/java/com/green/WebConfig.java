package com.green;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 이 클래스는 스프링부트의 설정파일입니다.
public class WebConfig implements WebMvcConfigurer {
	
	// addResourceHandlers : 정적 리소스(HTML, CSS, JS등)을 관리하는 메서드이다.
	// 외부의 물리적인 경로를 웹에서 사용하는 URL 주소로 매핑하는 설정을 담당한다.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/img/**")
				.addResourceLocations("file:///C:/Spring_Boot/com.green_MyBatis/frontend/public/img/");
	}
	
	
	
}
