package tec.inf.javaEE.lab2023.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import tec.inf.javaEE.lab2023.business.GraficasBusinessLocal;
import tec.inf.javaEE.lab2023.dao.EmpresaDaoLocal;
import tec.inf.javaEE.lab2023.dao.GuiaDaoLocal;
import tec.inf.javaEE.lab2023.dao.UsuarioDaoLocal;
import tec.inf.javaEE.lab2023.dao.ViajeDaoLocal;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;
import tec.inf.javaEE.lab2023.dto.InfoViajesDTO;

@Stateless
@Named("graficaBusiness")
public class GraficasBusiness implements GraficasBusinessLocal{

	@EJB
	EmpresaDaoLocal empresaDao;
	@EJB
	UsuarioDaoLocal usuarioDao;
	@EJB
	ViajeDaoLocal viajeDao;
	@EJB
	GuiaDaoLocal guiaDao;
	
	@Override
	public List<GraficasDTO> EmpresaViaje() {
		return empresaDao.EmpresasViajes();
	}
	
	@Override
	public List<GraficasDTO> EmpresaVehiculo() {
		return empresaDao.EmpresasVehiculos();
	}
	
	@Override
	public List<GraficasDTO> UsuarioTipo() {
		return usuarioDao.UsuariosTipos();
	}

	@Override
	public List<GraficasDTO> ViajeRubro() {
		return viajeDao.ViajeRubro();
	}

	@Override
	public List<GraficasDTO> ViajeTiposDeCarga() {
		return viajeDao.ViajeTipoCarga();
	}

	@Override
	public List<GraficasDTO> RubroVolumen() {
		return guiaDao.VolumenCargaRubro();
	}
	
	@Override
	public List<InfoViajesDTO> InfoViajes(){
		return viajeDao.infoViajes();
	}
	@Override
	public GraficasDTO cantConductores(String empresa) {
		return empresaDao.cantConductores(empresa);
	}
	@Override
	public GraficasDTO cantViajes(String empresa) {
		return empresaDao.cantViajes(empresa);
	}
	@Override
	public GraficasDTO cantVehiculos(String empresa) {
		return empresaDao.cantVehiculos(empresa);
	}
}
