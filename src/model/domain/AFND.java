package model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AFND {

	// PROBABLEMENTE LIST STRING?
	char[] alfabetoInput;
	Estado estadoInicial = new Estado(1);
	List<Estado> estados;
	List<Estado> estadosFinales;
	List<Transicion> transiciones;

	public AFND(char[] alfabetoInput, int cantEstados, int[] estadosFinales, List<Transicion> transiciones) {

		this.alfabetoInput = alfabetoInput;
		this.inicializarEstados(cantEstados);
		this.inicializarEstadosFinales(estadosFinales);

		this.inicializarTransiciones(transiciones);

	}

	// INICIALIZAR ESTADOS DEL AFD, COMIENZA EN 1 Y SON CORRELATIVOS POR DEFINICION
	private void inicializarEstados(int cantEstados) {

		this.estados = new ArrayList<Estado>();

		int i = 1;

		while (i <= cantEstados) {
			this.estados.add(new Estado(i));
			i++;
		}
	}

	private void inicializarEstadosFinales(int[] estadosFinales) {

		this.estadosFinales = new ArrayList<Estado>();

		for (int i : estadosFinales) {
			this.estadosFinales.add(new Estado(i));
		}
	}

	private void inicializarTransiciones(List<Transicion> transiciones) {

		this.transiciones = transiciones;		
		
		//ORDENAR DE MENOR A MAYOR POR VALOR DE ESTADO, por char y por estado final
		Collections.sort(this.transiciones, new Comparator<Transicion>(){
			
			@Override
			public int compare(Transicion transicion, Transicion otraTransicion) {

				if(transicion.estadoInicial.getValor() > otraTransicion.getEstadoInicial().getValor())
					return 1;
				else if(transicion.estadoInicial.getValor() < otraTransicion.getEstadoInicial().getValor())
					return -1;
				else if(transicion.getInput() > otraTransicion.getInput())
					return 1;
				else if(transicion.getInput() < otraTransicion.getInput() )
					return -1;
				else if(transicion.estadoFinal.getValor() > otraTransicion.getEstadoFinal().getValor())
					return 1;
				else if(transicion.estadoFinal.getValor() < otraTransicion.getEstadoFinal().getValor())
					return -1;
				return 0;
			}
			 });
		
		
	}

	public List<Transicion> getTransiciones() {
		return this.transiciones;
	}
	
	
}