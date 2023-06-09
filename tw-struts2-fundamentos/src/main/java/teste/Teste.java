package teste;

import java.util.Locale;

public class Teste {

	public static void main(String[] args) {
		
		
		String teste = "SELECIõNE";
		if(teste != "" && teste != null && "SELECIõNE".equalsIgnoreCase(teste)) {
		System.out.println(teste.toUpperCase(Locale.ENGLISH));
		} else {
			System.err.println("POR FAVOR PREENCHA O CAMPO!");
		}
	}

}
