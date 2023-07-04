package app;
import java.util.List;

public class AnalisadorSintatico {
	//aqui ele vai analisar linha por linha do programa 
	public void analisar( List<String> programa, 
			List<String> expressoes) {
		int erros = 0;
		for(String linha: programa) {
			if (!linhaAprovada(linha.trim(), expressoes)) {
				System.out.println("Erro na linha:" + linha);
				erros ++;
			}
		}
		System.out.println("Fim da Analise Sint�tica: erros = " + erros);
	}
	
	//se a linha estiver de acordo com os requisitos da expressão regular do arquivo "expressoes" ele vai retornar true, se n retorna false e aumenta numero de erro
	public Boolean linhaAprovada( String linha, List<String> expressoes) {

		for(String exp: expressoes) {
			if (linha.matches(exp)) {
				return true;
			}
		}
		return false;	
	}
}
