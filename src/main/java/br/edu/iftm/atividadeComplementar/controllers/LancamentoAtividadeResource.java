package br.edu.iftm.atividadeComplementar.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;
import br.edu.iftm.atividadeComplementar.repositories.LancamentoAtividadeRepository;

@RestController
@RequestMapping(value = "/lancamento")
public class LancamentoAtividadeResource {
	
	@Autowired
	private LancamentoAtividadeRepository repo;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Optional<LancamentoAtividade> Lanc_atividadeOptional = repo.findById(id);
		if (Lanc_atividadeOptional.isPresent()) {
			return ResponseEntity.ok(Lanc_atividadeOptional);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		try {
			repo.deleteById(id);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody LancamentoAtividade Lancamentoatividade){
		LancamentoAtividade atv = repo.save(Lancamentoatividade);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(atv.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
		
	}

}
