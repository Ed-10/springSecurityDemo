package com.example.springsecuritydemo.Controller;

import com.example.springsecuritydemo.Modelos.AutorizarModel;
import com.example.springsecuritydemo.Modelos.UsuarioModel;
import com.example.springsecuritydemo.Service.AutorizarService;
import com.example.springsecuritydemo.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/acceso")
public class AccesoController {
    //Inyectando servicios para el REGISTRO de USUARIOS
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private AutorizarService autorizarService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //Encriptacion de contraseñas, de nuestra clase Seguridad dentro de las configuraciones
    ///////////////////////////////////////////////////
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
    @GetMapping("/registro")
    public String registro(Model model)
    {
        model.addAttribute("usuario", new UsuarioModel());
        return "acceso/registro";
    }
    //@Valid, Es necesario agregar una dependencia en nuestro pom.xml
    @PostMapping("/registro")
    public String registro_post(@Valid UsuarioModel usuario,
                                BindingResult result,
                                RedirectAttributes flash,
                                Model model)
    {
        if (result.hasErrors())
        {
            Map<String,String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(),
                        "El campo ".concat(err.getField()).concat("").concat(err.getDefaultMessage()));
            });
            model.addAttribute("errores",errores);
            model.addAttribute("usuario",usuario);
            return "acceso/registro";
        }
        //Creando al USUARIO
        UsuarioModel guardar=this.usuariosService.guardar(new UsuarioModel(
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                this.passwordEncoder.encode(usuario.getPassword()),
                1,
                null));
        //Creando un ROL
        this.autorizarService.guardar(new AutorizarModel("ROLE_USER",guardar));
        flash.addFlashAttribute("clase","success");
        flash.addFlashAttribute("mensaje","Te has registrado exitosamente");
        //Redireccionamiento de usuario
        return "redirect:/acceso/registro";
    }
}
