package com.dd.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.java.Log;

@Log
@Configuration //샘이 추가하라고 한 어노테이션, 이것을 사용하여 xml과 같이 설정파일로 사용할 수 있다.
@EnableWebSecurity // 빈으로 인식하기 위함
//설정을 담당하는 WebSecurityConfigurerAdapter 클래스를 상속한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// UserService 만들면 필요 없음(거기에서 처리하므로) 처음 디비 연결 연습용
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserService UserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		log.info("security config...........");
		
		// authorizeRequests() -> 시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미
		// antMatchers() 에서는 특정한 경로를 지정.
		// permitAll() -> 모든 사용자 접근 가능, hasRole() 특정 권한을 가진 사람만 접근 가능
		
		//http.authorizeRequests().antMatchers("/user/join*/**").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/file/bellToken").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/file/**").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/chart/**").hasRole("USER");
		
		//http.authorizeRequests().antMatchers("/user/manager/**").hasRole("MANAGER")
								//.antMatchers("/user/manager/**").hasRole("USER");
		//http.authorizeRequests().antMatchers("/user/manager/**").hasRole("USER");
		
		//	http.authorizeRequests().antMatchers("/user/admin/**").hasRole("ADMIN");
		
		//http.csrf().disable().authorizeRequests().antMatchers("/visitorFiles/**").hasRole("USER");
		http.csrf().disable().authorizeRequests().antMatchers("/visitorFiles/**").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/sendVoice/**").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/resVoice/**").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/notify/**").permitAll();
		
		http.formLogin().loginPage("/user/login").defaultSuccessUrl("/file/file");
		
		//	오류 발생시 이쪽으로 돌려버림
		http.exceptionHandling().accessDeniedPage("/user/accessDenied");
		
		http.logout().invalidateHttpSession(true);
		
		http.userDetailsService(UserService); // 여기에서 인증매니저를 만들고 처리하겠다
		
		//http.rememberMe().key("dingdong").userDetailsService(UserService); //쿠키 사용
		
		http.rememberMe().key("dingdong")
		.userDetailsService(UserService)
		.tokenRepository(getJDBCRepository())
		.tokenValiditySeconds(60*60*24);
	}
	
	// 쿠키정보 디비에 저장
	private PersistentTokenRepository getJDBCRepository() {
		
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
		
	}
	
	/*//passwordEncider
	@Bean // 설정에서만 사용하는 것이 아니고, 다른 서비스에서도 이용되므로  별도의 빈으로 생성
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	*/
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		log.info("build Auth global....");
		auth.userDetailsService(UserService).passwordEncoder(passwordEncoder());
		
	}*/
	
	
	/*	UserService를 이용해서 객체를 주입 받아버리면 이 설정은 의미가 없음..
	 * User Service에서 내가 원하는 방식으로 인증을 처리할 수 있음 ( UserDetailsService를 임플리먼트해서)
	// AuthenticationManagerBuilder -> 인증 매니저를 생성하는 빌더
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		log.info("build Auth global");
		
		// 메모리 기반의 인증매니저를 생성
		auth.inMemoryAuthentication()
		.withUser("manager")
		.password("1111")
		.roles("MANAGER");
		
		String query1 =
		"SELECT uid username, upw password, true enabled FROM tbl_members WHERE uid= ?";
		String query2 = 
		"SELECT member uid, role_name role FROM tbl_member_roles WHERE member = ?";
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(query1)
		.rolePrefix("ROLE_")
		.authoritiesByUsernameQuery(query2);
		
		
		
	}*/
	
}
