package app;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Interpretador {
	
	public List<String> programa; 
	HashMap<String, Integer> tabela;

	
	public void executar(List<String> programa) {
		this.programa = programa;
		tabela = new HashMap<String,Integer>();
		
		System.out.println("Interpretando o programa...");
		int numeroLinha = 0;
		processarLinha(programa.get(0), numeroLinha);
		System.out.println("");
		System.out.println("Fim do interpretador...");
	}
	
	//processa cada linha do exemplo
	public void processarLinha(String linha, int nr) {
		System.out.println("  processando linha:"+nr +": " + linha);
		
		if (nr == 0) { // estou no titulo do programa
					   // o bloco comeca na linha 1
			int fb = encontrarFimBloco(programa,1);
			System.out.println("bloco: 1 ate "+fb);
			fazer( programa, 1, fb);
		}
		
	}
	
	public int encontrarFimBloco(List<String> programa, int inicio) {
		int fb = 0;
		for(int i=inicio; i<programa.size();i++) {
			if (programa.get(i).contains("{")) {
				fb++;
			}
			if (programa.get(i).contains("}")) {
				fb--;
				if (fb < 0) {
					return i;
				}
			}
		}
		return fb;
	}
	//aqui roda todos os processos
	public int fazer(List<String> programa, int numeroLinha, int linhaFinal) {
		int fb = 0;
		for(int i=numeroLinha; i < linhaFinal;i++) {
			String linha = programa.get(i).trim();
			String[] token = linha.trim().split(" ");
			if (linha.contains("}")) {
				return linhaFinal+1;
			}
			if (linha.contains("{")) {
				if (linha.contains("para")) {
					int n = processarPara(linha);
					fb = encontrarFimBloco(programa,i+1 );
					for(int j = 0;j<n;j++) {
						tabela.put(token[1], j);
						fazer(programa,i+1, fb );
					}
					i = fb;
				} else if (linha.startsWith("se")) {
					fb = encontrarFimBloco(programa,i+1 );
					if ( processarSe(linha)) {
						fazer(programa,i+1, fb );
					}
					i = fb;
				} else {
					i = fazer(programa,i+1, fb );
				}
				numeroLinha = fb;
			} else {
			   processarInstrucao(linha);
			}
		}
		return numeroLinha+1;
	}
	
	//processa o If
	public Boolean processarSe(String comando) {
		String[] token = comando.trim().split(" ");
		
		int valor1 = tabela.get(token[1]);
		int valor2 = Integer.parseInt(token[3]);
		String operador = token[2];
		if (operador.equals("=")) {
			return valor1 == valor2;
		}
		if (operador.equals(">")) {
			return valor1 > valor2;
		}		
		if (operador.equals("<")) {
			return valor1 < valor2;
		}			
		if (operador.equals("!")) {
			return valor1 != valor2;
		}		
		return false;
	}
	//processa o For
	public int processarPara(String comando) {
		String[] token = comando.split(" ");
		
		if (token[0].equals("para")) {
			int n = Integer.parseInt(token[5]);
			return n;
		}
		return 1;
	}
	// processa leia e escreva
	public void processarInstrucao(String comando) {
		String[] token = comando.split(" ");
		// processamento do int
		if (token[0].equals("int")) {
			this.tabela.put(token[1],0);
		}
		// linha =  "Digite um numero:"
		if (token[0].equals("escreva")) {
			// se estiver na tabela de simbolos
			if (tabela.containsKey(token[1])) {
				System.out.print(tabela.get(token[1]));
			} else {
				String texto = comando.replace("escreva ", "").replace("\"","");
				System.out.print(texto);
			}
		}
		
		if (token[0].equals("leia")) {
			Scanner teclado = new Scanner(System.in);
			int temp;
			temp = teclado.nextInt();
			tabela.put(token[1],temp);
		}
		
		// processar atribuicao
		if (tabela.containsKey(token[0]))
		if (token[1].equals("=")) {
			processarAtribuicao(token);
		}
		if (token[0].equals("novalinha")) {
			System.out.println(" ");
		}
	}
	//processa os tipos de calculo
	public void processarAtribuicao(String[] token) {
		int t1;
		int t2;
		int r = 0;
		String operador;
		t1 = tabela.get(token[2]);
		t2 = tabela.get(token[4]);
		operador = token[3];
		if (operador.equals("*")) {
			r = t1 * t2;
		}
		if (operador.equals("/")) {
			r = t1 / t2;
		}
		if (operador.equals("-")) {
			r = t1 - t2;
		}
		if (operador.equals("+")) {
			r = t1 + t2;
		}
		tabela.put(token[0],r);
	}
}
