package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;

public interface UsuarioService {

	Usuario getByUsername(String username);
	int crear(Usuario usuario);
	int editar(Usuario usuario);
	List<Usuario> listado();
	Usuario getById(int id);
}
