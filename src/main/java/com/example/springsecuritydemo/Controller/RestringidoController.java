package com.example.springsecuritydemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restringido")
public class RestringidoController {
    @GetMapping("")
    public String home()
    {
        return "restringido/home";
    }
    @GetMapping("/restringido-2")
    public String protegido_2()
    {
        return "restringido/restringido_2";
    }
}
