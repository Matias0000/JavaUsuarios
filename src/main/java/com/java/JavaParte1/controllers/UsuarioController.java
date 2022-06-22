package com.java.JavaParte1.controllers;


import com.java.JavaParte1.Models.Usuario;

import com.java.JavaParte1.dao.UsuarioDao;
import com.java.JavaParte1.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


//    @RequestMapping(value="api/usuario/{id}", method = RequestMethod.GET)
//    public Usuario getUsuario(@PathVariable Long id){
//        Usuario usuario = new Usuario();
//        usuario.setId(id);
//        usuario.setNombre("Prueba");
//        usuario.setApellido("nro2");
//        usuario.setCorreo("asd@asd.asd");
//        usuario.setTelefono(213456);
//        usuario.setPassword("asd");
//        return  usuario;
//    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token)) { return null; }
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //iteration , memory,parallelism
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrarUsuario(usuario);
    }

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader(value="Authorization") String token,@PathVariable  long id){
        if (!validarToken(token)) { return ; }
        usuarioDao.eliminar(id);
    }




    @RequestMapping(value="usuario3")
    public Usuario putUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Prueba");
        usuario.setApellido("nro2");
        usuario.setCorreo("asd@asd.asd");
        usuario.setTelefono(213456);
        usuario.setPassword("asd");
        return  usuario;
    }


//    @RequestMapping(value="usuarios")
//    public List<Usuario> getUsuarios(){
//
//        List <Usuario> usuarios= new ArrayList<>();
//
//        Usuario usuario = new Usuario();
//        usuario.setId(1L);
//        usuario.setNombre("Prueba0");
//        usuario.setApellido("nro2");
//        usuario.setCorreo("asd@asd.asd");
//        usuario.setTelefono(123);
//        usuario.setPassword("password1");
//
//        Usuario usuario1 = new Usuario();
//        usuario1.setId(2L);
//        usuario1.setNombre("Prueba");
//        usuario1.setApellido("nro2");
//        usuario1.setCorreo("asd@asd.asd123");
//        usuario1.setTelefono(456);
//        usuario1.setPassword("password2");
//
//        Usuario usuario2 = new Usuario();
//        usuario2.setId(3L);
//        usuario2.setNombre("Prueba1");
//        usuario2.setApellido("nro22");
//        usuario2.setCorreo("asd@asd.asd2");
//        usuario2.setTelefono(789);
//        usuario2.setPassword("password3");
//
//        usuarios.add(usuario);
//        usuarios.add(usuario1);
//        usuarios.add(usuario2);
//        return  usuarios;
//    }

}
