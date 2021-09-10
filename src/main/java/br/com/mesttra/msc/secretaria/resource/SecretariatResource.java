package br.com.mesttra.msc.secretaria.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mesttra.msc.secretaria.dto.request.SecretariatRequestDTO;
import br.com.mesttra.msc.secretaria.dto.response.SecretariatResponseDTO;
import br.com.mesttra.msc.secretaria.entity.Secretariat;
import br.com.mesttra.msc.secretaria.enums.DestinationTypeEnum;
import br.com.mesttra.msc.secretaria.exceptions.ApplicationException;
import br.com.mesttra.msc.secretaria.service.SecretariatService;
import br.com.mesttra.msc.secretaria.useful.ConstantsPath;
import br.com.mesttra.msc.secretaria.useful.ConstantsSwagger;
import br.com.mesttra.msc.secretaria.useful.Useful;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Classe que disponibiliza os serviços para manter o secretariat.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value= ConstantsPath.PATH_SECRETARIAT, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantsPath.PATH_SECRETARIAT, produces = MediaType.APPLICATION_JSON_VALUE, tags = { ConstantsPath.TAG_SECRETARIAT })
public class SecretariatResource {

	private final SecretariatService service;

	/**
	 * Método REST que cria um secretariado.
	 * @param request - SecretariatRequestDTO
	 * @return ResponseEntity<?> - secretariado criado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws ApplicationException 
	 * @since 10 de set de 2021
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = ConstantsSwagger.CREATE_SECRETARIAT, 
		notes = ConstantsSwagger.CREATE_SECRETARIAT_NOTES, response = SecretariatResponseDTO.class)
	public @ResponseBody ResponseEntity<?> create(
			@Valid @RequestBody SecretariatRequestDTO request) throws ApplicationException {

		Secretariat secretariat = Useful.convert(request, Secretariat.class);
		secretariat.setFolder(DestinationTypeEnum.getEnum(request.getFolder()));

		secretariat = this.service.create(secretariat);
		SecretariatResponseDTO response = Useful.convert(secretariat, SecretariatResponseDTO.class);

		return new ResponseEntity<SecretariatResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * Método REST que recupera um secretariado.
	 * @param id - id do secretariado
	 * @return ResponseEntity<?> - secretariado recuperado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws ApplicationException 
	 * @since 10 de set de 2021
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = ConstantsSwagger.RETRIEVE_SECRETARIAT, 
		notes = ConstantsSwagger.RETRIEVE_SECRETARIAT_NOTES, response = SecretariatResponseDTO.class)
	public @ResponseBody ResponseEntity<?> retrieve(
			@PathVariable("id") Long id) throws ApplicationException {

		Secretariat secretariat = this.service.retrieve(id);
		SecretariatResponseDTO response = Useful.convert(secretariat, SecretariatResponseDTO.class);

		return new ResponseEntity<SecretariatResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * Método REST que lista os secretariados de acordo com os filtros informados.
	 * @param dtBudget - filtro dtBudget do secretariado
	 * @param source - filtro source do secretariado
	 * @param destination - filtro destination do secretariado
	 * @return ResponseEntity<?> - lista de secretariados ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws ApplicationException 
	 * @since 10 de set de 2021
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = ConstantsSwagger.LIST_SECRETARIAT, 
		notes = ConstantsSwagger.LIST_SECRETARIAT_NOTES, response = SecretariatResponseDTO.class)
	public @ResponseBody ResponseEntity<Page<?>> list(
			@PageableDefault(value = 30, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam("folder") Optional<String> folder,
			@RequestParam("secretary") Optional<String> secretary,
			@RequestParam("populationGrade") Optional<Integer> populationGrade,
			@RequestParam("underInvestigation") Optional<Boolean> underInvestigation) throws ApplicationException {

		Secretariat secretariat = 
				Secretariat
				.builder()
				.folder(DestinationTypeEnum.getEnum(folder.orElse(null)))
				.secretary(secretary.orElse(null))
				.populationGrade(populationGrade.orElse(null))
				.underInvestigation(underInvestigation.orElse(null))
				.build();

		Page<Secretariat> page = this.service.list(secretariat, pageable);
		Page<SecretariatResponseDTO> response = Useful.convertToPage(page, SecretariatResponseDTO.class);

		return ResponseEntity.ok(response);
	}
}
