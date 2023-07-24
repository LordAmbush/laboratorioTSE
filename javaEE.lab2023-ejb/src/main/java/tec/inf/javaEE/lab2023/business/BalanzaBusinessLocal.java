package tec.inf.javaEE.lab2023.business;

import java.util.List;

import tec.inf.javaEE.lab2023.dto.PesadasDTO;

public interface BalanzaBusinessLocal {

	void agregarPesada(int id, String[] pesadas);

	List<PesadasDTO> listarPesadas();

}
