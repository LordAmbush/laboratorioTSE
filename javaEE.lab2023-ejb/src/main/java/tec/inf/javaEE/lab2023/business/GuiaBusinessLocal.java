package tec.inf.javaEE.lab2023.business;


public interface GuiaBusinessLocal {
	void altaGuia(int volumenDeCarga, String origen, String destino, String rubro, String tipoDeCarga)throws Exception;	
	public int obtenerUltimaGuia();
}
