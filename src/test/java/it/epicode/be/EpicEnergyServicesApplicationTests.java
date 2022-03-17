package it.epicode.be;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.TipoCliente;
import it.epicode.be.repository.ClienteRepository;

@SpringBootTest
@AutoConfigureMockMvc
class EpicEnergyServicesApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	Cliente cliente;
	
	@Autowired
	ClienteRepository clienteRepo;
	
//	@Test
//	void contextLoads() {
//	}

	@Test
	@WithAnonymousUser
	public void loginNoBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithAnonymousUser
	public void getAllClienti() throws Exception {
		this.mockMvc.perform(get("/api/clienti")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin")
	public void listaClientiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/clienti")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin")
	public void addNewCliente() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(cliente);

		MvcResult result = mockMvc.perform(post("/api/cliente/crea").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		JSONObject json_obj = new JSONObject(result.getResponse().getContentAsString());
		assertTrue(json_obj.has("nome"));

		assertTrue(json_obj.has("cognome"));

		// Ho reso stringa l'oggetto json e ho valutato che contiene la stringa A234.
		assertTrue(json_obj.getString("nome").contains("Gianni"));

	}
	
	@BeforeEach
	public void initContext() {
		cliente = new Cliente();
		cliente.setRagioneSociale("Trapani PA");
		cliente.setPartitaIva("12345678901");
		cliente.setEmail("gianni@dd.it");
		cliente.setFatturatoAnnuale(120000.00);
		cliente.setPec("giannipec@pec.it");
		cliente.setTelefono("3317345321");
		cliente.setEmailContatto("giannigianni@e.it");
		cliente.setNome("Gianni");
		cliente.setCognome("Percoco");
		cliente.setTelefonoContatto(cliente.getTelefono());
		cliente.setTipoCliente(TipoCliente.PA);
		
		
	}
}
