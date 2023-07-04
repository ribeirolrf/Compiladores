package app;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorSemantico {
	
	public List<String> tabelaSimbolos;
	public List<String> dicionario;
	
	//construtor da tebelaSimbolos
	public AnalisadorSemantico() {
		tabelaSimbolos = new ArrayList<>();
	}
	
	//recebe os parametros o codigo exemplo e o dicionario que tem os operadores os tipos de variaveis e os comandos
	public void analisar(List<String> exemplo, List<String> dicionario) {
	
		this.dicionario = dicionario;
		int erros = 0;
		int nl = 1;
		for(String linha: exemplo) {
			erros += validarLinha( linha.trim(), nl );
			nl++;
		}
		System.out.println("Fim da Analise Sem�ntica: erros = " + erros);
	}
	
	public int validarLinha(String linha, int nl) {
		String[] tokens = linha.split(" ");
		int numeroLinha = 0;
		for(String token: tokens) {
			//se o token for igual a escreva e começa com \ 
			if (!(tokens[0].equals("escreva") && tokens[1].startsWith("\""))) {
				//se o numero da linha for maior que 0 e o token for uma variavel
				if (numeroLinha > 0 && ehVariavel(token) ) {
					//se o token for algum tipo de variavel
					
					//testa tirando o int
					if (tokens[0].equals("int") || 
						tokens[0].equals("char") ||
						tokens[0].equals("bool") ||
						tokens[0].equals("float"))
					{
						//adiciona o token na tabela de simbolos
						tabelaSimbolos.add(token);
					}
					// se não printa o erro
					else {
						if (tabelaSimbolos.contains(token)) {
							return 0;
						} else {
							System.out.println("Linha " + nl + ": Variavel n�o declarada: " + token);
							return 1;
						}
					}
				}
			}
			numeroLinha++;
		}
		
		return 0;
	}
	
	public boolean ehVariavel(String token) {
		//só retorna true se o token n for vazio, se n tiver o token no dicionario e se não começar com número
		if (token.isEmpty()) {
			return false;
		}
		if (dicionario.contains(token)) {
			return false;
		} else {
			if (token.matches("^[0-9]{1,10}$") ) {
				return false;
			}
		}
		return true;
	}
}
