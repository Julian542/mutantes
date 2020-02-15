package com.example.demo.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.util.*;
import com.example.demo.dtos.PDto;
import com.example.demo.modelo.Adn;
import com.example.demo.modelo.Ente;
import com.example.demo.modelo.Humanos;
import com.example.demo.modelo.Mutantes;
import com.example.demo.repositories.PRepository;

@Service
public class PService {
	private PRepository repository;
	

	public PService(PRepository repository) {
		super();
		this.repository = repository;
	}
	//FindAll
	@Transactional
	public List<PDto> findAll() throws Exception{
		List<Ente> entidades = repository.findAll();
		List<PDto> dtos = new ArrayList();
		try {
			for(Ente i: entidades) {
				PDto unDto = new PDto();
				unDto.setId(i.getId());
				unDto.setNombre(i.getNombre());
				unDto.setDna1(i.getAdn().getDna1());
				unDto.setDna2(i.getAdn().getDna2());
				unDto.setDna3(i.getAdn().getDna3());
				unDto.setDna4(i.getAdn().getDna4());
				unDto.setDna5(i.getAdn().getDna5());
				unDto.setDna6(i.getAdn().getDna6());
				unDto.setClasificacion(i.getClasificacion());
				dtos.add(unDto);
			}
			return dtos;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	//FindById
	@Transactional
	public PDto findById(int id)throws Exception{
		Optional<Ente> entidadOpcional = repository.findById(id);
		PDto unDto = new PDto();
		try {
			Ente entidad = entidadOpcional.get();
			
			unDto.setId(entidad.getId());
			unDto.setNombre(entidad.getNombre());
			unDto.setDna1(entidad.getAdn().getDna1());
			unDto.setDna2(entidad.getAdn().getDna2());
			unDto.setDna3(entidad.getAdn().getDna3());
			unDto.setDna4(entidad.getAdn().getDna4());
			unDto.setDna5(entidad.getAdn().getDna5());
			unDto.setDna6(entidad.getAdn().getDna6());
			unDto.setClasificacion(entidad.getClasificacion());
			return unDto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	//Save
	@Transactional
	public boolean save(PDto dto) throws Exception{
		Ente entidad = new Ente();
		entidad.setNombre(dto.getNombre());
		Adn adn = new Adn();
		adn.setDna1(dto.getDna1());
		adn.setDna2(dto.getDna2());
		adn.setDna3(dto.getDna3());
		adn.setDna4(dto.getDna4());
		adn.setDna5(dto.getDna5());
		adn.setDna6(dto.getDna6());
		entidad.setAdn(adn);
		entidad.setMutante(new Mutantes());
		entidad.setHumano(new Humanos());
		try {	
			String [] dna = {dto.getDna1(),dto.getDna2(),dto.getDna3(),dto.getDna4(),dto.getDna5(),dto.getDna6()};
			boolean estadoGenetico = isMutant(dna);
			if(estadoGenetico == true) {
				
				entidad.getMutante().setEsmutante(true);
				entidad.getHumano().setEshumano(false);
				dto.setClasificacion("Mutante");
				entidad.setClasificacion(dto.getClasificacion());
				entidad = repository.save(entidad);
				dto.setId(entidad.getId());
				return estadoGenetico;
			}else {
				
				entidad.getMutante().setEsmutante(false);
				entidad.getHumano().setEshumano(true);
				dto.setClasificacion("Humano");
				entidad.setClasificacion(dto.getClasificacion());
				entidad = repository.save(entidad);
				dto.setId(entidad.getId());
				return estadoGenetico;
			}
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	@Transactional
	public String stats() throws Exception{
		List<Ente> entes = repository.findAll();
		List<Ente> mutantes = new ArrayList();
		List<Ente> humanos = new ArrayList();
		try {
			for(Ente i: entes) {
				if(i.getMutante().getEsmutante() == true) {
					mutantes.add(i);
				}else {
					humanos.add(i);
				}
			}	
			double contadorHumanos = humanos.size();
			double contadorMutantes = mutantes.size();
			double ratio = contadorMutantes / contadorHumanos;
			String resultado = "La cantidad de mutantes es " + contadorMutantes + " ,la cantidad de humanos es " + contadorHumanos + " ,el ratio es: " + ratio;
			return resultado;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	
	
	//Update
	@Transactional
	public PDto update(int id, PDto dto) throws Exception{
		Optional<Ente> entidadOpcional = repository.findById(id);
		boolean estadoGenetico;
		try {
			Ente entidad = entidadOpcional.get();
			entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());
			entidad.getAdn().setDna1(dto.getDna1());
			entidad.getAdn().setDna2(dto.getDna2());
			entidad.getAdn().setDna3(dto.getDna3());
			entidad.getAdn().setDna4(dto.getDna4());
			entidad.getAdn().setDna5(dto.getDna5());
			entidad.getAdn().setDna6(dto.getDna6());
			String [] dna = {dto.getDna1(),dto.getDna2(),dto.getDna3(),dto.getDna4(),dto.getDna5(),dto.getDna6()};
			estadoGenetico = isMutant(dna);
			
			if((entidad.getHumano().getEshumano() == false && estadoGenetico == true) || (entidad.getHumano().getEshumano() == true && estadoGenetico == false)) {
			repository.save(entidad);
			dto.setId(entidad.getId());
			return dto;
			}else {
				repository.deleteById(id);
				return null;
			}
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	//Delete
	@Transactional
	public boolean delete(int id) throws Exception{
		try {
			if(repository.existsById(id)) {
				repository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	
	//Metodos Mutante
	public boolean isMutant(String [] dna) throws Exception{
        int n = dna.length; //representa longitud del String ingresado  
        int contadorX = 0; //contador que cuenta secuencias repetidas
        if(Validador (n,dna) == true){ // validamos que las longitudes sean correctas para trabajar bien
            char [][] matriz = cargaMatriz(n, dna); //convertimos nuestro arreglo en una matriz char
           contadorX += buscadorHorizontal(n, matriz);
           if(contadorX < 2) {
           contadorX += buscadorVertical(n, matriz);
           if(contadorX < 2) {
           contadorX += busquedaOblicuaCentral( n, matriz);
           if(contadorX < 2) {
           contadorX += busquedaOblicuaArriba(n, matriz);
           if(contadorX < 2) {
           contadorX += busquedaOblicuaAbajo( n, matriz);
           				}
           			}
           		}
           }
           if (contadorX >= 2) {
            return true;
        } else {
            return false;
        }
        }else{
        	throw new Exception("Ocurrio un error, la cantidad de columnas superan a las filas, o se ingresó un caracter invalido en la secuencia de ADN");
             //tiramos una excepcion para avisar con un mensaje, que algo no ha ido bien
        }
    }
	
	//VALIDADOR
	 public boolean Validador(int n, String [] dna){ //Validamos la cadena que ingresó el usuario
		 boolean status = true;
	        for (int i = 0; i < dna.length; i++) {
	            dna[i] = dna[i].toUpperCase();
	            char[] partido = dna[i].toCharArray();
	            for (int j = 0; j < partido.length; j++) {
	                if((partido.length!=dna.length) || (partido[j] != 'A' && partido[j] != 'T' && partido[j] != 'G' && partido[j] != 'C' )){          
	                    status = false;              
	                }         
	            }           
	        }
	        return status;
	    }
	
	 //CARGA MATRIZ
	 public char[][] cargaMatriz(int n, String [] dna){ //si esta todo bien, convertimos nuestra cadena en una matriz de Letras
	        char [][] matriz = new char[n][n];
	        for (int i = 0; i < n; i++) {
	                char[] partido = dna[i].toCharArray(); //cada celda del arreglo, sera un arreglo de letras individuales
	                for (int j = 0; j < n; j++) { 
	                    matriz[i][j] = partido[j]; //cargamos fila por fila, utilizando como dato, cada columna de los arreglos
	                }
	            }
	        return matriz;
	    }
	 
	 //BUSCA HORIZONTALES
	 public int buscadorHorizontal( int n, char [][] matriz){ //buscamos secuencias en Horizontal  
		 int contadorX = 0;   
		 int contador1 = 0;
	        for (int i = 0; i < n; i++) {
	                for (int j = 1; j < n; j++) {
	                    if (matriz[i][j - 1] != matriz[i][j]) {
	                        contador1 = 0;
	                    } else {
	                        contador1++;
	                        if (contador1 == 3) {
	                            contadorX++;
	                            contador1 = 0;//Esto sirve por si tenemos una doble secuencia, para que se sumen 2, por ejemplo AAAAAAAA
	                        }
	                    }                   
	                }
	                contador1 = 0; 
	            }
	        return contadorX;
	    }
	 
	 //BUSCA VERTICALES
	 public int buscadorVertical(int n, char [][] matriz){ //buscamos secuencias en Vertical
		 int contadorX = 0;    
		 int contador2 = 0;
	        for (int i = 0; i < n; i++) {
	                for (int j = 1; j < n; j++) {
	                    if (matriz[j - 1][i] != matriz[j][i]) {
	                        contador2 = 0;
	                    } else {
	                        contador2++;
	                        if (contador2 == 3) {
	                            contadorX++;
	                            contador2 = 0;
	                        }
	                    }
	                }              
	                contador2 = 0;
	            }
	        return contadorX;
	    }
	 
	 //BUSCA DIAGONAL CENTRAL
	 public int busquedaOblicuaCentral(int n, char [][] matriz){ //Busca secuencas en la diagonal mas grande, la central
		 int contadorX = 0;    
		 int coincidencia_central = 0;
	            for (int i = 1; i < n; i++) {
	                for (int j = 1; j < n; j++) {
	                    if ((i == j) && (matriz[i - 1][j - 1] == matriz[i][j])) { //busca secuencias entre las posiciones centrales de la matriz, comparando anterior con actual
	                        coincidencia_central++;
	                        if (coincidencia_central == 3) { //si encuentra una secuencia, suma al contador central y resetea para buscar mas
	                            contadorX++;
	                            coincidencia_central = 0;
	                        }
	                    } else {
	                        if ((i == j) && (matriz[i - 1][j - 1] != matriz[i][j])) { //reinicia el contador solo en caso de que en una posicion central no haya coincidencia con otra
	                            coincidencia_central = 0;
	                        }
	                    }
	                }
	            }
	            return contadorX;
	    }
	 
	 //BUSQUEDA DIAGONAL ARRIBA DE LA CENTRAL
	 public int busquedaOblicuaArriba(int n, char[][]matriz){//busca coincidencias solo desde la diagonal central por arriba
		 int contadorX = 0; 
		 	int vuelta = n - 1; //esta variable sirve, debido que a medida que hacemos diagonales, la cantidad de lugares a recorrer se disminuye
	            int coincidencia_arriba = 0;
	            for (int i = 0; i < n; i++) {
	                for (int j = 1; j < n; j++) {
	                    int aux1 = i;
	                    int aux2 = j;
	                    for (int k = 0; k < (vuelta - 1); k++) {// a medida que va disminuyendo la cantidad de vueltas, el for recorre menos lugares
	                        if (matriz[aux1][aux2] == matriz[aux1 + 1][aux2 + 1]) {
	                            coincidencia_arriba++;
	                            if (coincidencia_arriba == 3) {
	                                System.out.println("SE SUMO SECUENCIA EN OBL ARRIBA");
	                                contadorX++;
	                                coincidencia_arriba = 0;
	                            }
	                        } else {
	                            coincidencia_arriba = 0;
	                        }
	                        aux1++;
	                        aux2++;
	                    }
	                    coincidencia_arriba = 0;
	                    vuelta--; //por cada vuelta, resta un lugar a recorrer para que no quede fuera de rango el metodo
	                }
	            }
	        return contadorX;
	    }
	 
	 //BUSQUEDA ABAJO DE LA CENTRAL
	 public int busquedaOblicuaAbajo( int n, char[][]matriz){//busca coincidencias desde la diagonal central para abajo
		 int contadorX = 0;   
		 int vuelta = n - 1;
	            int coincidencia_abajo = 0;
	            for (int c = 0; c < n; c++) {
	                for (int f = 1; f < n; f++) {
	                    int aux1 = f;
	                    int aux2 = c;
	                    for (int k = 0; k < (vuelta - 1); k++) { 
	                        if (matriz[aux1][aux2] == matriz[aux1 + 1][aux2 + 1]) {
	                            coincidencia_abajo++;
	                            if (coincidencia_abajo == 3) {
	                                coincidencia_abajo = 0;
	                                contadorX++;
	                            }
	                        } else {
	                            coincidencia_abajo = 0;
	                        }
	                        aux1++;
	                        aux2++;
	                    }
	                    coincidencia_abajo = 0;
	                    vuelta--;
	                }
	            }
	        return contadorX;
	    }
}

