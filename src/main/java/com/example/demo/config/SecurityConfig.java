package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.service.NewUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private NewUserDetailService newuserDetailService;
	
	@Autowired
	private DataSource dataSource;
	
	/*
	 * 自定義的類來創建帳號密碼驗證
	 * */
	
	@Bean 
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
//		jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(newuserDetailService).passwordEncoder(password());
	}
	
	@Bean
	PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
	//http url讀取 認證判斷
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		//登出
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
//		http.logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		
		http.formLogin() //自定義自己編寫的登入頁面
			.loginPage("/login") //登入頁面設定
			.loginProcessingUrl("/login") //登入訪問路徑
			.defaultSuccessUrl("/BingX",true).permitAll() //登入成功後，跳轉路徑
			.and().authorizeRequests() //定義哪些url需要認證，哪些不需要認證，直接訪問
				.antMatchers("/","/login","/api/v1/webhook/{platform}").permitAll()//哪些url不需認證 直接訪問
//				.antMatchers("/index").hasAuthority("admins") //登入前 只有具有admins權限才可以訪問這個路徑
//				.antMatchers("/index").hasAnyAuthority("admins","manager") //登入前 只有具有admins或是manager權限才可以訪問這個路徑
			.anyRequest().authenticated() //所有url都可以請求
			.and().rememberMe().tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(60) //設置token有效時長  單位為秒
			.userDetailsService(newuserDetailService)
			.and().csrf().disable(); //關閉csrf
	}
	
	//網頁資源讀取 認證判斷
	@Override
	public void configure(WebSecurity web) throws Exception {
        web
           .ignoring()
           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }
}
