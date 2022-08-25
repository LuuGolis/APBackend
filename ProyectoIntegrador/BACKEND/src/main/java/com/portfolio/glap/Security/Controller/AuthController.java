/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.glap.Security.Controller;

import com.portfolio.glap.Security.Dto.JwtDto;
import com.portfolio.glap.Security.Entity.Rol;
import com.portfolio.glap.Security.Entity.Usuario;
import com.portfolio.glap.Security.Enums.RolNombre;
import com.portfolio.glap.Security.Service.RolService;
import com.portfolio.glap.Security.Service.UsuarioService;
import com.portfolio.glap.Security.jwt.JwtProvider;
//ojo con hashset capaz no va
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lugol
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioService usuarioService;
            
    @Autowired
    RolService rolService;
            
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Email inválido / Error en los campos"), HttpStatus.BAD_REQUEST);

        if(usuarioService.existsByNombreUusario(nombreUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Nombre de usuario ya existente, por favor elija otro"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByEmail(nombreUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Email ya existente, por favor ingrese un email válido"), HttpStatus.BAD_REQUEST);
    
    Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
    Set<Rol> roles = new HashSet<>();
    roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    
    if(nuevoUsuario.getRoles().contains("admin"))
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    usuario.setRoles(roles);
    usuarioService.save(usuario);
    
    return new ResponseEntity(new Mensaje("Usuario guardado correctamente"), HttpStatus.CREATED);
   
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities()); 
        
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
        
    }
    
    
    
}
