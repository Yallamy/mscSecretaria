package br.com.mesttra.msc.secretaria.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.mesttra.msc.secretaria.useful.ConstantsSwagger;
import br.com.mesttra.msc.secretaria.useful.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de request do secretariado para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = ConstantsSwagger.SECRETARIAT_REQUEST_DTO)
public class SecretariatRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 893843078285377857L;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_REQUEST_FOLDER_DTO, position = 1)
	@NotNull(message = Message.FOLDER_REQUIRED)
	private String folder;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_REQUEST_SECRETARY_DTO, position = 2)
	@NotNull(message = Message.SECRETARY_REQUIRED)
	private String secretary;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_REQUEST_POPULATION_GRADE_DTO, position = 3)
	@NotNull(message = Message.POPULATION_GRADE_REQUIRED)
	private Integer populationGrade;
}
