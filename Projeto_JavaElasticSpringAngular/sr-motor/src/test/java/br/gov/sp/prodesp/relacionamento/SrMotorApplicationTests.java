package br.gov.sp.prodesp.relacionamento;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SrMotorApplicationTests {

	@Test
	public static void deveCriarClassServico(){
		Servico servico = new Servico();
	}

	@Test
	public static void deveCriarClassEvento() {
		Evento evento = new Evento();

		//Assertions.

	}
}

/*
* @Getter
@Setter
EVENTO
	private String tipo;
    private String id;
    private String idAtendimento;
    private Long data;
    private String idCidadao;

@Getter
@Setter
SERVICO
	private Integer id;
    private String login;
    private String desc;
    private Integer idAgenda;
* */