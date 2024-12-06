package btl_GA_TSP;

import java.util.ArrayList;

public class Population {
	    ArrayList<Tour> tours = new ArrayList<>();

	    public Population(int populationSize, ArrayList<City> cities) {
	        for (int i = 0; i < populationSize; i++) {
	            tours.add(new Tour(cities));
	        }
	    }

	    public Tour getFittest() {
	        return tours.stream().max((t1, t2) -> Double.compare(t1.getFitness(), t2.getFitness())).get();
	    }
}
