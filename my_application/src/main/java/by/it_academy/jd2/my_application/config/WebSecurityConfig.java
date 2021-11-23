package by.it_academy.jd2.my_application.config;

import by.it_academy.jd2.my_application.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter ;

    WebSecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/product/**","/api/recipe/**","/api/profile/**")
                .hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/api/product/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.PUT,"/api/product/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.DELETE,"/api/product/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/api/recipe/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.PUT,"/api/recipe/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.DELETE,"/api/recipe/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/register", "/auth").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
