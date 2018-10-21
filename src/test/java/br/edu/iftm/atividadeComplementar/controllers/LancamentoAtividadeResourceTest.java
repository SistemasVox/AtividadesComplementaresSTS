package br.edu.iftm.atividadeComplementar.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LancamentoAtividadeResourceTest {
	
	@Autowired
	public WebApplicationContext contex;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.contex).build();
	}
	
	@Test
	public void test01RequisicaoSucesso() throws Exception{
		String url = "/lancamento/1";
		this.mvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(jsonPath("codigo", equalTo(1)));
	}
	
	@Test
	public void test02RequisicaoFalha() throws Exception{
		String url = "/lancamento/100";
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void test03DeleteSucesso() throws Exception{
		String url = "/lancamento/1";
		this.mvc.perform(delete(url))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("1")));
	}
	public void test04DeleteFalha() throws Exception{
		String url = "/lancamento/100";
		this.mvc.perform(delete(url))
		.andExpect(status().isNotFound());
	}
	
	public void test05SalvarSucesso() throws Exception{
		String url = "/lancamento";
		this.mvc.perform(post(url)
		.content("{\"codigo\": 2, \"quantidadeHoras\": 100, \"dataInicio\": \"2018-10-20T03:00:00.000+0000\", \"dataFim\": \"2018-11-20T02:00:00.000+0000\", \"aluno\": { \"ra\": 123456789, \"nome\": \"Marcelo Vieira\", \"atividades\": []}, \"atividade\": { \"codigo\": 1, \"nome\": \"Monitoria\", \"percentualCargaHoraria\": 50, \"maximoAtividadesSemestre\": 1, \"percentualPorAtividade\": 100 }, \"semestreAtividade\": \"2018-2\", \"horasAproveitadas\": 100}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(header().string("Location", is("http://192.168.0.103:8080/lancamento/2")))
		.andDo(MockMvcResultHandlers.print());
	}
}
