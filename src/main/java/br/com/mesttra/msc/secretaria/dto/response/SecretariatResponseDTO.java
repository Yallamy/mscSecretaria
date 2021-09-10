package br.com.mesttra.msc.secretaria.dto.response;

import java.io.Serializable;

import br.com.mesttra.msc.secretaria.enums.DestinationTypeEnum;
import br.com.mesttra.msc.secretaria.useful.ConstantsSwagger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados do response do secretariado para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = ConstantsSwagger.SECRETARIAT_RESPONSE_DTO)
public class SecretariatResponseDTO implements Serializable {

	private static final long serialVersionUID = 8692009026050331917L;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_RESPONSE_ID_DTO, position = 1)
	private Long id;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_RESPONSE_FOLDER_DTO, position = 2)
	private DestinationTypeEnum folder;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_RESPONSE_SECRETARY_DTO, position = 3)
	private String secretary;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_RESPONSE_POPULATION_GRADE_DTO, position = 4)
	private Integer populationGrade;
	
	@ApiModelProperty(value = ConstantsSwagger.SECRETARIAT_RESPONSE_UNDER_INVESTIGATION_DTO, position = 5)
	private Boolean underInvestigation;
}
