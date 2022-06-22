package com.java.JavaParte1.dao;

import com.java.JavaParte1.Models.Usuario;

import java.util.List;

public interface UsuarioDao  {
    List<Usuario>getUsuarios();

    void eliminar(long id);

    void registrarUsuario(Usuario usuario);

    Usuario obtenerUsuarioVerificadoCredenciales(Usuario usuario);
}
