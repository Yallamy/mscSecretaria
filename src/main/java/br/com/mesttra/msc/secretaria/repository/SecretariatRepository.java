package br.com.mesttra.msc.secretaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mesttra.msc.secretaria.entity.Secretariat;

/**
 * Repositório da entidade {@link Secretariat}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Repository
public interface SecretariatRepository extends JpaRepository<Secretariat, Long> {

}
