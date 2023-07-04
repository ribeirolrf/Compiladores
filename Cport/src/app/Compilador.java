package app;
import java.io.IOException;
import java.util.List;

public class Compilador {
	//main
	public static void main(String[] args) throws IOException {
		System.out.println("Compilador CPORT 1.0...");
		String currentPath = new java.io.File(".").getCanonicalPath();
		System.out.println("Diretorio de Trabalho:" + currentPath);

		Arquivos arquivos = new Arquivos();
		AnalisadorLexico al = new AnalisadorLexico();
		AnalisadorSintatico as = new AnalisadorSintatico();
		AnalisadorSemantico ase = new AnalisadorSemantico();
		Interpretador it = new Interpretador();
		Build build = new Build();
		al.analisar(arquivos.alfabeto,arquivos.programa);
		as.analisar(arquivos.programa, arquivos.expressoes);
		ase.analisar(arquivos.programa, arquivos.dicionario);
		//it.executar(arquivos.programa);
		List<String> byteCode = build.Compilar(arquivos.programa);
		arquivos.gravarArquivo(byteCode, "Exemplo");

	}

}
