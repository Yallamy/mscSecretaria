package br.com.mesttra.msc.secretaria.useful;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.mesttra.msc.secretaria.dto.request.SecretariatRequestDTO;
import br.com.mesttra.msc.secretaria.entity.Secretariat;


/**
 * Classe de teste que representa os cenários de testes da classe {@link Useful}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@SuppressWarnings("static-access")
public class UsefulTest {

	@InjectMocks
	private Useful util;
	
	private SecretariatRequestDTO source;
	
	@BeforeEach
	public void setup() {

		this.source = 
				SecretariatRequestDTO
				.builder()
				.build();
	}

	@Test
	public void convertModelMapperTest() {

		Secretariat entidade = util.convert(source, Secretariat.class);

		SecretariatRequestDTO dto = util.convert(entidade, 
				SecretariatRequestDTO.class);

		assertNotNull(entidade);
		assertNotNull(dto);
	}

	@Test
	public void convertModelMapperSourceNullTest() {

		Secretariat entidade = util.convert(null, Secretariat.class);

		assertNull(entidade);
	}

	@Test
	public void convertModelMapperDestinationNullTest() {

		Secretariat entidade = util.convert(source, null);

		assertNull(entidade);
	}
	
	@Test
	public void convertModelMapperToListTest() {
		
		List<SecretariatRequestDTO> sourceList = 
				new LinkedList<SecretariatRequestDTO>();
		
		sourceList.add(this.source);
		
		List<Secretariat> listaEntidade = util.convertToList(sourceList, 
				Secretariat.class);
		
		List<SecretariatRequestDTO> listaDTO = util.convertToList(listaEntidade, 
				SecretariatRequestDTO.class);
		
		
		assertNotNull(listaEntidade);
		assertNotNull(listaDTO);
	}
	
	@Test
	public void convertModelMapperToListSourceNullTest() {
		
		List<Secretariat> listaEntidade = util.convertToList(null, 
				Secretariat.class);
		
		assertNotNull(listaEntidade);
		assertEquals(0, listaEntidade.size());
	}
	
	@Test
	public void convertModelMapperToListDestinationNullTest() {
		
		List<SecretariatRequestDTO> sourceList = 
				new LinkedList<SecretariatRequestDTO>();
		
		sourceList.add(this.source);
		
		List<Secretariat> listaEntidade = util.convertToList(sourceList, 
				null);
		
		assertNotNull(listaEntidade);
		assertEquals(0, listaEntidade.size());
	}
	
	@Test
	public void convertModelMapperToPageTest() {
		
		List<SecretariatRequestDTO> sourceList = 
				new LinkedList<SecretariatRequestDTO>();
		
		sourceList.add(this.source);
		
		Page<SecretariatRequestDTO> pageSource = new PageImpl<SecretariatRequestDTO>(
				sourceList, Pageable.unpaged(), sourceList.size());
		
		Page<Secretariat> pageResponse = util.convertToPage(pageSource, 
				Secretariat.class);
		
		assertNotNull(pageResponse);
		assertEquals(1, pageResponse.getContent().size());
	}
	
	@Test
	public void convertModelMapperToPageSourceNullTest() {
		
		Page<Secretariat> pageResponse = util.convertToPage(null, 
				Secretariat.class);
		
		assertNull(pageResponse);
	}
	
	@Test
	public void convertModelMapperToPageDestinationNullTest() {
		
		List<SecretariatRequestDTO> sourceList = 
				new LinkedList<SecretariatRequestDTO>();
		
		sourceList.add(this.source);
		
		Page<SecretariatRequestDTO> pageSource = new PageImpl<SecretariatRequestDTO>(
				sourceList, Pageable.unpaged(), sourceList.size());
		
		Page<Secretariat> pageResponse = util.convertToPage(pageSource, 
				null);
		
		assertNull(pageResponse);
	}
}
