package br.edu.iftm.atividadeComplementar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.atividadeComplementar.repositories.LancamentoAtividadeRepository;

@Service
public class LancamentoAtividadeService {
	
	@Autowired
	private LancamentoAtividadeRepository repository;
	
	public List<LancamentoAtividadeRepository> buscar(Integer id) {
		return repository.findByCodigoContaining(id);
	}

}
