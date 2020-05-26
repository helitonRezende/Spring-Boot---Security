package com.heliton.service;

// JAVA //
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

// Model e Repositorio //
import com.heliton.model.Usuario;
import com.heliton.repository.UsuarioRep;

@Service
public class UsuarioSer {

    @Autowired
    private UsuarioRep userRepository;

    //
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }
    //
    public Usuario findId(long id) {
        return userRepository.findById(id).get();
    }
    //
    public Usuario findLogin(String login) {
        return userRepository.findByLogin(login);
    }    
    //
    public void saveUser(Usuario user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        userRepository.save(user);
    }
    //
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}