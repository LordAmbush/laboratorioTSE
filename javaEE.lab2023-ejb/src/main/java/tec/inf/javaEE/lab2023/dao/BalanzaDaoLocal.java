package tec.inf.javaEE.lab2023.dao;

import java.util.List;

import tec.inf.javaEE.lab2023.dto.PesadasDTO;
import tec.inf.javaEE.lab2023.entity.Viaje;

public interface BalanzaDaoLocal {

	void agregarPesada(Viaje v, String peso);

	List<PesadasDTO> listarPesadas();

}
