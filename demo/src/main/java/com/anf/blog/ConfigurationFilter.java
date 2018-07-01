package com.anf.blog;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFilter {
	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());// 添加过滤器
		registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
		registration.addInitParameter("name", "alue");// 添加默认参数
		registration.setName("MyFilter");// 设置优先级
		registration.setOrder(1);// 设置优先级
		return registration;
	}

	private static ThreadLocal<ServletRequest> requestMap = new ThreadLocal<ServletRequest>();

	public static ThreadLocal<ServletRequest> getRequestMap() {
		return requestMap;
	}

	public static void setRequestMap(ThreadLocal<ServletRequest> requestMap) {
		ConfigurationFilter.requestMap = requestMap;
	}

	public static ThreadLocal<ServletResponse> getResponseMap() {
		return responseMap;
	}

	public static void setResponseMap(ThreadLocal<ServletResponse> responseMap) {
		ConfigurationFilter.responseMap = responseMap;
	}

	private static ThreadLocal<ServletResponse> responseMap = new ThreadLocal<ServletResponse>();

	public class MyFilter implements Filter {

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {

		}

		@Override
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
				throws IOException, ServletException {
			requestMap.set(servletRequest);
			responseMap.set(servletResponse);
			filterChain.doFilter(servletRequest, servletResponse);
		}

		@Override
		public void destroy() {

		}
	}
}
