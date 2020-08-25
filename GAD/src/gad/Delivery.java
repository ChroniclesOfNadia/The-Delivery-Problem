package gad;

import java.util.*;
import java.io.*;

public class Delivery {
	
	public static void main(String [] args) throws IOException{
		
		int exampleNo= 0;
		Scanner fileScan = new Scanner(System.in);
		Scanner can = new Scanner(System.in);
		System.out.println("Enter max number of generations:");
		int max = can.nextInt();
		System.out.println("Enter file path:");
		//File file = new File(fileScan.nextLine());
		File file = new File("/home/beanbot/new-workspace/GAD/src/gad/input.txt");
		
		Scanner sc = new Scanner (file);
		
		
			while(sc.hasNext()) { 
				if (sc.next().startsWith("***")) {
					exampleNo++;
					System.out.println("Evaluation of example number: " + exampleNo);
					System.out.println("----------------------------------" );
				}
				int capacity;
				int quota;
				int length;
				capacity= sc.nextInt();
				//System.out.println(capacity);
				quota = sc.nextInt();
				length = sc.nextInt();
				
				char arrName [] = new char[length];
				int arrWeight [] = new int[length];
				int arrValue [] = new int[length];
				
				for(int i=0;i<length; i++) {
					arrName[i]=sc.next().charAt(0);
					arrWeight[i]= sc.nextInt();
					arrValue[i]= sc.nextInt();
	
				}
				Population pop = new Population(capacity, length, arrWeight, arrValue);
				int generation = 0;
				if(length>20) {
					System.out.println("Too many packages, the max 20!! ");
					System.out.println();
				}
				if(pop.size()==0 && length<=20) {
					boolean flag2= false;
					while(flag2==false) {
						generation++;
						pop.initPopulation();
						if(pop.size()>0) {
							flag2=true;
						}
						if(generation==max) {
							System.out.println("No solution sorry :(");
							System.out.println();
							flag2=true;
						}
					}
				}
				if(pop.size()>0 && length<=20) {
					Chromosome x = pop.getFittest();
					x.calcValue();
					int fit= x.getFitness();
					if(fit>quota) {
						System.out.println("An optimal solution has been found!!");
						printSoln(x, arrName ,generation);
					}else {
						boolean flag = false;
						while(flag==false) {
							Population pop2=nextGen(pop, capacity,length, arrWeight, arrValue);
							generation++;
							if(pop2.size()>0) {
								x = pop2.getFittest();
								x.calcValue();
								fit= x.getFitness();
								pop.clear();
								pop.addALL(pop2);
								
							}
							if(fit>=quota) {
								flag=true;
							}
							if(generation>=max) {
								flag=true;
							}
						}
						x.calcWeight();
						int w= x.getWeight();
						if(fit>=quota && w<=capacity ) {
							System.out.println("An optimal solution has been found!!");
							printSoln(x, arrName ,generation);							
						}else if(pop.size()>0 && w<=capacity ){
							System.out.println("Not the best, but the closet we can get is:");
							printSoln(x, arrName ,generation);
							
						}else {
							System.out.println("No available solution :(");
							System.out.println();
							//System.out.println("Generation : "+generation);
						}
					}
					
				}
			
			}//WHILE
		sc.close();	
		fileScan.close();
		can.close();
	}//MAIN
	
	public static Population nextGen(Population pop, int capacity, int length, int arrWeight [], int arrValue []) {
			Population nextgen = new Population(capacity, length, arrWeight, arrValue);
			for(int j=0; j<500; j++) {
				Chromosome parent1= tornamentSelection(pop, capacity, length, arrWeight, arrValue);
				Chromosome parent2 =tornamentSelection(pop, capacity, length, arrWeight, arrValue);
				if(Math.random()<0.9) {
					crossover(parent1, parent2);
				}
				if(Math.random()<0.001) {
					mutate(parent1);
				}
				if(Math.random()<0.001) {
					mutate(parent2);
				}
				parent1.calcWeight();
				parent2.calcWeight();
				if(parent1.getWeight()<=capacity) {
					nextgen.addChromosome(parent1);
				}
				if(parent1.getWeight()<=capacity) {
					nextgen.addChromosome(parent1);
				}			
			
			}//for
			return nextgen;
	}

	public static void printSoln(Chromosome x, char arrName [], int generation) {
		System.out.print("{ ");
		for(int i=0; i<x.geneLength; i++) {			
			if(x.genes[i]==1) {
				System.out.print(arrName[i] + " ");
			}
			
		}
		System.out.print("}");
			
		System.out.println();
		x.calcWeight();
		System.out.println("Total weight: " + x.getWeight());
		x.calcValue();
		System.out.println("Total value: " + x.getValue());
		System.out.println("Generation: "+ generation);
		System.out.println();
	}
	
	
	public static Chromosome tornamentSelection(Population pop, int capacity, int length, int arrWeight [], int arrValue []) {
		
		int k = 300;
		Population temp= new Population(capacity, length, arrWeight, arrValue);
		for(int i=0; i<k; i++) {
			int random = (int)(pop.size()*Math.random());
			temp.addChromosome(pop.get(random));
	
		}
		
		return temp.getFittest();
		
	}
	
	public static void crossover(Chromosome x, Chromosome y) {
		int len= x.geneLength;
		int split =(int)(len*Math.random());
		for(int i=0; i<split; i++) {
			int temp = x.genes[i];
			x.genes[i]=y.genes[i];
			y.genes[i]=temp;
		}
		
	}
	
	public static void mutate(Chromosome z) {
		int len= z.geneLength;
		int swap1 =(int)(len*Math.random());
		int swap2 =(int)(len*Math.random());
		if(swap1==swap2) {
			swap2 =(int)(len*Math.random());
		}
		int temp = z.genes[swap1];
		z.genes[swap1]=z.genes[swap2];
		z.genes[swap1]=temp;

		
	}

	
	

	

	
	
}//CLASS


