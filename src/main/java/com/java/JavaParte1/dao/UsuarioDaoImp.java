package com.java.JavaParte1.dao;

import com.java.JavaParte1.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query="FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }
    @Override
    public void eliminar(long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioVerificadoCredenciales(Usuario usuario) {
        String query="FROM Usuario WHERE email = :email ";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email",usuario.getCorreo())
                .getResultList();
        if(lista.isEmpty()){
            return null;
        }

        String passwordHashed=lista.get(0).getPassword();

    //    falta seguridad ya que es susceptible a un null exeception mas o menos correccion arriba
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if( argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }
        return null;
//        if(lista.isEmpty()){
//            return false;
//        }else{
//            return true;
//        }
//        return !lista.isEmpty();
    }

}
