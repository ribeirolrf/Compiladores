package app;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivos {
	//declaração de arquivos
	public String[] alfabeto;
	public List<String> dicionario;
	public List<String> expressoes;
	public List<String> programa;
	//atribuição dos arquivos
	public Arquivos() throws IOException {
		List<String> alfabetoLinhas;
		String dirbase = "src\\arquivos";
		alfabetoLinhas = lerArquivo(dirbase + "\\alfabeto.txt");
		dicionario= lerArquivo(dirbase +"\\dicionario.txt");
		expressoes = lerArquivo(dirbase + "\\expressoes.txt");
		programa= lerArquivo(dirbase + "\\exemplo.txt");
		
		String temp="";
		for(String linha: alfabetoLinhas) {
			temp += linha;
		}
		alfabeto = temp.split("");
	}
	
	//leitura dos arquivos
	
	@SuppressWarnings("resource")
	public List<String> lerArquivo(String arquivo) throws IOException{
		System.out.println("Arquivo lido:" + arquivo);
		List<String> conteudo = new ArrayList<>();
		FileReader leitor= new FileReader(arquivo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha;
		while ((linha = buffer.readLine()) != null) {
				conteudo.add(linha);
		}
		return conteudo;
	}
	
	//imprime oq tem nos arquivos
	public void imprimirArquivo(List<String> arquivo) {
		for(String linha: arquivo) {
			System.out.println(linha);
		}
	}
	
	//salva os arquivos
	public void gravarArquivo(List<String> byteCode,String nome) throws IOException {
		String arquivo = nome +".java";
		String dirbase = "src\\arquivos\\";
		FileWriter fw = new FileWriter(dirbase+arquivo);
		BufferedWriter gravador = new BufferedWriter(fw);
		for(String linha: byteCode) {
			gravador.write(linha);
			gravador.newLine();
		}
		gravador.close();
		fw.close();
	}

}
