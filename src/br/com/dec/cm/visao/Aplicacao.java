package br.com.dec.cm.visao;

import br.com.dec.cm.modelo.Tabuleiro;

public class Aplicacao {
	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
		
	}
}
