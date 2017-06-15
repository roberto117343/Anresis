/*
 * «Copyright 2017 Roberto Reinosa»
 * 
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
	
	public static final int alcance = 1000;
	public static final int alcance_Marco = 3;
	
	public static String sec_Nucl1[] = new String[alcance];
	public static String sec_Nucl2[] = new String[alcance];
	public static String sec_Nucl3[] = new String[alcance];
	
	public static String sec_aa1[] = new String[alcance];
	public static String sec_aa2[] = new String[alcance];
	public static String sec_aa3[] = new String[alcance];
	
	public static int identificador_Nucl[][] = new int[alcance_Marco][alcance];
	public static int finales_Nucl[][] = new int[alcance_Marco][alcance];
	
	public static void main(String[] args) throws IOException {
		
		String coleccion1;
		
		String Analisis_Id;
		
		String salida_Id;
		boolean salida_ID;
		
		boolean salida = true;
		String salida_Programa;
		
		InputStreamReader Secuencia_Nucleotidos = new InputStreamReader(System.in);
		
		InputStreamReader Id = new InputStreamReader(System.in);
		
		while(salida){
		
			System.out.println("\nIntroduce La Secuencia De ADN Del Provirus: ");
			
			BufferedReader buff1 = new BufferedReader(Secuencia_Nucleotidos);
		
			coleccion1 = buff1.readLine();
			coleccion1 = coleccion1.concat("^^^^^^");
			
			char cadena[] = coleccion1.toCharArray();
			
			Convertidor_ARN(cadena);
			
			for(int i = 0; i < sec_Nucl1.length; i++){
			
				sec_Nucl1[i]="Secuencia = ";
				sec_Nucl2[i]="Secuencia = ";
				sec_Nucl3[i]="Secuencia = ";
				
				sec_aa1[i]="Secuencia = ";
				sec_aa2[i]="Secuencia = ";
				sec_aa3[i]="Secuencia = ";
				
				
			}
		
			System.out.println("Secuencias De ARN Para Secuencias De Aminoácidos Encontrados: " + "\n");
			
			System.out.println("Marco De Lectura 1: " + "\n");
			Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl1, 0, identificador_Nucl, finales_Nucl, 0);			
			
			System.out.println("Marco De Lectura 2: " + "\n");
			Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl2, 1, identificador_Nucl, finales_Nucl, 1);
			
			System.out.println("Marco De Lectura 3: " + "\n");
			Identificador_Secuencias_Nucleotidos(cadena, sec_Nucl3, 2, identificador_Nucl, finales_Nucl, 2);
			
			
			System.out.println("Secuencias De Aminoácidos : " + "\n");
			
			System.out.println("Marco De Lectura 1: " + "\n");
			codigo_Letras_Aminoacidos(sec_Nucl1, sec_aa1, identificador_Nucl, 0);
			
			System.out.println("Marco De Lectura 2: " + "\n");
			codigo_Letras_Aminoacidos(sec_Nucl2, sec_aa2, identificador_Nucl, 1);
			
			System.out.println("Marco De Lectura 3: " + "\n");
			codigo_Letras_Aminoacidos(sec_Nucl3, sec_aa3, identificador_Nucl, 2);
			
			//Análisis
			
			try{
				
				salida_ID= true;
				
				while(salida_ID){
				
					System.out.println("Introduce Identificador Para Analizar :");
			
					BufferedReader id1 = new BufferedReader(Id);
					Analisis_Id = id1.readLine();
			
					Analisis(Analisis_Id, identificador_Nucl, finales_Nucl);
				
					System.out.println("¿Desea Analizar Otra Secuencia? S/N");
				
					BufferedReader salidaId = new BufferedReader(Id);
					salida_Id = salidaId.readLine();
				
					if(salida_Id.equals("s") || salida_Id.equals("S")){
						
						System.out.println("\n"+"--------------------------------------------------------------------------------------------------------" + "\n");
						salida_ID = true;
						
					}else{
						
						salida_ID = false;
						
					}
				
				}
				
			}catch(Exception e){
				
				System.out.println("\nID Incorrecto\n");
				
			};
			
			try{
				
				System.out.println("¿Desea Analizar Otra Secuencia Proviral? S/N");
				
				BufferedReader salidaPrograma = new BufferedReader(Id);
				salida_Programa = salidaPrograma.readLine();
				
				if(salida_Programa.equals("s") || salida_Programa.equals("S")){
					
					salida = true;
					System.out.println("\n"+"--------------------------------------------------------------------------------------------------------");

					
				}else{
					
					salida = false;
					
				}
				
				
			}catch(Exception e){System.out.println("Fallo Al Introducir Entrada");};
		
		}
		
	}
	public static void Convertidor_ARN(char cadena[]){ // Convierte la secuencia a ARN
		
		for(int i = 0; i<cadena.length;i++){
			
			if(cadena[i] == 'a'){
				
				cadena[i] = 'A';
			
			}else if(cadena[i] == 't' || cadena[i] == 'T' || cadena[i]== 'u'){
				
				cadena[i] = 'U';
				
			}else if(cadena[i] == 'g'){
				
				cadena[i] = 'G';
				
			}else if(cadena[i] == 'c'){
				
				cadena[i] = 'C';
				
			}else if (cadena[i] != 'c' && cadena[i] != 'C' && cadena[i] != 'a' && cadena[i] != 'A' && cadena[i] != 't' && cadena[i] != 'T' 
					&& cadena[i] != 'g' && cadena[i] != 'G' && cadena[i] != '^'){
				
				cadena[i] = '?';
				
			}
			
			
		}
		
	}
	public static void Identificador_Secuencias_Nucleotidos(char cadena[], String sec_Nucl[], int marco, int Identificadores[][],
			int Finalizadores[][], int marco_Estatico){ //Identifica las secuencias de nucleótidos que pasará a secuencias de aminoácidos
		
		int guardar_Inicio = 0;
		int guardar_Final = 0;
		String Base;
		int contador_String_secNucl = 0;
		int contador_Tamano_secNucl = 0;
		boolean nueva_Secuencia = false;
		boolean nuevo_Inicio = true;
		char secuencia_ARN_Temporal[];
			
		for(; marco<= cadena.length -4; marco+=3){
			
			if(cadena[marco]=='A' && cadena[marco+1]=='U' && cadena[marco+2]=='G' && nuevo_Inicio == true){
				
				guardar_Inicio= marco;
				nueva_Secuencia = true;
				nuevo_Inicio = false;
				
				
			}
			else if((cadena[marco]=='U' && cadena[marco+1]=='G' && cadena[marco+2]=='A')||
					(cadena[marco]=='U' && cadena[marco+1]=='A' && cadena[marco+2]=='A')||
					(cadena[marco]=='U' && cadena[marco+1]=='A' && cadena[marco+2]=='G')){
				
				if(nueva_Secuencia == true){ 
					
					guardar_Final = marco+2;
					
					Finalizadores[marco_Estatico][contador_Tamano_secNucl] = guardar_Final + 1;
					Identificadores[marco_Estatico][contador_Tamano_secNucl] = guardar_Inicio + 1; 
				
					for(int x = guardar_Inicio; x<= guardar_Final; x++){
					
						Base = String.valueOf(cadena[x]); 
					
						sec_Nucl[contador_String_secNucl]=sec_Nucl[contador_String_secNucl].concat(Base);
					
					}
					
				
					nueva_Secuencia = false;
					nuevo_Inicio = true;
					
					
					contador_Tamano_secNucl++;
					contador_String_secNucl++;
					
				}
				
			}
		}
		
		for(int i = 0; i<sec_Nucl.length; i++){
			
			if(sec_Nucl[i].equals("Secuencia = ")){
				break;
			}

			secuencia_ARN_Temporal = sec_Nucl[i].toCharArray();

			if(secuencia_ARN_Temporal.length<=102){
					
				continue;
					
			}
			
			System.out.println("#ID = " + Identificadores[marco_Estatico][i]);
			
			for(int y = 0; y < secuencia_ARN_Temporal.length; y++){
					
				if(y % 100 == 0 && y != 0){
						
					System.out.print("\n");
						
				}
				System.out.print(secuencia_ARN_Temporal[y]);
					
			}
				
			System.out.println("\n"+"--------------------------------------------------------------------------------------------------------" + "\n");

				
		}
	
	}
	public static void codigo_Letras_Aminoacidos(String secuencia[], String aa[], int Identificadores[][], int marco_Estatico){ // Pasa las secuencias de ARN a Secuencias de aminoácidos
		
		char secuencias_Temporal[];
		String codones="";
		String bases[] = new String[3];
		
		for(int i = 0; i<secuencia.length; i++){
			
			secuencias_Temporal = secuencia[i].toCharArray();
			
			for(int x = 12; x<secuencias_Temporal.length; x+=3){
				
				bases[0] = String.valueOf(secuencias_Temporal[x]);
				bases[1] = String.valueOf(secuencias_Temporal[x+1]);
				bases[2] = String.valueOf(secuencias_Temporal[x+2]);
				
				codones = codones.concat(bases[0]+bases[1]+bases[2]);
				
				if(codones.equals("UCU") || codones.equals("UCC") || codones.equals("UCA") || codones.equals("UCG") || codones.equals("AGU")
						|| codones.equals("AGC")){
					
					aa[i]= aa[i].concat("S");
					
				}else if(codones.equals("CGU") || codones.equals("CGC") || codones.equals("CGA") || codones.equals("CGG") || codones.equals("AGA")
						|| codones.equals("AGG")){
					
					aa[i] = aa[i].concat("R");
					
				}else if(codones.equals("UUA") || codones.equals("UUG") || codones.equals("CUU") || codones.equals("CUC") || codones.equals("CUA")
						|| codones.equals("CUG")){
					
					aa[i] = aa[i].concat("L");
					
				}else if(codones.equals("GCU") || codones.equals("GCC") || codones.equals("GCA") || codones.equals("GCG")){
					
					aa[i] = aa[i].concat("A");
					
				}else if(codones.equals("GGU") || codones.equals("GGC") || codones.equals("GGA") || codones.equals("GGG")){
					
					aa[i] = aa[i].concat("G");
					
				}else if(codones.equals("CCU") || codones.equals("CCC") || codones.equals("CCA") || codones.equals("CCG")){
					
					aa[i] = aa[i].concat("P");
					
				}else if(codones.equals("ACU") || codones.equals("ACC") || codones.equals("ACA") || codones.equals("ACG")){
					
					aa[i] = aa[i].concat("T");
					
				}else if(codones.equals("GUU") || codones.equals("GUC") || codones.equals("GUA") || codones.equals("GUG")){
					
					aa[i] = aa[i].concat("V");
					
				}else if(codones.equals("AUU") || codones.equals("AUC") || codones.equals("AUA")){
					
					aa[i] = aa[i].concat("I");
					
				}else if(codones.equals("AAU") || codones.equals("AAC")){
					
					aa[i] = aa[i].concat("N");
					
				}else if(codones.equals("GAU") || codones.equals("GAC")){
					
					aa[i] = aa[i].concat("D");
					
				}else if(codones.equals("UGU") || codones.equals("UGC")){
				
					aa[i] = aa[i].concat("C");
				
				}else if(codones.equals("CAA") || codones.equals("CAG")){
					
					aa[i] = aa[i].concat("Q");
					
				}else if(codones.equals("GAA") || codones.equals("GAG")){
				
					aa[i] = aa[i].concat("E");
				
				}else if(codones.equals("CAU") || codones.equals("CAC")){
					
					aa[i] = aa[i].concat("H");
					
				}else if(codones.equals("AAA") || codones.equals("AAG")){
				
					aa[i] = aa[i].concat("K");
				
				}else if(codones.equals("UUU") || codones.equals("UUC")){
					
					aa[i] = aa[i].concat("F");
					
				}else if(codones.equals("UAU") || codones.equals("UAC")){
				
					aa[i] = aa[i].concat("Y");
				
				}else if(codones.equals("AUG")){
					
					aa[i] = aa[i].concat("M");
					
				}else if(codones.equals("UGG")){
				
					aa[i] = aa[i].concat("W");
				
				}else if(codones.equals("UAA") || codones.equals("UAG") || codones.equals("UGA")){
					
					//Evita fallo al encontrar terminaciones
					
				}else{
					
					aa[i] = aa[i].concat("?");
					
				}
				
				codones="";
			
			}
			
			
		}
		
		char secuencia_AA_Temporal[];
		
		for(int i = 0; i<aa.length;i++){
			
			if(aa[i].equals("Secuencia = ")){
				break;
			}
			
			secuencia_AA_Temporal=aa[i].toCharArray();
			
			if(secuencia_AA_Temporal.length < 42){
					
					continue;	
			}
			
			System.out.println("#ID = " + Identificadores[marco_Estatico][i]);
			
			for(int x = 0; x<secuencia_AA_Temporal.length;x++){
				
				if(x % 100 == 0 && x != 0){
					
					System.out.print("\n");
					
				}
				System.out.print(secuencia_AA_Temporal[x]);
								
			}
			
			System.out.println("\n"+"--------------------------------------------------------------------------------------------------------" + "\n");

		}
	}
	public static void Analisis(String Analisis_Id, int Identificadores[][], int Finalizadores[][]){//primero el marco
		
		int Analisis_ID = Integer.parseInt(Analisis_Id);
		
		int marco_Analisis = 0;
		
		int posicion_Arr_Analisis = 0;
		
		for(int i = 0; i< alcance_Marco; i++){
			
			for(int x = 0; x<alcance; x++){
				
				if(Analisis_ID == Identificadores[i][x]){
					
					marco_Analisis = i;
					posicion_Arr_Analisis = x;
					
				}else if(Identificadores[i][x] == 0){ // Para que cuando sea 0 el valor del array que guarda identificadores pare y siga con la siguente secuencia
					
					break;
					
				}
			}
		}
		
		// Empieza la parte del análisis de secuencias
				
		
		System.out.println("\nLongitudes: \n");
		
		System.out.println("Longitud De La Secuencia Problema (Nucleótidos): " + Analisis_Longitud(marco_Analisis, posicion_Arr_Analisis));
		System.out.println("Longitud De La Secuencia Problema (Aminoácidos): " + (Analisis_Longitud(marco_Analisis, posicion_Arr_Analisis)-1)/3);
		
		System.out.println("Inicio Y Final De La Secuencia Problema (Nucleótidos): " + Identificadores[marco_Analisis][posicion_Arr_Analisis] + " - " + 
		Finalizadores[marco_Analisis][posicion_Arr_Analisis]);
		
		System.out.println("Marco De La Secuencia Problema: " + (marco_Analisis +1));
		
		proporcion_Nucleotidos(marco_Analisis, posicion_Arr_Analisis, "La Secuencia Problema");
		
		proporcion_Aminoacidos(marco_Analisis, posicion_Arr_Analisis, "La Secuencia Problema");
		
		
	}
	public static int Analisis_Longitud(int marco, int valor){ 
		
		if(marco == 0){
			
			return sec_Nucl1[valor].length() - 12; 
			
		}else if(marco == 1){
			
			return sec_Nucl2[valor].length() - 12;
			
		}else if(marco == 2){
			
			return sec_Nucl3[valor].length() - 12;
			
		}else{
			
			System.out.println("Fallo al asignar el marco");
			return 1;
			
		}
		
	}
	public static void proporcion_Nucleotidos(int marco, int valor, String nombre){
		
		char sec_Nucl_Analizar[];
		
		int contador_A = 0;
		int contador_U = 0;
		int contador_G = 0;
		int contador_C = 0;
		int contador_Nucl_Anomalos = 0;
		
		if(marco == 0){
			
			sec_Nucl_Analizar = sec_Nucl1[valor].toCharArray();
			
		}else if(marco == 1){
			
			sec_Nucl_Analizar = sec_Nucl2[valor].toCharArray();
			
		}else if(marco == 2){
			
			sec_Nucl_Analizar = sec_Nucl3[valor].toCharArray();
			
		}else{
			
			sec_Nucl_Analizar = null; 
			System.out.println("Fallo al asignar el marco");
			
		}
		
		for(int i = 12; i< sec_Nucl_Analizar.length; i++){
			
			if(sec_Nucl_Analizar[i] == 'A'){
				
				contador_A++;	
				
			}else if(sec_Nucl_Analizar[i] == 'U'){
				
				contador_U++;
				
			}else if(sec_Nucl_Analizar[i] == 'G'){
				
				contador_G++;
				
			}else if(sec_Nucl_Analizar[i] == 'C'){
				
				contador_C++;
				
			}else{
				
				contador_Nucl_Anomalos++;
				
			}
				
		}
		
		System.out.println("\nProporción De Nucleótidos" + " Para " + nombre +  ": ");
		
		System.out.println("\nProporción De Adenina : " + calculo_regla(contador_A, sec_Nucl_Analizar.length - 12) + " %");
		System.out.println("Proporción De Uracilo : " + calculo_regla(contador_U, sec_Nucl_Analizar.length - 12) + " %" );
		System.out.println("Proporción De Guanina : " + calculo_regla(contador_G, sec_Nucl_Analizar.length - 12) + " %");
		System.out.println("Proporción De Citosina : " + calculo_regla(contador_C, sec_Nucl_Analizar.length - 12) + " %" + "\n");
		System.out.println("Proporción De Nucleótidos Anómalos : " + calculo_regla(contador_Nucl_Anomalos, sec_Nucl_Analizar.length - 12) + " %\n");
		
	}
	public static void proporcion_Aminoacidos(int marco, int valor, String nombre){
		
		char Analisis_Aminoacidos_Temporal[];
		double S = 0, R = 0, L = 0, A = 0, G = 0, P = 0, T = 0, V = 0, I = 0, N = 0, D = 0, C = 0, Q = 0, E = 0, H = 0, K = 0, F = 0, Y = 0, M = 0, W = 0, Anomalo = 0;
		
		double peso_AA_Libres = 0;
		final double peso_Agua = 18.01528;
		
		final double peso_S = 105.093, peso_R = 174.2017, peso_L = 131.1736, peso_A = 89.0935, peso_G = 75.0669, peso_P = 115.131, peso_T = 119.1197, peso_V = 117.1469,
				peso_I = 131.1736, peso_N = 132.1184, peso_D = 133.1032, peso_C = 121.159, peso_Q = 146.1451, peso_E = 147.1299, peso_H = 155.1552, 
				peso_K = 146.1882, peso_F = 165.19, peso_Y = 181.1894, peso_M = 149.2124, peso_W = 204.2262;
		
		if(marco == 0){
			
			Analisis_Aminoacidos_Temporal = sec_aa1[valor].toCharArray();
			
		}else if(marco == 1){
			
			Analisis_Aminoacidos_Temporal = sec_aa2[valor].toCharArray();
			
		}else if(marco == 2){
			
			Analisis_Aminoacidos_Temporal = sec_aa3[valor].toCharArray();
			
		}else{
			
			System.out.println("Fallo Al Asignar El Marco");
			Analisis_Aminoacidos_Temporal = null; 
			
		}
		
		for(int i = 12; i< Analisis_Aminoacidos_Temporal.length; i++){
			
			switch (Analisis_Aminoacidos_Temporal[i]){
				
				case 'S':		
					
					S++;
					peso_AA_Libres += peso_S;
					break;
			
				case 'R':
					
					R++;
					peso_AA_Libres += peso_R;
					break;
					
				case 'L':
					
					L++;
					peso_AA_Libres += peso_L;
					break;
					
				case 'A':
					
					A++;
					peso_AA_Libres += peso_A;
					break;
					
				case 'G':
					
					G++;
					peso_AA_Libres += peso_G;
					break;
					
				case 'P':
					
					P++;
					peso_AA_Libres += peso_P;
					break;
					
				case 'T':
					
					T++;
					peso_AA_Libres += peso_T;
					break;
					
				case 'V':
					
					V++;
					peso_AA_Libres += peso_V;
					break;
					
				case 'I':
					
					I++;
					peso_AA_Libres += peso_I;
					break;
				
				case 'N':
					
					N++;
					peso_AA_Libres += peso_N;
					break;
				
				case 'D':
					
					D++;
					peso_AA_Libres += peso_D;
					break;
				
				case 'C':
					
					C++;
					peso_AA_Libres += peso_C;
					break;
				
				case 'Q':
					
					Q++;
					peso_AA_Libres += peso_Q;
					break;
					
				case 'E':
					
					E++;
					peso_AA_Libres += peso_E;
					break;
				
				case 'H':
					
					H++;
					peso_AA_Libres += peso_H;
					break;
					
				case 'K':
					
					K++;
					peso_AA_Libres += peso_K;
					break;
					
				case 'F':
					
					F++;
					peso_AA_Libres += peso_F;
					break;
					
				case 'Y':
					
					Y++;
					peso_AA_Libres += peso_Y;
					break;
					
				case 'M':
					
					M++;
					peso_AA_Libres += peso_M;
					break;
					
				case 'W':
					
					W++;
					peso_AA_Libres += peso_W;
					break;
					
				case '?':
					
					Anomalo++;
					break;
					
				default:
					
					System.out.println("Fallo Al Contar Aminoácido");
					
			}
			
		}
		
		System.out.println("Proporción De Aminoácidos Para " + nombre + " : \n");
		
		S = calculo_regla((int)S, Analisis_Aminoacidos_Temporal.length -12);
		R = calculo_regla((int)R, Analisis_Aminoacidos_Temporal.length -12);
		L = calculo_regla((int)L, Analisis_Aminoacidos_Temporal.length -12);
		A = calculo_regla((int)A, Analisis_Aminoacidos_Temporal.length -12);
		G = calculo_regla((int)G, Analisis_Aminoacidos_Temporal.length -12);
		P = calculo_regla((int)P, Analisis_Aminoacidos_Temporal.length -12);
		T = calculo_regla((int)T, Analisis_Aminoacidos_Temporal.length -12);
		V = calculo_regla((int)V, Analisis_Aminoacidos_Temporal.length -12);
		I = calculo_regla((int)I, Analisis_Aminoacidos_Temporal.length -12);
		N = calculo_regla((int)N, Analisis_Aminoacidos_Temporal.length -12);
		D = calculo_regla((int)D, Analisis_Aminoacidos_Temporal.length -12);
		C = calculo_regla((int)C, Analisis_Aminoacidos_Temporal.length -12);
		Q = calculo_regla((int)Q, Analisis_Aminoacidos_Temporal.length -12);
		E = calculo_regla((int)E, Analisis_Aminoacidos_Temporal.length -12);
		H = calculo_regla((int)H, Analisis_Aminoacidos_Temporal.length -12);
		K = calculo_regla((int)K, Analisis_Aminoacidos_Temporal.length -12);
		F = calculo_regla((int)F, Analisis_Aminoacidos_Temporal.length -12);
		Y = calculo_regla((int)Y, Analisis_Aminoacidos_Temporal.length -12);
		M = calculo_regla((int)M, Analisis_Aminoacidos_Temporal.length -12);
		W = calculo_regla((int)W, Analisis_Aminoacidos_Temporal.length -12);
		Anomalo = calculo_regla((int)Anomalo, Analisis_Aminoacidos_Temporal.length -12);
		
		
		System.out.println("S = " + S + " %" + " R = " + R + " %" + " L = " + L +  " %" + " A = " + A + " %" + " G = " + G + " %" + "\nP = " + P + " %" + " T = " + T + " %" +
		" V = " + V + " %" + " I = " + I + " %" + " N = " + N + " %" + "\nD = " + D + " %" + " C = " + C + " %" + " Q = " + Q + " %" + " E = " + E + " %" + " H = " + H + " %" +
				"\nK = " + K + " %" + " F = " + F + " %" + " Y = " + Y + " %" + " M = " + M + " %" + " W = " + W + " %" + "\n");
		System.out.println("Proporción De Aminoácidos Anómalos: " + Anomalo + " %\n");
		
		System.out.println("Masa Molecular ~ " + (peso_AA_Libres - (peso_Agua * ((Analisis_Aminoacidos_Temporal.length - 12)-1)))/1000 + " kDa\n");
		
		if(Anomalo != 0){
			
			System.out.println("En La Masa Molecular No Son Considerados Los Aminoácidos Anómalos\n");
			
		}
		
		
	}
	public static double calculo_regla(int nucl, int longitud){
				
		return (double)(nucl * 100)/(double)longitud;
		
	}

} 
