package tec.inf.javaEE.lab2023.business;

import java.util.List;

import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.InfoViajesDTO;

public interface GraficasBusinessLocal {

	List<GraficasDTO> EmpresaViaje();

	List<GraficasDTO> EmpresaVehiculo();

	List<GraficasDTO> UsuarioTipo();

	List<GraficasDTO> ViajeRubro();

	List<GraficasDTO> ViajeTiposDeCarga();

	List<GraficasDTO> RubroVolumen();

	List<InfoViajesDTO> InfoViajes();

	GraficasDTO cantConductores(String empresa);

	GraficasDTO cantViajes(String empresa);

	GraficasDTO cantVehiculos(String empresa);

}
