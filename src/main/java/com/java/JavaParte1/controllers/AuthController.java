package com.java.JavaParte1.controllers;

import com.java.JavaParte1.Models.Usuario;
import com.java.JavaParte1.dao.UsuarioDao;
import com.java.JavaParte1.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioJwt=usuarioDao.obtenerUsuarioVerificadoCredenciales(usuario);
        if(usuarioJwt !=null){

            String token =jwtUtil.create(String.valueOf(usuarioJwt.getId()),usuarioJwt.getCorreo());

            return token;
        };
        return "FAIL";
    }

}
