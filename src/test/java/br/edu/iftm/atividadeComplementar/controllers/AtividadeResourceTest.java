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

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtividadeResourceTest {
	
	@Autowired
	public WebApplicationContext contex;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.contex).build();
	}
	
	@Test
	public void testa01RequisicaoSucesso() throws Exception{
		String url = "/atividades/1";
		this.mvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(jsonPath("nome", equalTo("Monitoria")));
	}
	
	@Test
	public void test02RequisicaoFalha() throws Exception{
		String url = "/atividades/100";
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void test03RequisicaoNomeSucesso() throws Exception{
		String url = "/atividades/like/moni";
		this.mvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Monitoria")));
	}
	
	@Test
	public void test04NomeFalha() throws Exception{
		String url = "/atividades/like/Marcelo";
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testa05DeleteSucesso() throws Exception{
		String url = "/atividades/1";
		this.mvc.perform(delete(url))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("1")));
	}

	@Test
	public void test06DeleteFalha() throws Exception{
		String url = "/atividades/100";
		this.mvc.perform(delete(url))
		.andExpect(status().isNotFound());
	}
	public void testa07SalvarSucesso() throws Exception{
		String url = "/atividades";
		this.mvc.perform(post(url)
		.content("\"nome\": \"Veneração ao Marcelo\", \"percentualCargaHoraria\": 50, \"maximoAtividadesSemestre\": 1, \"percentualPorAtividade\": 100")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(header().string("Location", is("http://192.168.0.103:8080/atividades/5")))
		.andDo(MockMvcResultHandlers.print());
	}
	
	public void testa08SalvarFalha() throws Exception{
		String url = "/atividades";
		this.mvc.perform(post(url)
		.content("\"n0m3\": \"Veneração ao Marcelo\", \"percentualCargaHoraria\": 50, \"maximoAtividadesSemestre\": 1, \"percentualPorAtividade\": 100")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}
	
}
