package stats;

public class Histogram {
	
	// Lista para los valores del efecto avalancha
	private int[] histogram;

	public Histogram(int n){
		histogram = new int[n];
	}

	public int[] getHistogram() {
		return histogram;
	}
	

	// A�ade valor de efecto avalancha
	public void addData(int data){
		histogram[data]++;
	}
	

	// Muestra por pantalla los valores del efecto avalancha
	public void print() {
		for (int data: histogram){
			System.out.println(data);
		}
	}

}
