package com.example.naumenwebproject.config;

import com.example.naumenwebproject.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
        // говорим спринг исмпользовать собственую странцу аутентификации
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //конфигурируем сам spring security (вход, ошибки и т.д)
        // конфигурируем авторизацию

        http.csrf().disable() // отключаем защиту от меж сайтовой потделки запросов
                .authorizeRequests() // включаем настройки для авторизтрованых и не авторизированых пользователей
                .antMatchers("/admin").hasRole("ADMIN")// на страницу админа может попасть только админ
                .antMatchers("/auth/login","/auth/registration", "/error").permitAll() // пускать всех не авторизированых пользователей на эти страницы
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and() // заканчиваем настройку авторизации
                .formLogin().loginPage("/auth/login") // какую подключить форму
                .loginProcessingUrl("/process_login") // с какого адреса забрать данные
                .defaultSuccessUrl("/hello",true) // на какую страницу вести после аутентификации
                .failureUrl("/auth/login?error") // куда будет вести в случае неудачной аутентификации
                .and()
                .logout().logoutUrl("/logout") //если человек проходит по этому адресу то он разлогиниваеться и стераютться все кукис
                .logoutSuccessUrl("/auth/login"); // при разлогировании он переходит по этому пути
    }

    // настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder()); // сравнивать пароль при авторизации с паролем в базе данных
    }
    // указываем с каким алгоритмом будем проводить шифрование
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
