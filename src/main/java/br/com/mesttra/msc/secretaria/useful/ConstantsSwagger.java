package br.com.mesttra.msc.secretaria.useful;

/**
 * Classe que posssui as constantes utilizadas no Swagger.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 10 de set de 2021
 */
public class ConstantsSwagger {

	//Swagger
    
    //serviços
    public static final String CREATE_SECRETARIAT = "Criar um secretariado";
    
    public static final String CREATE_SECRETARIAT_NOTES = "Cria um secretariado.";
    
    public static final String RETRIEVE_SECRETARIAT = "Recuperar um secretariado";
    
    public static final String RETRIEVE_SECRETARIAT_NOTES = "Recupera um secretariado por id.";
    
    public static final String LIST_SECRETARIAT = "Listar os secretariados";
    
    public static final String LIST_SECRETARIAT_NOTES = "Listar os secretariados de acordo com os filtros.";
    
    
    
    //DTOs - REQUESTS
    public static final String SECRETARIAT_REQUEST_DTO = "Armazena os dados do request da alocação do secretariado.";
    
    public static final String SECRETARIAT_REQUEST_FOLDER_DTO = "Armazena os dados da pasta do secretariado.";
    
    public static final String SECRETARIAT_REQUEST_SECRETARY_DTO = "Armazena os dados do nome do secretário do secretariado.";
    
    public static final String SECRETARIAT_REQUEST_POPULATION_GRADE_DTO = "Armazena os dados da nota da população para o secretariado.";
    
    
    
    
    //DTOs - RESPONSES
    public static final String SECRETARIAT_RESPONSE_DTO = "Armazena os dados do request da alocação do secretariado.";
    
    public static final String SECRETARIAT_RESPONSE_ID_DTO = "Armazena o id do secretariado.";

    public static final String SECRETARIAT_RESPONSE_FOLDER_DTO = "Armazena os dados da pasta do secretariado.";

    public static final String SECRETARIAT_RESPONSE_SECRETARY_DTO = "Armazena os dados do nome do secretário do secretariado.";

    public static final String SECRETARIAT_RESPONSE_POPULATION_GRADE_DTO = "Armazena os dados da nota da população para o secretariado.";
    
    public static final String SECRETARIAT_RESPONSE_UNDER_INVESTIGATION_DTO = "Armazena o dado se o secretariado está sob investigação.";
    
    
}
