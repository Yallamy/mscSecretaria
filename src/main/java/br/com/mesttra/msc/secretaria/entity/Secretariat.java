package br.com.mesttra.msc.secretaria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import br.com.mesttra.msc.secretaria.enums.DestinationTypeEnum;
import br.com.mesttra.msc.secretaria.useful.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe que representa a entidade Secretariat.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Table(name = "Secretariat")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
public class Secretariat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_secretariat")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "folder", nullable = false, unique = true)
	@NotNull(message = Message.FOLDER_REQUIRED)
	@Enumerated(EnumType.STRING)
	private DestinationTypeEnum folder;
	
	@Column(name = "secretary", nullable = false)
	@NotNull(message = Message.SECRETARY_REQUIRED)
	private String secretary;
	
	@Column(name = "populationGrade", nullable = false)
	@NotNull(message = Message.POPULATION_GRADE_REQUIRED)
	private Integer populationGrade;
	
	@Column(name = "underInvestigation", nullable = false)
	@NotNull(message = Message.UNDER_INVESTIGATION_REQUIRED)
	private Boolean underInvestigation;
}
