package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {
	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;
	private TipoApuesta tipoMasJugado;

	public Estadisticas(List<Resultado> historial) {
		calcularEstadisticas(historial);
	}
	private void calcularEstadisticas(List<Resultado> historial) {
		this.totalJugadas = historial.size(); // cantidad de resultados registrados[cite: 12]

		if (this.totalJugadas == 0) {
			return; // Si no hay jugadas, terminamos para evitar división por cero
		}

		int rachaActual = 0;
		Map<TipoApuesta, Integer> conteoApuestas = new HashMap<>();

		for (Resultado r : historial) {

			if (r.isAcierto()) {
				this.victorias++;
				rachaActual++;
				if (rachaActual > this.rachaMaxima) {
					this.rachaMaxima = rachaActual; // mayor secuencia de aciertos consecutivos[cite: 12]
				}
			} else {
				rachaActual = 0;
			}

			TipoApuesta tipo = r.getTipoApuesta();
			conteoApuestas.put(tipo, conteoApuestas.getOrDefault(tipo, 0) + 1);
		}

		this.porcentajeVictorias = (this.victorias * 100.0) / this.totalJugadas;

		int maxFrecuencia = -1;
		for (Map.Entry<TipoApuesta, Integer> entry : conteoApuestas.entrySet()) {
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
	public TipoApuesta getTipoMasJugado() { return tipoMasJugado; }


}