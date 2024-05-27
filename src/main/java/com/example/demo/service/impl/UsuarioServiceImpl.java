package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class UsuarioServiceImpl implements UsuarioService  {

	private final UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository= usuarioRepository;
	}
	
	
	@Override
	@Transactional
	public int crear(Usuario usuario) {
		try {
			
			
			UsuarioEntity usuarioEntity= new UsuarioEntity();
			
			usuarioEntity.setNombre(usuario.getNombre());
			usuarioEntity.setApellido(usuario.getApellido());
			usuarioEntity.setEmail(usuario.getEmail());
			usuarioEntity.setRol(usuario.getRol());
			usuarioEntity.setUsername(usuario.getUsername());
			
			UsuarioEntity usuarioGuardado=usuarioRepository.save(usuarioEntity);
			
			return usuarioGuardado.getId();
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex; 
		}
	}

	@Override
	public int editar(Usuario usuario) {
		try {
			
			Optional<UsuarioEntity> usuarioEntityGuardadoOptional = usuarioRepository.findById(usuario.getId());
		
			if(usuarioEntityGuardadoOptional.isEmpty()) return -1;
			
			UsuarioEntity usuarioEntityGuardado =usuarioEntityGuardadoOptional.get();
		
			usuarioEntityGuardado.setNombre(usuario.getNombre());
			usuarioEntityGuardado.setApellido(usuario.getApellido());
			usuarioEntityGuardado.setEmail(usuario.getEmail());
			usuarioEntityGuardado.setRol(usuario.getRol());
			
			UsuarioEntity usuarioGuardado=usuarioRepository.save(usuarioEntityGuardado);
			
			return usuarioGuardado.getId();
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex; 
		}
	}
	
	@Override
	public List<Usuario> listado() {
		try {
			
			
			List<Usuario> listado= new ArrayList<>();
			Iterable<UsuarioEntity> listadoEntities=usuarioRepository.findAll();
			
			for(UsuarioEntity usuarioEntity : listadoEntities) {
				Usuario usuario= new Usuario();
				usuario.setId(usuarioEntity.getId());
				usuario.setNombre(usuarioEntity.getNombre());
				usuario.setApellido(usuarioEntity.getApellido());
				usuario.setEmail(usuarioEntity.getEmail());
				usuario.setUsername(usuarioEntity.getUsername());
				usuario.setRol(usuarioEntity.getRol());
				listado.add(usuario);
			}
			
			return listado;
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex; 
		}
	}


	@Override
	public Usuario getById(int id) {
		UsuarioEntity usuarioEntity=  usuarioRepository.findById(id).orElse(null);
		if(usuarioEntity== null) return null;
		
		Usuario usuario= new Usuario();
		usuario.setId(usuarioEntity.getId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setApellido(usuarioEntity.getApellido());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setUsername(usuarioEntity.getUsername());
		usuario.setRol(usuarioEntity.getRol());
		return usuario;
	}
	@Override
	public Usuario getByUsername(String username) {
		UsuarioEntity usuarioEntity=  usuarioRepository.searchByUsername(username);
		if(usuarioEntity== null) return null;
		
		Usuario usuario= new Usuario();
		usuario.setId(usuarioEntity.getId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setApellido(usuarioEntity.getApellido());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setUsername(usuarioEntity.getUsername());
		usuario.setRol(usuarioEntity.getRol());
		return usuario;
	}

}
