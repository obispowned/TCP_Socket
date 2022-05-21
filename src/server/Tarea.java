package server;

public class Tarea {
	private String descripcion;
	private String estado;
	
	/*CONSTUCTOR*/
	public Tarea(String descripcion, String estado)
	{
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	/*GETTER Y SETTER*/
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/*toString*/
	public String toString() {
        return "Descripcion:" + descripcion + "- Estado:" + estado;
    }
	
}

