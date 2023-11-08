package com.example.demo.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.PedidoDTO;
import com.example.demo.entidades.Pedido;
import com.example.demo.repositorio.PedidoRepositorioJpa;

@Service
public class PedidoServico {
	
	
	@Autowired
	private PedidoRepositorioJpa repository;
	
	
	public Pedido insert(Pedido pedido) {
		return repository.save(pedido);
		
	}
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll (){
		List<Pedido> list = repository.findAll();
		return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
	}

}
