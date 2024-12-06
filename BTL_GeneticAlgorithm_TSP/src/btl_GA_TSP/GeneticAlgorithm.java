package btl_GA_TSP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
	 private static final double mutationRate = 0.015;
	    private static final int tournamentSize = 5;
	    private static final boolean elitism = true;

	    public static Population evolve(Population pop) {
	        Population newPop = new Population(0, new ArrayList<>());
	        int elitismOffset = elitism ? 1 : 0;
	        if (elitism) {
	            newPop.tours.add(pop.getFittest());
	        }

	        for (int i = elitismOffset; i < pop.tours.size(); i++) {
	            Tour parent1 = tournamentSelection(pop);
	            Tour parent2 = tournamentSelection(pop);
	            Tour child = crossover(parent1, parent2);
	            newPop.tours.add(child);
	        }

	        for (int i = elitismOffset; i < newPop.tours.size(); i++) {
	            mutate(newPop.tours.get(i));
	        }

	        return newPop;
	    }

	    private static Tour crossover(Tour parent1, Tour parent2) {
	        ArrayList<City> childCities = new ArrayList<>(Collections.nCopies(parent1.cities.size(), null));
	        Random rand = new Random();
	        int startPos = rand.nextInt(parent1.cities.size());
	        int endPos = rand.nextInt(parent1.cities.size());

	        for (int i = Math.min(startPos, endPos); i <= Math.max(startPos, endPos); i++) {
	            childCities.set(i, parent1.cities.get(i));
	        }

	        for (City city : parent2.cities) {
	            if (!childCities.contains(city)) {
	                for (int i = 0; i < childCities.size(); i++) {
	                    if (childCities.get(i) == null) {
	                        childCities.set(i, city);
	                        break;
	                    }
	                }
	            }
	        }

	        Tour child = new Tour(childCities);
	        return child;
	    }

	    private static void mutate(Tour tour) {
	        Random rand = new Random();
	        for (int i = 0; i < tour.cities.size(); i++) {
	            if (rand.nextDouble() < mutationRate) {
	                int j = rand.nextInt(tour.cities.size());
	                Collections.swap(tour.cities, i, j);
	            }
	        }
	    }

	    private static Tour tournamentSelection(Population pop) {
	        Population tournament = new Population(0, new ArrayList<>());
	        Random rand = new Random();
	        for (int i = 0; i < tournamentSize; i++) {
	            tournament.tours.add(pop.tours.get(rand.nextInt(pop.tours.size())));
	        }
	        return tournament.getFittest();
	    }

}
