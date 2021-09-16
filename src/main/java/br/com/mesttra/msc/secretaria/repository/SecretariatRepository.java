package br.com.mesttra.msc.secretaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mesttra.msc.secretaria.entity.Secretariat;
import br.com.mesttra.msc.secretaria.enums.DestinationTypeEnum;

/**
 * Repositório da entidade {@link Secretariat}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Repository
public interface SecretariatRepository extends JpaRepository<Secretariat, Long> {
	
	/**
	 * Método que recupera uma secretaria por pasta
	 * @param folder - pasta
	 * @return Secretariat
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 16 de set de 2021
	 */
	public Secretariat findByFolder(DestinationTypeEnum folder);

}
