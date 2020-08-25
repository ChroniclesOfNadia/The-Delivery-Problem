package gad;

public class Chromosome {
	int fitness;
	int genes [];
	int geneLength;
	int arrWeight [];
	int arrValue [];
	int weight;
	int value;
	
	public Chromosome() {
		
	}
	public Chromosome(int geneLength, int arrWeight [], int arrValue []){		
		this.geneLength=geneLength;
		genes= new int[geneLength];
		this.arrWeight= arrWeight;
		this.arrValue=arrValue;
		
	}
	
	public void generateRandomChrom(){
		
		for(int i=0; i<geneLength; i++) {
			genes[i] = (int)(2 * Math.random());
		}
		
		
	}
	
	public void printC() {
		for(int i=0; i<geneLength; i++) {
			System.out.print(genes[i]+" ");	
		}
		
	}

	
	public void calcWeight() {
		weight=0;
		for(int i=0; i<geneLength; i++) {
			weight = weight + genes[i]*arrWeight[i];
		}
	}
	
	
	
	public void calcValue() {
		value=0;
		for(int i=0; i<geneLength; i++) {
			value = value + genes[i]*arrValue[i];
		}
			
	}
	

	public int getFitness() {
		fitness=0;
		fitness=value;
		return fitness;
	}

	

	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}

	
	


}
