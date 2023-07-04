package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Build {

	public List<String> byteCode;
	public List<String> Compilar( List<String> programa) {
	
		byteCode = new ArrayList<>();
		
		int i=0;
		for(String linha: programa) {
			String[] token = linha.trim().split(" ");
			linha  = linha.trim();
			
			if (i == 0) { // primeira linha do programar
				prepararClasse(token);
			}
			
			if (token[0].equals("int")) {
				adicionarInt(linha);
			}
			if (token[0].equals("escreva")){
				adicionarEscreva(linha);
			}
			if (token[0].equals("leia")) {
				adicionarLeia(token);
			}
			if (token[0].equals("novalinha")) {
				byteCode.add("System.out.println();");
			}
			
			if(token[0].equals("para")) {
				adicionarPara(token);
			}

			if (token.length >1 &&  token[1].equals("=")) {
				adicionarAtribuicao(linha);
			}
			
			if (token[0].equals("se")) {
				adicionarSe(linha);
			}
			if (token[0].equals("}")) {
				byteCode.add(linha);
			}
			i++;
		}
		
		byteCode.add("}");
		for(String linha: byteCode) {
			System.out.println(linha);
		}
		return byteCode;
	}
	
	public void prepararClasse( String[] token) {
		byteCode.add("import java.util.Scanner;");
		
		byteCode.add("public class " + token[0] + " {");
		byteCode.add("public static Scanner teclado = new Scanner(System.in);");

		byteCode.add("   public static void main( String[] args ){");
		
	}
	
	public void adicionarInt(String linha) {
		byteCode.add(linha + ";");
	}
	
	public void adicionarEscreva(String linha) {
		linha = linha.replace("escreva ","System.out.print(");
		linha += ");";
		byteCode.add(linha);
	}
	
	public void adicionarLeia(String[] token) {
		byteCode.add( token[1] + "= teclado.nextInt();");
	}
	
	public void adicionarPara(String[] token) {
		   //para i = 1 ate 10 {
		
		byteCode.add("for("+
					  token[1] 
					  +"="+
					  token[3]+";"+
					  token[1]+"<"+
					  token[5]+";"+
					  token[1]+"++){");

	}
	public void adicionarAtribuicao(String linha) {
		byteCode.add(linha +";");
	}
	
	public void adicionarSe(String linha) {
		 // se i > 5 {
		// if (i > 5 {
		linha = linha.replace("se", "if (");
		linha = linha.replace("{","){");
		byteCode.add(linha);
	}

}
