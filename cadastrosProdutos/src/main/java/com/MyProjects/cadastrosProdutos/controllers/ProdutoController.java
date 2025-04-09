package com.MyProjects.cadastrosProdutos.controllers;

import com.MyProjects.cadastrosProdutos.model.entities.Produto;
import com.MyProjects.cadastrosProdutos.model.repositories.ProdutoRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepositorio pr;

//   Como salvar um novo e alterar usam o mesmo padrão (Se quiser alterar, informar o id a mais apenas)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Produto salvarProduto(@Valid Produto p){
        pr.save(p);
        return p;        
    }

//    Listar todos os produtos
    @GetMapping
    public List<Produto> obterProdutos(){
        return pr.findAll();
    }

//    Buscar produto por nome
    @GetMapping("/nome/{nomeBusca}")
    public Iterable<Produto> obterProdutosPorNome(@PathVariable String nomeBusca){
        return pr.findByNomeContainingIgnoreCase(nomeBusca);
    }

//    Listar produtos com paginação, melhor para não ter uma consulta muito extensa
    @GetMapping("/pagina/{numeroPagina}/{quantidade}")
    public Page<Produto> buscaPaginada(@PathVariable int numeroPagina,
                                       @PathVariable @Min(1) @Max(5) int quantidade){

        Pageable page = PageRequest.of(numeroPagina, quantidade);
        return pr.findAll(page);
    }

//    Busca por id
    @GetMapping("/{id}")
    public Optional<Produto> obterProdutoPorId(@PathVariable int id){
        return pr.findById(id);
    }

//    Deletar por id
    @DeleteMapping("/{id}")
    public void deletarUsuarioPorId(@PathVariable Integer id){
        pr.deleteById(id);
    }

}
