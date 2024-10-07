package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Usuario;
import com.softskills.softskills.repository.UsuarioRepository;

@Service
public class UsuarioService implements Iservice<Usuario>{

//Falta implementar Cache

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Usuario> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Usuario get(Long id) {
        return repo.findById(id).orElse(null); // ...
    }

    @Override
    public Usuario save(Usuario objeto) {
        // if (objeto.getSenha() == null || objeto.getSenha().isBlank()) {
        //     Long id = objeto.getId();
        //     Usuario usuario = get(id);
        //     if (usuario != null) {
        //         objeto.setSenha(usuario.getSenha());
        //     }
        // importar o springFramework security para implementar a encriptação da senha
        // } else {
        //     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //     String senhaCriptografada = passwordEncoder.encode(objeto.getSenha());
        //     objeto.setSenha(senhaCriptografada);
        //    } 
           return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // public Usuario getByNomeUsuario(String nomeUsuario) {
    //     return repo.findBynome_usuario(nomeUsuario);
    // }

    public Usuario getByNomeUsuario(String nomeUsuario) {
        return repo.findByNomeUsuario(nomeUsuario);
    }

}
