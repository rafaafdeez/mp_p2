package libClases;

import java.util.Scanner;

final public class Fecha implements Cloneable, Proceso {
	
	private int dia;
	private int mes;
	private int anio;
	
	public Fecha(int d, int m, int a){
		setFecha(d,m,a);
	}
	
	public Fecha (Fecha a){
		setFecha(a.getDia(),a.getMes(),a.getAnio());
	}
	
	public boolean bisiesto() {
		return anio%400 == 0 || (anio%4 == 0 && anio%100 != 0);
	}
	
	public Fecha diaSig() {
		Fecha f= (Fecha)this.clone();
		f.dia++;
		
		
		 int bi;
		  if(bisiesto()){
		        bi=29;
		    }else{
		        bi=28;
		    }

		   int diaM[]={0,31,bi,31,30,31,30,31,31,30,31,30,31};

		    if(f.dia>diaM[f.mes]){
		        f.dia-=diaM[f.mes];
		        f.mes++;
		        if(f.mes>12){
		            f.mes=1;
		            f.anio++;
		        }
		    }
		return f;
	}
	
	
	public void setFecha(int d, int m, int a) {
		this.dia=d;
		this.mes=m;
		this.anio=a;
		
		if(mes < 1){
	        mes=1;
	    }else if(mes >12){
	        mes=12;
	    }
		
		  int bi;
		  if(bisiesto()){
		        bi=29;
		    }else{
		        bi=28;
		    }

		   int diaM[]={0,31,bi,31,30,31,30,31,31,30,31,30,31};

		   if(dia<0){
		    dia=1;
		   }else if(dia>diaM[this.mes]){
		        dia=diaM[this.mes];
		   }
		
	}
	
	public int getDia() {return dia;}
	public int getMes() {return mes;}
	public int getAnio() {return anio;}
	
	public String toString() {
		String s="";
		if (dia<10) s=s+0;
		s=s+dia+"/";
		if (mes<10) s=s+0;
		s=s+mes+"/"+anio;
		return s;
	}
	
	public static Fecha pedirFecha() {
		Fecha fecha = null;
		boolean valida = false;
		Scanner sc = new Scanner(System.in);
		int dia, mes, anio;
		do {
			System.out.print("Introduce la Fecha (dd/mm/aaaa): ");
			String cadena = sc.next();
			String[] tokens = cadena.split("/");
			try {
			 if (tokens.length != 3)
			throw new NumberFormatException();
			 
			 dia = Integer.parseInt(tokens[0]); //parseInt lanza la excepcion
			 mes = Integer.parseInt(tokens[1]); //NumberFormatException si no
			 anio = Integer.parseInt(tokens[2]);//puede convertir el String a int
			 fecha = new Fecha(dia, mes, anio);
			 if (fecha.getDia() != dia || fecha.getMes() != mes)
			 throw new NumberFormatException();
			 valida=true;
			} catch(NumberFormatException e) {
				System.out.println("Fecha no valida");
			  }
		} while(!valida);
		
		return fecha;
		
	}
	
	public static boolean mayor(Fecha f1, Fecha f2) {
	/*
		boolean devolver=false;;
		if(f1.getAnio()>f2.getAnio()) {
			devolver=true;
		}else if(f1.getAnio()==f2.getAnio()) {
			if(f1.getMes()>f2.getMes()) {
				devolver=true;
			}else if(f1.getMes()==f2.getMes()) {
				if(f1.getDia()>f2.getDia()) {
					devolver=true;
				}
			}
		}
		return devolver;
		*/
		
		if (f1.anio*10000+f1.mes*100+f1.dia>f2.anio*10000+f2.mes*100+f2.dia)
			return true;
			else
				return false;
	}
	
	@Override
	public Object clone() {
		 //return new Fecha(this);
		 Object obj=null;
		 try {
		 obj=super.clone(); //se llama al clone() de la clase base (Object)
		 //que hace copia binaria de los atributos
		 } catch(CloneNotSupportedException ex) {
			 System.out.println(" no se puede duplicar");
		   }
		 return obj; 
	}
	
	@Override
	public boolean equals(Object obj) { //true sin son iguales
		if (this == obj) return true; //si apuntan al mismo sitio son iguales
		if (obj == null) return false;
		if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
		return false; // si los 2 no son de la misma clase no son iguales
		Fecha c = (Fecha) obj;
		return (dia==c.dia && mes==c.mes && anio==c.anio);
	}
	
	@Override
	public void ver() {
		System.out.println(this);
	}

}
