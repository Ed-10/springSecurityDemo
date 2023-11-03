package com.example.springsecuritydemo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Modificacion del Core de Spring Security
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//Spring entiende que aqui se van a realizar las configuraciones de todo Security
public class Seguridad {
    //Metodo para ajustar todo lo referente a la autentificacion
    //Inyectando LoginPersonalizado
    @Autowired
    private LoginPersonalizado loginPersonalizado;
    @Bean
    public AuthenticationManager autenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //Administracion de has de contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests()

        //Vistas que no requieren autentificacion
        .antMatchers(
                "/",
                "/liberada/**",
                "/acceso/registro"
        )
                .permitAll()
        //Asugnar permisas a URL por Roles
                .antMatchers(
                        "/protegido/**"
                ).hasAnyAuthority("ROLE_ADMIN")
                //.hasAnyAuthority("ROLE_ADMIN","ROLE_USER")

        //Configuraciones genrales
                .anyRequest().authenticated()
                //Pagina del login
                    //.and().formLogin().permitAll() Este es el login que venia por defecto
                    //.and().formLogin().loginPage("/acceso/login").permitAll()
                .and().formLogin().successHandler(loginPersonalizado).loginPage("/acceso/login").permitAll()

                //Rutra del login
                .and().logout().permitAll()
        ;
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return  (web -> web.ignoring().antMatchers("/images/**","/js/**","/css/**"));
    }
}

