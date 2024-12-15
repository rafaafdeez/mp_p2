package libClases;

public class Cliente implements Cloneable, Proceso {
	// metodo static patra que sea visible y actualizable en todos
	// los objetos creados
	private static int contador = 1;
	private static Fecha f_xdefecto = new Fecha(1, 1, 2018);
	private final String nif;
	private final int codCliente;
	private String nombre;
	private final Fecha fechaNac;
	private final Fecha fechaAlta;

	public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta) {
		this.nif = NIF;
		this.nombre = nom;
		this.fechaNac = fNac;
		this.fechaAlta = fAlta;
		this.codCliente = contador++;
	}

	public Cliente(String NIF, String nom, Fecha fNac) {
		this.nif = NIF;
		this.nombre = nom;
		this.fechaNac = fNac;
		this.fechaAlta = f_xdefecto;
		this.codCliente = contador++;
	}

	public Cliente(Cliente c) {
		this.nif = c.getNif();
		this.nombre = c.getNombre();
		this.fechaNac = c.getFechaNac();
		this.fechaAlta = c.getFechaAlta();
		this.codCliente = contador++;
	}

	// necesario para poder ver el objeto con this
	public String toString() {
		return nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " - " + fechaAlta + ")";
	}

	// metodos final get para que no se puedan sobreescribir por herencia
	public final String getNombre() {
		return nombre;
	}

	public final String getNif() {
		return nif;
	}

	public final Fecha getFechaNac() {
		return new Fecha(fechaNac);
	}

	public final Fecha getFechaAlta() {
		return new Fecha(fechaAlta);
	}

	public final int getCodCliente() {
		return codCliente;
	}

	public void setNombre(String n) {
		nombre = n;
	}

	public final static Fecha getFechaPorDefecto() {
		return f_xdefecto;
	}

	public void setFechaAlta(Fecha a) {
		this.fechaAlta.setFecha(a.getDia(), a.getMes(), a.getAnio());
	}

	public final static void setFechaPorDefecto(Fecha f) {
		f_xdefecto.setFecha(f.getDia(), f.getMes(), f.getAnio());
	}

	public float factura() {
		throw new UnsupportedOperationException("SIN TIPO");
	}

	@Override
	public Object clone() {
		return new Cliente(this);
	}

	@Override
	public boolean equals(Object obj) { // true sin son iguales
		Cliente nc = (Cliente) obj;
		return (obj instanceof Cliente) && nif.equals(nc.getNif());
	}

	@Override
	public void ver() {
		System.out.println(this);
	}
}

/*
 * package libClases;
 * 
 * public class ClienteVIP extends Cliente {
 * private float descuento;
 * 
 * public ClienteVIP(String NIF, String nom, Fecha fNac, Fecha fAlta, float
 * descuento) {
 * super(NIF, nom, fNac, fAlta);
 * this.descuento = descuento;
 * }
 * 
 * public ClienteVIP(String NIF, String nom, Fecha fNac, float descuento) {
 * super(NIF, nom, fNac);
 * this.descuento = descuento;
 * }
 * 
 * public float getDescuento() {
 * return descuento;
 * }
 * 
 * public void setDescuento(float descuento) {
 * this.descuento = descuento;
 * }
 * 
 * public float calcularDescuento(float monto) {
 * return monto - (monto * descuento / 100);
 * }
 * 
 * @Override
 * public void ver() {
 * super.ver();
 * System.out.println("Descuento: " + descuento + "%");
 * }
 * }
 */