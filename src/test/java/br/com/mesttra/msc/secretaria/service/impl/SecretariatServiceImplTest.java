package br.com.mesttra.msc.secretaria.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.mesttra.msc.secretaria.EntityGenericUtil;
import br.com.mesttra.msc.secretaria.entity.Secretariat;
import br.com.mesttra.msc.secretaria.enums.DestinationTypeEnum;
import br.com.mesttra.msc.secretaria.exceptions.ApplicationException;
import br.com.mesttra.msc.secretaria.repository.SecretariatRepository;

/**
 * Classe de teste que representa os cenários de testes da classe {@link SecretariatServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 11 de set de 2021
 */
@SpringBootTest
public class SecretariatServiceImplTest {

	@InjectMocks
	private SecretariatServiceImpl service;

	@Mock
	private SecretariatRepository repository;

	@Mock
	private Pageable pageable;

	@Mock
	private Page<Secretariat> page;

	private Secretariat request;

	private Secretariat response;
	
	private List<Secretariat> listaResponse;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {

		this.request = Secretariat
				.builder()
				.folder(DestinationTypeEnum.EDUCATION)
				.secretary(EntityGenericUtil.getString())
				.populationGrade(EntityGenericUtil.getInteger())
				.underInvestigation(null)
				.build();

		this.response = Secretariat
				.builder()
				.id(EntityGenericUtil.getLong())
				.folder(DestinationTypeEnum.EDUCATION)
				.secretary(EntityGenericUtil.getString())
				.populationGrade(EntityGenericUtil.getInteger())
				.underInvestigation(false)
				.build();
		
		listaResponse = new LinkedList<Secretariat>();
		listaResponse.add(this.response);

		Mockito.when(this.repository.save(
				Mockito.any(Secretariat.class))).thenReturn(this.response);
		Mockito.when(this.repository.findByFolder(
				Mockito.any(DestinationTypeEnum.class))).thenReturn(null);
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(Optional.of(this.response));
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
		Mockito.when(this.page.getContent()).thenReturn(this.listaResponse);
	}

	//create
	@Test
	public void createTest() {

		Mockito.when(this.page.getContent()).thenReturn(new LinkedList<Secretariat>());
		
		Secretariat response = this.service.create(request);

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.response, response);
	}
	
	@Test
	public void createDuplicateTest() {
		
		Mockito.when(this.repository.findByFolder(
				Mockito.any(DestinationTypeEnum.class))).thenReturn(this.response);
		
		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}

	@Test()
	public void createSecretariatNullTest() {

		assertThrows(ApplicationException.class, () -> {
			this.service.create(null);
		});
	}

	@Test()
	public void createFolderNullTest() {

		this.request.setFolder(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}

	@Test()
	public void createSecretaryNullTest() {

		this.request.setSecretary(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}

	@Test()
	public void createPopulationGradeNullTest() {

		this.request.setPopulationGrade(null);

		assertThrows(ApplicationException.class, () -> {
			this.service.create(request);
		});
	}
	
	//underInvestigation
	@Test
	public void underInvestigationTest() {

		Secretariat response = this.service.underInvestigation(
				Secretariat.builder().id(EntityGenericUtil.getLong())
				.underInvestigation(true).build());

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.response, response);
	}
	
	@Test
	public void underInvestigationNullTest() {

		assertThrows(ApplicationException.class, () -> {
			this.service.underInvestigation(null);
		});
	}
	
	@Test
	public void underInvestigationNotFoundTest() {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		assertThrows(ApplicationException.class, () -> {
			this.service.underInvestigation(Secretariat.builder()
					.id(EntityGenericUtil.getLong()).underInvestigation(true).build());
		});
	}

	//retrieve
	@Test
	public void retrieveTest() {

		Secretariat response = this.service.retrieve(EntityGenericUtil.getLong());

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.response, response);
	}

	@Test()
	public void retrieveNotFoundTest() {

		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		assertThrows(ApplicationException.class, () -> {
			this.service.retrieve(EntityGenericUtil.getLong());
		});
	}

	@Test()
	public void retrieveComNullTest() {

		Long id = null;

		assertThrows(ApplicationException.class, () -> {
			this.service.retrieve(id);
		});
	}

	//list
	@Test
	public void listTest() {

		Secretariat request = Secretariat.builder()
				.build();

		Page<Secretariat> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listComSecretariatNullTest() {

		Page<Secretariat> response = this.service.list(null, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test()
	public void listComPageableNullTest() {

		assertThrows(ApplicationException.class, () -> {
			this.service.list(this.request, null);
		});
	}

	@Test
	public void listPorFolderTest() {

		Secretariat request = Secretariat.builder()
				.folder(DestinationTypeEnum.HEALTH)
				.build();

		Page<Secretariat> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listPorSecretaryTest() {

		Secretariat request = Secretariat.builder()
				.secretary(EntityGenericUtil.getString())
				.build();

		Page<Secretariat> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}

	@Test
	public void listPorPopulationGradeTest() {

		Secretariat request = Secretariat.builder()
				.populationGrade(EntityGenericUtil.getInteger())
				.build();

		Page<Secretariat> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorUnderInvestigationTest() {

		Secretariat request = Secretariat.builder()
				.underInvestigation(EntityGenericUtil.getBoolean())
				.build();

		Page<Secretariat> response = this.service.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
}
