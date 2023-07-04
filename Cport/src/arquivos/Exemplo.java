import java.util.Scanner;
public class Taboada {
public static Scanner teclado = new Scanner(System.in);
   public static void main( String[] args ){
int i;
int r;
int n;
System.out.print("Digite um numero:");
n= teclado.nextInt();
System.out.print(n);
System.out.println();
for(i=1;i<10;i++){
r = i * n;
System.out.print(n);
System.out.print(" x ");
System.out.print(i);
System.out.print(" = ");
System.out.print(r);
System.out.print("   ");
if ( i > 5 ){
System.out.print("maior que 5");
}
System.out.println();
}
i = 0;
r = r + i;
i = i + 1;
}
}
}
