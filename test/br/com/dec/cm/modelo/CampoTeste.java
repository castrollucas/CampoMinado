package br.com.dec.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.dec.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	@Test
	void testeVizinhoDiatancia1() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDiatancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
		
	}
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		
	}
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
		
	}
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () ->{
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		Campo vizinho1 = new Campo(2,2);
		Campo vizinho2 = new Campo(2,3);
		
		campo.adicionarVizinho(vizinho1);
		vizinho1.adicionarVizinho(vizinho2);
		campo.abrir();
		
		assertTrue(vizinho1.isAberto() && vizinho2.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && !campo11.isAberto());
	}
	
	@Test
	void testeIsFechado() {
		assertTrue(campo.isFechado());
	}
	
	@Test
	void testeGetLinha() {
		assertTrue(campo.getLinha() == 3);
	}
	
	@Test
	void testeGetColuna() {
		assertTrue(campo.getColuna() == 3);
	}
	
	@Test
	void testeObjetivoAlcancado1() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado2() {
		campo.minar();
		campo.alternarMarcacao();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeMinasNaVizinhanca() {
		Campo campo22 = new Campo(2 ,2);
		Campo campo23 = new Campo(2 ,3);
		Campo campo24 = new Campo(2 ,4);
		
		campo22.minar();
		campo23.minar();
		campo24.minar();
		
		campo.adicionarVizinho(campo22);
		campo.adicionarVizinho(campo23);
		campo.adicionarVizinho(campo24);
		
		assertTrue(campo.minasNaVizinhanca() == 3);
		
	}
	

	@Test
	void testeReiniciar() {
		campo.reiniciar();
		assertFalse(campo.isAberto() && campo.isMinado() && campo.isMarcado());
	}
	
	@Test
	void testeToString() {
		campo.alternarMarcacao();
		assertEquals(campo.toString(), "x");
		
		campo.alternarMarcacao();
		campo.abrir();
		assertEquals(campo.toString(), " ");
		
		campo.minar();
		campo.abrir();
		assertEquals(campo.toString(), "*");
		
		campo.reiniciar();
		Campo campo22 = new Campo(2 ,2);
		Campo campo23 = new Campo(2 ,3);
		Campo campo24 = new Campo(2 ,4);
		
		campo22.minar();
		campo23.minar();
		campo24.minar();
		
		campo.adicionarVizinho(campo22);
		campo.adicionarVizinho(campo23);
		campo.adicionarVizinho(campo24);
		campo.abrir();
		
		assertEquals(campo.toString(), "3");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
