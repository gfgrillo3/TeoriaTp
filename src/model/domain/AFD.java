package model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.logica.StringGenerator;

public class AFD {

	// PROBABLEMENTE LIST STRING?
	char[] alfabetoInput;
	List<Estado> estadoInicial;
	List<List<Estado>> estados;
	List<List<Estado>> estadosFinales;
	List<Transicion> transiciones;

	// PROBABLEMENTE SE RECIBA LA LISTA DE ESTADIS DIRECTAMENTE YA QUE SE ESTA
	// CONVIRTIENDO DE UN AFND
	// POR ESTO, NO SE RECIBIR�A CANT ESTADOS
	public AFD(char[] alfabetoInput, List<List<Estado>> estados, List<List<Estado>> estadosFinales,
			List<Transicion> transiciones) {

		this.alfabetoInput = alfabetoInput;
		this.estadoInicial = new ArrayList<>();
		this.estadoInicial.add(new Estado(1));
		this.estados = estados;
		this.estadosFinales = estadosFinales;
		this.transiciones = transiciones;

	}

	// METODO PARA VALIDAR UN STRING. EL STRING DEBE CONTENER CARACTERES DEL INPUT
	// UNICAMENTE
	public boolean procesarString(String string) {

		// comienzo en el estadoInicial y me muevo
		List<Estado> estadoActual = estadoInicial;

		// me muevo con los input del string
		for (char caracterInput : string.toCharArray()) {
			estadoActual = this.mover(estadoActual, caracterInput);
		}

		// VALIDO SI ES ESTADO FINAL. VEO LOS ESTADOS FINALES DEL AFD
		// SI EL ESTADO ACTUAL ES UN ESTADO FINAL, ACEPTO EL STRING
		for (List<Estado> estadoFinal : this.estadosFinales) {
			if (this.tienenLosMismosEstados(estadoFinal, estadoActual))
				return true;
		}

		return false;
	}

	// METODO PARA AVANZAR UNA POSICION DESDE UN ESTADO CON UN CHAR DE INPUT
	private List<Estado> mover(List<Estado> estadoActual, char caracterInput) {

		for (Transicion transicion : this.transiciones) {
			// Si es el mismo estado inicial, y tienen el mismo char de input, es la
			// transicion que necesito
			if (tienenLosMismosEstados(transicion.getEstadoInicial(), estadoActual)
					&& transicion.getInput() == caracterInput)
				return transicion.getEstadoFinal();
		}

		return null;
	}

	// VEO SI DOS CONJUNTOS DE ESTADOS TIENEN LOS MISMOS ESTADOS
	private boolean tienenLosMismosEstados(List<Estado> listaEstados, List<Estado> otraListaEstados) {

		// SI NO TIENEN EL MISMO TAMA�O, SON DISTINTAS LISTAS
		if (listaEstados.size() != otraListaEstados.size())
			return false;

		// RECORRO LOS ESTADOS Y VEO SI SON LOS MISMOS
		for (int i = 0; i < listaEstados.size(); i++) {
			if (listaEstados.get(i).getValor() != otraListaEstados.get(i).getValor())
				return false;
		}
		// SI ERAN TODOS IGUALES Y TIENEN LA MISMA LONGITUD, SON LA MISMA LISTA
		return true;
	}

	@Override
	public String toString() {

		String alfabeto = "Alfabeto = ";
		String estados = "Estados = ";
		String estadoInicial = "Estado Inicial = ";
		String estadosFinales = "Estados Finales = ";
		String transiciones = "Transiciones = \n";


		alfabeto += Arrays.toString(this.alfabetoInput).replace("[", "").replace("]", "");
		
		
		estados += this.estados
		           .stream()
		           .map(listEstado -> StringGenerator.toStringListEstados(listEstado))
		           .collect(Collectors.joining(" , "));

		
		estadoInicial += StringGenerator.toStringListEstados(this.estadoInicial);
		
		
		estadosFinales += this.estadosFinales
		           .stream()
		           .map(listEstado -> StringGenerator.toStringListEstados(listEstado))
		           .collect(Collectors.joining(" , "));

		
		for(Transicion transicion : this.transiciones)
			transiciones += transicion.toString()+"\n";
		
		

		return  alfabeto + "\n" + 
				estados + "\n" + 
				estadoInicial + "\n" +
				estadosFinales + "\n"+
				transiciones;
	}

	public char[] getAlfabetoInput() {
		return alfabetoInput;
	}

	public List<Estado> getEstadoInicial() {
		return estadoInicial;
	}

	public List<List<Estado>> getEstados() {
		return estados;
	}

	public List<List<Estado>> getEstadosFinales() {
		return estadosFinales;
	}

	public List<Transicion> getTransiciones() {
		return transiciones;
	}
	
	
	

	
}
