package libClases;

public class ClienteTarifaPlana extends Cliente {

	private static float precio=20f;
	private static int limite=300;
	private static float exceso=0.15f;
	
	private float minutosHablados;
	private String nacionalidad;
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float mh, String nac){
		super(NIF,nom,fNac,fAlta);
		minutosHablados=mh;
		nacionalidad=nac;
	}
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float mh, String nac){
		super(NIF,nom,fNac);
		minutosHablados=mh;
		nacionalidad=nac;
	}
	
	public ClienteTarifaPlana(ClienteTarifaPlana ctf){
		super(ctf);
		minutosHablados=ctf.getMinutosHablados();
		nacionalidad=ctf.getNacionalidad();
	}
	
	
	public float getMinutosHablados() {return minutosHablados;}
	
	public String getNacionalidad() {return nacionalidad;}
	
	public void setMinutos(float min) {minutosHablados=min;}
	
	public void setNacionalidad(String nac) {nacionalidad=nac;}
	
	public float factura() {
		float fact=0;
	    if(minutosHablados>limite){
	        fact=precio+((minutosHablados-limite)*exceso);
	    }else{
	        fact=precio;
	    }

	    return fact;
	}
	
	public static float getPrecio() {return precio;}
	
	public static int getLimite() {return limite;}
	
	public static void setTarifa(int l, float p) {
		precio=p;
		limite=l;
	}
	
	public String toString() {
		return super.toString()+" "+nacionalidad+" ["+limite+" por "+precio+"] "+minutosHablados+" --> "+factura();
		
	}
	
	public static float getTarifa(){return precio;}
	
    @Override
    public Object clone() {
    	return new ClienteTarifaPlana(this.getNif(),this.getNombre(),this.getFechaNac(),this.getFechaAlta(),this.getMinutosHablados(),this.getNacionalidad());
    }
    
    @Override
	public boolean equals(Object o) {
    	/*
    	ClienteTarifaPlana cm=(ClienteTarifaPlana) o;
		return o instanceof ClienteTarifaPlana && this.getNif().equals(cm.getNif());
		*/
		return o instanceof ClienteTarifaPlana && getNif().equals(((Cliente)o).getNif());
    }
}
