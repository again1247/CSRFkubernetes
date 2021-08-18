package security.k8s.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@EnableWebSecurity
class MyWebSecurity extends WebSecurityConfigurerAdapter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN")
        ;
        log.debug("auth={}", auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	// 로그인 설정
        http.authorizeRequests()
        	   .antMatchers("/login","/admin","/invalid", "/error**").permitAll()
            .antMatchers("/**").authenticated();

        http
            // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
            .formLogin()
            .defaultSuccessUrl("/")
            .failureUrl("/error")
            .usernameParameter("id_user")
            .passwordParameter("password");
        http
            // 로그아웃 관련 설정
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
         .and()
           	.csrf()
           	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http
	      .sessionManagement()
	      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	      .invalidSessionUrl("/invalid");

    }
}