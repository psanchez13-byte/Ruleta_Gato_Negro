package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {
	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;
	private String tipoMasJugado;

	public Estadisticas(List<Resultado> historial) {
		calcularEstadisticas(historial);
	}

	private void calcularEstadisticas(List<Resultado> historial) {
		this.totalJugadas = historial.size();

		if (this.totalJugadas == 0) return;

		int rachaActual = 0;
		Map<String, Integer> conteoApuestas = new HashMap<>();

		for (Resultado r : historial) {
			if (r.isAcierto()) {
				this.victorias++;
				rachaActual++;
				if (rachaActual > this.rachaMaxima) {
					this.rachaMaxima = rachaActual;
				}
			} else {
				rachaActual = 0;
			}

			String etiqueta = r.getApuesta().getEtiqueta();
			conteoApuestas.put(etiqueta, conteoApuestas.getOrDefault(etiqueta, 0) + 1);
		}

		this.porcentajeVictorias = (this.victorias * 100.0) / this.totalJugadas;

		int maxFrecuencia = -1;
		for (Map.Entry<String, Integer> entry : conteoApuestas.entrySet()) {
			if (entry.getValue() > maxFrecuencia) {
				maxFrecuencia = entry.getValue();
				this.tipoMasJugado = entry.getKey();
			}
		}
	}

	public int getTotalJugadas() { return totalJugadas; }
	public int getVictorias() { return victorias; }
	public double getPorcentajeVictorias() { return porcentajeVictorias; }
	public int getRachaMaxima() { return rachaMaxima; }
	public String getTipoMasJugado() { return tipoMasJugado; }
}