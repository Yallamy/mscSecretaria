package br.com.mesttra.msc.secretaria.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.mesttra.msc.secretaria.entity.Secretariat;
import br.com.mesttra.msc.secretaria.exceptions.ApplicationException;

/**
 * Interface que define os métodos do serviço para manter um {@link Secretariat}.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
public interface SecretariatService {

	/**
	 * Método que cria um secretariado
	 * @param secretariat
	 * @return Secretariat
	 * @throws ApplicationException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 10 de set de 2021
	 */
	public Secretariat create(Secretariat secretariat) throws ApplicationException;
	
	/**
	 * Método que coloca o secretariado sob investigação
	 * @param secretariat
	 * @return Secretariat
	 * @throws ApplicationException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 10 de set de 2021
	 */
	public Secretariat underInvestigation(Secretariat secretariat) throws ApplicationException;
	
	/**
	 * Método que recupera um secretariado por id
	 * @param id - id do secretariado
	 * @return Secretariat
	 * @throws ApplicationException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 10 de set de 2021
	 */
	public Secretariat retrieve(Long id) throws ApplicationException;
	
	/**
	 * Método que lista os secretariados de acordo com os filtros
	 * @param secretariat
	 * @param pageable
	 * @return Page<Secretariat>
	 * @throws ApplicationException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 10 de set de 2021
	 */
	public Page<Secretariat> list(Secretariat secretariat, Pageable pageable) throws ApplicationException;
}
