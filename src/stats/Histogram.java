package stats;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Histogram {
	
	// Lista para los valores del efecto avalancha
	private LinkedList<BigDecimal> histogram_AE;
	// Lista para los valores de la distribucion de probabilidad
	private LinkedList<BigDecimal> histogram_PD;
	
	public Histogram(){
		histogram_AE = new LinkedList<BigDecimal>();
		histogram_PD = new LinkedList<BigDecimal>();
	}

	public LinkedList<BigDecimal> getHistogramAE() {
		return histogram_AE;
	}
	
	public LinkedList<BigDecimal> getHistogramPD() {
		return histogram_PD;
	}
	
	// A�ade valor de efecto avalancha
	public void addDataAE(BigDecimal data){
		histogram_AE.add(data);
	}
	
	// A�ade valor de distribucion de probabilidad
	public void addDataPD(BigDecimal data){
		histogram_PD.add(data);
	}

	// Muestra por pantalla los valores del efecto avalancha
	public void printAE() {
		for (BigDecimal i:histogram_AE){
			System.out.println(i.toString().replace(".", ","));
		}
	}
	
	// Muestra por pantalla los valores de la distribucion de probabilidad
	public void printPD() {
		for (BigDecimal i:histogram_PD){
			System.out.println(i.toString().replace(".", ","));
		}
	}

}
