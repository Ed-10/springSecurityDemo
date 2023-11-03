package com.example.springsecuritydemo.seguridad;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginPersonalizado extends SimpleUrlAuthenticationSuccessHandler {//Nos permite agregar un filtro al momento de hacer la peticion al Login
    //sobre autenticación correcta
    //Metodo para intervencion de nuestro metodo POST
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                       HttpServletResponse response,
                                       FilterChain chain,
                                       Authentication authentication) throws ServletException, IOException
    {
        response.sendRedirect("/");//Redireccionaminto del usuario
        super.onAuthenticationSuccess(request,response,chain,authentication);
    }
}
