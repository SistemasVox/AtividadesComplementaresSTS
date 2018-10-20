package br.edu.iftm.atividadeComplementar.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.iftm.atividadeComplementar.domains.Aluno;
import br.edu.iftm.atividadeComplementar.domains.Atividade;



@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtividadeRepositoryTest  {
	
	@Autowired
	private AtividadeRepository repo;

	@Test
	public void test01AtividadeMonitoria() {
		List<Atividade> atividades = repo.findByNomeContainingIgnoreCase("moni");
		assertThat(atividades.size()).isEqualTo(1);
	}

	@Test
	public void test02DisciplinasExtracurriculares() {
		List<Atividade> atividades = repo.findByNomeContainingIgnoreCase("extra");
		assertThat(atividades.size()).isEqualTo(1);
	}
	
	@Test
	public void test03Participação() {
		List<Atividade> atividades = repo.findByNomeContainingIgnoreCase("giado");
		assertThat(atividades.size()).isEqualTo(1);
	}
	
	@Test
	public void test04Participação() {
		List<Atividade> atividades = repo.findByNomeContainingIgnoreCase("Minicursos");
		assertThat(atividades.size()).isEqualTo(1);
	}

}
