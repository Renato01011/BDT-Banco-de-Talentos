package com.fractal.bancodetalentos.security;

import com.fractal.bancodetalentos.model.dto.TmUsuarioDTO;
import com.fractal.bancodetalentos.model.dto.TxRolDTO;
import com.fractal.bancodetalentos.service.MasterUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MasterUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TmUsuarioDTO usuarioDTO = usuarioService.findByUsername(username);
                /*.orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND")
        );*/
        return new User(usuarioDTO.getUsUsuario(), usuarioDTO.getPwPassword(), mapRoles(usuarioDTO.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(List<TxRolDTO> roles) {
        return roles.stream()
                .filter(Objects::nonNull)
                .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());
    }
}
