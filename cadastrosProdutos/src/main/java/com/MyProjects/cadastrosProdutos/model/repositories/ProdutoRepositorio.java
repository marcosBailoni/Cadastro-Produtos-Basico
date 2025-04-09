package com.MyProjects.cadastrosProdutos.model.repositories;

import com.MyProjects.cadastrosProdutos.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

    public Iterable<Produto> findByNomeContainingIgnoreCase(String nomeBusca);

}


// Interface com extends de JPAReposiotory para utilizar os métodos ce crud + findBy personalizado com Spring