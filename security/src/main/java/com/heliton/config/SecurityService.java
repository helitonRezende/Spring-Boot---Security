package com.heliton.config;

// Security //
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

// Model e Servico //
import com.heliton.model.Usuario;
import com.heliton.service.UsuarioSer;

@Component
public class SecurityService implements UserDetailsService {
	
	@Autowired
    private UsuarioSer usuarios;

	//
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarios.findLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Não autorizado!");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getNome()));
		
		return buildUserForAuthentication(usuario, authorities);
	}

	//
	private UserDetails buildUserForAuthentication(Usuario usuario, List<GrantedAuthority> authorities) {
		return new User(usuario.getLogin(), usuario.getSenha(), true, true, true, true, authorities);
    }	
}