package br.com.mesttra.msc.secretaria.service.impl;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mesttra.msc.secretaria.entity.Secretariat;
import br.com.mesttra.msc.secretaria.exceptions.ApplicationException;
import br.com.mesttra.msc.secretaria.exceptions.ServiceEnumValidation;
import br.com.mesttra.msc.secretaria.repository.SecretariatRepository;
import br.com.mesttra.msc.secretaria.service.SecretariatService;
import br.com.mesttra.msc.secretaria.validation.ValidationCustom;
import lombok.RequiredArgsConstructor;

/**
 * Classe que implementa os métodos do serviço para manter o secretariado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SecretariatServiceImpl implements SecretariatService {
	
	private final SecretariatRepository repository;

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.secretaria.service.SecretariatService#create(br.com.mesttra.msc.secretaria.entity.Secretariat)
	 */
	@Override
	public Secretariat create(Secretariat secretariat) throws ApplicationException {
		
		ValidationCustom.validateConsistency(secretariat);
		
		if(Objects.isNull(secretariat.getUnderInvestigation())) {
			secretariat.setUnderInvestigation(false);
		}
		
		ValidationCustom.validateDataViolation(secretariat, secretariat.getClass());
		
		//verificar se o folder já existe
		PageRequest pageRequest = PageRequest.of(
                1,
                1,
                Sort.Direction.ASC,
                "id");
		
		Page<Secretariat> lista = list(Secretariat.builder().folder(secretariat.getFolder()).build(), pageRequest);
		
		if(!lista.getContent().isEmpty()) {
			throw new ApplicationException(ServiceEnumValidation.SECRETARIAT_FOLDER);
		}
		
		return repository.save(secretariat);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.secretaria.service.SecretariatService#underInvestigation(br.com.mesttra.msc.secretaria.entity.Secretariat)
	 */
	@Override
	public Secretariat underInvestigation(Secretariat secretariat) throws ApplicationException {
		
		ValidationCustom.validateConsistency(secretariat);
		ValidationCustom.validateConsistency(secretariat.getId(), secretariat.getUnderInvestigation());
		
		Secretariat secretariatSave = retrieve(secretariat.getId());
		secretariatSave.setUnderInvestigation(secretariat.getUnderInvestigation());

		return repository.save(secretariatSave);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.secretaria.service.SecretariatService#retrieve(java.lang.Long)
	 */
	@Override
	public Secretariat retrieve(Long id) throws ApplicationException {
		
		ValidationCustom.validateConsistency(id);

		try {

			return repository.findById(id).get();

		} catch(NoSuchElementException ex) {
			throw new ApplicationException(ServiceEnumValidation.SECRETARIAT_NOT_FOUND);
		}
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see br.com.mesttra.msc.secretaria.service.SecretariatService#list(br.com.mesttra.msc.secretaria.entity.Secretariat, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Secretariat> list(Secretariat secretariat, Pageable pageable) throws ApplicationException {
		
		ValidationCustom.validateConsistency(pageable);

		if(Objects.isNull(secretariat)) {
			secretariat = Secretariat.builder().build();
		}

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<Secretariat> example = Example.of(secretariat, matcher);

		return repository.findAll(example, pageable); 
	}

}
