package btl_GA_TSP;

import java.util.ArrayList;

public class run {
	public static void main(String[] args) {
		ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(60, 200));
        cities.add(new City(180, 200));
        cities.add(new City(80, 180));
        cities.add(new City(140, 180));
        cities.add(new City(20, 160));
        cities.add(new City(100, 160));
        cities.add(new City(200, 160));
        cities.add(new City(140, 140));
        cities.add(new City(40, 120));
        cities.add(new City(100, 120));

        Population pop = new Population(50, cities);
        int generations = 100;

        for (int i = 0; i < generations; i++) {
            pop = GeneticAlgorithm.evolve(pop);
        }

        System.out.println("Best distance: " + pop.getFittest().getDistance());
        System.out.print("Best tour: ");
        pop.getFittest().printTour();
	}
}
