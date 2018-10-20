package br.edu.iftm.atividadeComplementar.controllers;

import java.net.URI;
import java.util.List;
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

import br.edu.iftm.atividadeComplementar.domains.Atividade;
import br.edu.iftm.atividadeComplementar.repositories.AtividadeRepository;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

	@Autowired
	private AtividadeRepository repo;

	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Optional<Atividade> atividadeOptional = repo.findById(id);
		if (atividadeOptional.isPresent()) {
			return ResponseEntity.ok(atividadeOptional);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping(value = "like/{nome}")
	public ResponseEntity<?> findByNome(@PathVariable String nome) {
		List<Atividade> atividades = repo.findByNomeContainingIgnoreCase(nome);
		if (atividades.size() > 0) {
			return ResponseEntity.ok(atividades);
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
	public ResponseEntity<?> salvar(@Valid @RequestBody Atividade atividade){
		Atividade atv = repo.save(atividade);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(atv.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	

}
