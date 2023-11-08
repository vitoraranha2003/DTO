package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.ProdutoDTO;
import com.example.demo.entidades.Produto;
import com.example.demo.servicos.ProdutoServico;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServico servico;

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody ProdutoDTO produto) {
		ProdutoDTO prod = servico.insert(produto);
		return prod != null ? new ResponseEntity<>("Produto criado com sucesso", HttpStatus.CREATED)
				: new ResponseEntity<>("Erro ao criar produto", HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<ProdutoDTO> list = servico.findAll();
		return !list.isEmpty() ? new ResponseEntity<>(list, HttpStatus.OK)
				: new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/produto-dto-id/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
		ProdutoDTO produto = servico.findById(id);
		if (produto != null) {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/produto-update")
	public ResponseEntity<String> update(@RequestBody ProdutoDTO produto) {
		ProdutoDTO prod = servico.update(produto);
		return prod != null ? new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK)
				: new ResponseEntity<>("Erro ao atualizar produto", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/produto-delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		boolean deleted = servico.delete(id);
		if (deleted) {
			return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Erro ao deletar produto", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/produto-dto-id-preco-jpql/{id}/{preco}")
	public ResponseEntity<ProdutoDTO> findByIdPrecoJpql(@PathVariable Integer id, @PathVariable Double preco) {
		ProdutoDTO produto = servico.findByIdPrecoJpql(id, preco);
		if (produto != null) {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/produto-dto-id-preco-sql/{id}/{preco}")
	public ResponseEntity<ProdutoDTO> findByIdPrecoSql(@PathVariable Integer id, @PathVariable Double preco) {
		ProdutoDTO produto = servico.findByIdPrecoSql(id, preco);
		if (produto != null) {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
