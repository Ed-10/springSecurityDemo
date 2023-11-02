package com.example.springsecuritydemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/acceso")
public class AccesoController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error",required = false)String error,
                        @RequestParam(value = "logout",required = false)String logout,
                        RedirectAttributes flash,
                        Principal principal) //Principal Nos permite obetener algun tipo de informacion si es que el usuario se encuntra logeado
    {
        if (principal!=null)
        {
            flash.addFlashAttribute("clase","success");
            flash.addFlashAttribute("mensaje","Ya ah iniciado sesion anteriormente");
            return "redirect:/";
        }
        if (error!=null)
        {
            flash.addFlashAttribute("clase","danger");
            flash.addFlashAttribute("mensaje","Los datos ingresados no son correctos, intentelo de nuevo");
            return "redirect:/acceso/login";
        }
        if (logout!=null)
        {
            flash.addFlashAttribute("clase","success");
            flash.addFlashAttribute("mensaje","Se ha cerrado la secion de forma exitosa");
            return "redirect:/acceso/login";
        }
        return "acceso/login";
    }
}
