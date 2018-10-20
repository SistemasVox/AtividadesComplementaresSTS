package br.edu.iftm.atividadeComplementar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.atividadeComplementar.domains.Atividade;
import br.edu.iftm.atividadeComplementar.repositories.AtividadeRepository;


@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repository;
	
	public List<Atividade> buscar(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	public void salvarAtualizar(Atividade atividade) {
		repository.save(atividade);
	}
	
	public void excluir(Integer id) {
		repository.deleteById(id);
	}
	
	
	public Optional<Atividade> buscarID(Integer id) {
		return repository.findById(id);
	}
}
