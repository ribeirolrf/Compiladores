package app;
import java.util.List;

//se a letra não tiver no alfabeto vai dar erro
//alterar  alfabeto pra exemplo

public class AnalisadorLexico {
	//Recebe o alfabeto e o exemplo q vai testar
	public void analisar(String[] alfabeto, List<String> programa) {
		int erros = 0;
		for(String linha: programa) {
			String[] letras = linha.trim().split("");
			for (String letra: letras) {
			//	System.out.print("letra: " + letra + " => ");
				if (!verificarSeALetraEstaNoAlfabeto(letra, alfabeto)) {
					System.out.println("A letra " + letra + " n�o est� no alfabeto..");
					erros++;
				} else {
					//System.out.println("ok");
				}
			}
		}
		System.out.println("Fim da analise lexica => erros = " + erros);
	}
	public boolean verificarSeALetraEstaNoAlfabeto( String letra, String[] alfabeto) {
		for(String caracter: alfabeto) {
			if (letra.equals(caracter)) {
				return true;
			}
		}
		return false;
	}
	
	
}
