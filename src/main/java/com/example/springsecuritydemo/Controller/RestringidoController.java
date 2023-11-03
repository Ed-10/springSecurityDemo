package com.example.springsecuritydemo.Controller;

import com.example.springsecuritydemo.seguridad.PermisosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/restringido")
public class RestringidoController {
    @Autowired
    private PermisosServices permisosServices;
    @GetMapping("")
    public String home(RedirectAttributes flash)
    {
        if (this.permisosServices.comprobacionDeRoles("ROLE_ADMIN"))
        {
            return "restringido/home";
        }else {
            flash.addFlashAttribute("clase","warning");
            flash.addFlashAttribute("mensaje","No tienes acceso ah ese contenido");
            return "redirect:/";
        }
    }
    @GetMapping("/restringido-2")
    public String protegido_2()
    {
        return "restringido/restringido_2";
    }
}
