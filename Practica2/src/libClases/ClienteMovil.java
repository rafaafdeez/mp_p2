package libClases;

public class ClienteMovil extends Cliente{
		private Fecha FechaPermanencia;
		private float minutosHablados;
		private float precioMinuto;
		
		public ClienteMovil(String nif, String nom, Fecha fNac, Fecha fAlta, Fecha fp, float mh, float pm){
			super(nif,nom,fNac,fAlta);
			FechaPermanencia=fp;
			minutosHablados=mh;
			precioMinuto=pm;
		}
		
		//si no se especifica permanencia si le amplia 1 año
		public ClienteMovil(String nif, String nom, Fecha fNac, Fecha fAlta, float mh, float pm){
			this (nif, nom, fNac, fAlta, new Fecha(fAlta.getDia(), fAlta.getMes(), fAlta.getAnio()+1 ), mh, pm);
		}
		//si no se especifica fecha de permanencia se le asigna una por defecto
	
		public ClienteMovil(String nif, String nom, Fecha fNac, float mh, float pm){
			this (nif, nom, fNac, getFechaPorDefecto(), mh, pm);
		}
	
		public ClienteMovil(ClienteMovil cm) {
			super(cm);
			FechaPermanencia=cm.getFPermanencia();
			minutosHablados=cm.getMinutos();
			precioMinuto=cm.getPrecioMinuto();
			
		}
		
		public Fecha getFPermanencia(){return FechaPermanencia;}
	    public float getMinutos(){return minutosHablados;}
	    public float getPrecioMinuto(){return precioMinuto;}
	    
	    public void setFPermanencia(Fecha f){FechaPermanencia=f;}
	    public void setMinutos(float m){minutosHablados = m;}
	    public void setPrecioMinuto(float p){precioMinuto = p;}
	    
	    public float factura() {return precioMinuto*minutosHablados;}
	    
	    @Override
	    public Object clone() {
	    	return new ClienteMovil(this.getNif(),this.getNombre(),this.getFechaNac(),this.getFechaAlta(),this.getFPermanencia(),this.getMinutos(),this.getPrecioMinuto());
	    }
	    
	    @Override
		public boolean equals(Object o) {
	    	/*
	    	ClienteMovil cm=(ClienteMovil) o;
			return o instanceof ClienteMovil && this.getNif()==cm.getNif() ;
			*/
			return o instanceof ClienteMovil && getNif().equals(((Cliente)o).getNif());	 
		}
	    
	    @Override
	    public String toString() {
	    	return super.toString()+" "+FechaPermanencia+" "+minutosHablados+" x "+precioMinuto+" --> "+factura();
	    }

}
