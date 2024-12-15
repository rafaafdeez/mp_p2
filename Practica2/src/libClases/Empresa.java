package libClases;

import java.util.Scanner;

public class Empresa implements Cloneable, Proceso {
	private Cliente[] clientes;
	private int nClientes;
	private final int INCRE = 2;

	public Empresa() {
		clientes = new Cliente[INCRE];
		this.nClientes = 0;
	}

	public void alta(Cliente c) {
		if (this.nClientes == 0 && c != null) {
			clientes[nClientes] = c;
			nClientes++;
		} else if (buscarCliente(c.getNif()) == -1 && c != null) {
			clientes[nClientes] = c;
			nClientes++;
		}

		if (nClientes == clientes.length) {
			Cliente[] aux = new Cliente[clientes.length + INCRE];
			for (int i = 0; i < clientes.length; i++) {
				aux[i] = clientes[i];
			}
			clientes = aux;
		}
	}

	public void alta() {
		Scanner teclado = new Scanner(System.in);

		String dni, nom;
		Fecha fn, fa;
		float mh;
		int opc;
		System.out.print("DNI: ");
		dni = teclado.nextLine();

		if (buscarCliente(dni) == -1) {
			System.out.print("Nombre: ");
			nom = teclado.nextLine();

			System.out.println("Fecha de Nacimiento: ");
			fn = Fecha.pedirFecha();

			System.out.println("Fecha de Alta: ");
			fa = Fecha.pedirFecha();

			System.out.print("Minutos que habla al mes: ");
			mh = teclado.nextFloat();

			do {
				System.out.print("Indique tipo de cliente (1-Movil, 2-Tarifa Plana)" + ": ");
				opc = teclado.nextInt();

				if (opc == 1) {
					float pm;
					Fecha fp;

					System.out.print("Precio por minuto");
					pm = teclado.nextFloat();

					System.out.println("Fecha fin permanencia: ");
					fp = Fecha.pedirFecha();
					ClienteMovil cnuevo = new ClienteMovil(dni, nom, fn, fa, fp, mh, pm);

					alta(cnuevo);

				} else if (opc == 2) {
					String nac;

					System.out.print("Introduzca nacionalidad: ");
					nac = teclado.nextLine();

					ClienteTarifaPlana cnuevo = new ClienteTarifaPlana(dni, nom, fn, fa, mh, nac);

					alta(cnuevo);
				} else {
					System.out.println("Opcion introducida incorrecta" + "\n");
				}

			} while (opc != 1 && opc != 2);

		} else {
			System.out.println("El dni introducido ya existe");
		}
	}

	public void baja(String codigo) {
		int pos = buscarCliente(codigo);
		if (pos != -1) {
			for (int i = pos; i < nClientes - 1; i++) {
				clientes[i] = clientes[i + 1];
			}
			nClientes--;
		}

		if (nClientes < clientes.length - INCRE) {
			Cliente[] aux = new Cliente[clientes.length - INCRE];
			for (int i = 0; i < aux.length; i++) {
				aux[i] = clientes[i];
			}
			clientes = aux;
		}
	}

	public void baja() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduzca nif cliente a dar de baja: ");
		String niff = sc.next();
		int pos = buscarCliente(niff);
		if (pos != -1) {
			System.out.println(clientes[pos]);
			System.out.print("¿Seguro que desea eliminarlo (s/n)?: ");
			String opc = sc.next();
			if (opc.equals("s") == true) {
				baja(niff);
				System.out
						.println("El cliente " + clientes[pos].getNombre() + " con nif " + niff + " ha sido eliminado");
			} else {
				System.out.println("El cliente con nif " + niff + " no se elimina");
			}
		} else {
			System.out.println("No existe cliente con nif " + niff);
		}
	}

	private int buscarCliente(String dni) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < this.nClientes) {
			if (clientes[i].getNif().equals(dni)) {
				encontrado = true;
			} else {
				i++;
			}
		}

		if (encontrado) {
			return i;
		} else {
			return -1;
		}

	}

	public float factura() {
		float devolver = 0;

		for (int i = 0; i < nClientes; i++) {
			devolver += clientes[i].factura();
		}
		return devolver;
	}

	public void descuento(int desc) {
		ClienteMovil aux;
		float nuevoprecio;
		for (int i = 0; i < nClientes; i++) {
			if (clientes[i].getClass() == ClienteMovil.class) {
				aux = (ClienteMovil) clientes[i];
				nuevoprecio = (desc * aux.getPrecioMinuto()) / 100;
				aux.setPrecioMinuto(nuevoprecio);
			}
		}
	}

	public int nClienteMovil() {
		int contador = 0;
		for (int i = 0; i < nClientes; i++) {
			if (clientes[i].getClass() == ClienteMovil.class) {
				contador++;
			}
		}
		return contador;
	}

	public int nClienteTarifaPlana() {
		int contador = 0;
		for (int i = 0; i < nClientes; i++) {
			if (clientes[i].getClass() == ClienteTarifaPlana.class) {
				contador++;
			}
		}
		return contador;
	}

	public void ver() {
		System.out.println(this);
	}

	public int getN() {
		return nClientes;
	}

	@Override
	public String toString() {
		String mostrar = "";
		for (int i = 0; i < nClientes; i++) {
			mostrar += clientes[i] + "\n";
		}
		return mostrar;
	}

	@Override
	public Object clone() {
		Empresa obj = null;
		try {
			obj = (Empresa) super.clone();
			obj.clientes = clientes.clone();
			for (int i = 0; i < nClientes; i++)
				obj.clientes[i] = (Cliente) clientes[i].clone();
		} catch (CloneNotSupportedException ex) {

		}
		return (Object) obj;
	}
}

/*
 * public int eliminarPeoresContratosTP() {
 * // Paso 1: Calcular la factura media de los contratos de tipo
 * ContratoTarifaPlana
 * double sumaFacturas = 0;
 * int contadorTP = 0;
 * 
 * for (Contrato contrato : contratos) {
 * if (contrato instanceof ContratoTarifaPlana) {
 * sumaFacturas += ((ContratoTarifaPlana) contrato).getFactura();
 * contadorTP++;
 * }
 * }
 * 
 * if (contadorTP == 0) {
 * return 0; // No hay contratos de tipo ContratoTarifaPlana
 * }
 * 
 * double facturaMedia = sumaFacturas / contadorTP;
 * 
 * // Paso 2: Eliminar los contratos de tipo ContratoTarifaPlana que tengan una
 * factura menor a la media
 * int contratosEliminados = 0;
 * Iterator<Contrato> iterator = contratos.iterator();
 * 
 * while (iterator.hasNext()) {
 * Contrato contrato = iterator.next();
 * if (contrato instanceof ContratoTarifaPlana && ((ContratoTarifaPlana)
 * contrato).getFactura() < facturaMedia) {
 * iterator.remove();
 * contratosEliminados++;
 * }
 * }
 * 
 * // Paso 3: Devolver el número de contratos eliminados
 * return contratosEliminados;
 * }
 * 
 * // Métodos adicionales para agregar contratos, etc.
 * public void agregarContrato(Contrato contrato) {
 * contratos.add(contrato);
 * }
 */