package gad;
import java.util.*;

public class Population {

	int length;
	int arrWeight [];
	int arrValue [];
	int capacity;
	ArrayList<Chromosome> population = new ArrayList<Chromosome>();
	public Population() {
		
	}
	public Population(int capacity, int length, int arrWeight [], int arrValue []) {
		this.capacity= capacity;
		this.length=length;
		this.arrWeight=arrWeight;
		this.arrValue=arrValue;
		
	}
	
	public void initPopulation() {
		
		for(int i=0;i<1000; i++) {
			Chromosome temp= new Chromosome(length, arrWeight, arrValue);
			temp.generateRandomChrom();
			temp.calcWeight();
			if(temp.getWeight()<=capacity) {
				population.add(temp);
			}				
		}
	}
	
	public void calculateFitness() {
		for(int i=0; i<population.size(); i++) {
			population.get(i).calcValue();		
		}	
		
	}
	

	
	public Chromosome getFittest() {
		calculateFitness();
		int fittest=population.get(0).getFitness();
		int fittestIndex =0;
		for(int i=0; i<population.size(); i++) {
			if(population.get(i).getFitness()> fittest) {
				fittest=population.get(i).getFitness();
				fittestIndex=i;
			} 
		}
		return population.get(fittestIndex);
	}
	public Chromosome getLestFittest() {
		calculateFitness();
		int lFittest=population.get(0).getFitness();
		int lFittestIndex =0;
		for(int i=0; i<population.size(); i++) {
			if(population.get(i).getFitness()< lFittest) {
				lFittest=population.get(i).getFitness();
				lFittestIndex=i;
			} 
		}
		return population.get(lFittestIndex);
	}
	
	public Chromosome getSecondFittest() {
		calculateFitness();
		int fittestIndex=0;
		int fittest2Index =0;
		for(int i=0; i<population.size(); i++) {
			if(population.get(i).getFitness()> population.get(fittestIndex).getFitness()) {
				fittest2Index=fittestIndex;
				fittestIndex=i;
				
			}else if(population.get(i).getFitness()> population.get(fittest2Index).getFitness()) {
				fittest2Index=i;
				
			} 
		}
		return population.get(fittestIndex);
	}
	
	
	public void addChromosome(Chromosome x) {
		x.calcWeight();
		x.getWeight();
		if(x.getWeight()<=capacity) {
			population.add(x);
		}
	}
	
	public void removeChromosome(Chromosome y) {
		population.remove(y);
	}
	public int size() {
		return population.size();
	}
	public void clear() {
		population.clear();
	}
	public void addALL(Population p) {
		for(int i=0; i<p.size(); i++) {
			population.add(p.get(i));
		}
		
	}
	public Chromosome get(int i) {
		return population.get(i);
		
	}
	
	

}
