import java.util.Arrays;
/**
 *This class allows simulation of evolution with some simple parametres
 *<br>The Population is represented by an array of individuals, which have
 * the same variable chromosome length and unique sequence of allels, represented
 * by 0 and 1.
 *
 * @author Luis Kress
 * @version 0.1
 */
public class Population {

    private Individual[] population;
    /**
     * Initializes an Population. Each individual gets its own chromosom,
     * which means its own sequence of 0 and 1. For every position 0 or 1 is chosen
     * randomly by a chance of 50:50.
     * @param chromosomeLength Length of the chromosome (number of bits)
     * @param size Size of the population
     */
    Population(int chromosomeLength, int size){
        population = new Individual[size];
        for(int i = 0; i < size; i++){
            //Neues Individuum erstellen
            this.population[i] = new Individual(chromosomeLength);

            //jedes Bit des Indivuduums zufaellig setzen
            for(int j = 0; j < chromosomeLength; j++){
                int bit = (int)(Math.random()*2);
                this.population[i].setBit(j, bit);
            }
        }
    }

    /**
     * Simulates evolution of the previous initialized population, where
     * one generation represents the mating of two parents, which are chosen
     * randomly out of the population. The arisen child replaces the weakest
     * individual. Strength is defined by the number of 1 on the chromosome.
     * @param generation
     * @param mutationsfaktor
     */
    public void evolution(int generation, int mutationsfaktor){
        //Schleife fuer Anzahl Paarungen
        for(int j = 0; j < generation; j++) {
            //Vater und Mutter aus der Population auwaehlen
            int vater = (int) Math.floor(Math.random() * population.length);
            int mutter = (int) Math.floor(Math.random() * population.length);
            Individual kind = new Individual(population[vater].getLength());

            //fuer jedes Bit im Chromosom Eltern vergleichen
            for (int i = 0; i < kind.getLength(); i++) {
                //mit 50:50 chance bit von Vater oder Mutter verwenden
                int bit = (int) Math.random() * 2 == 0 ? population[vater].getBit(i) : population[mutter].getBit(i);
                //mit mutationsrate Bit mutieren lassen
                int mutation = (int) Math.floor(Math.random() * mutationsfaktor);
                //falls Mutation stattgefunden hat bit mutieren
                bit = mutation == 0 ? (bit == 0 ? 1 : 0) : bit;
                //bit in kind einsetzen
                kind.setBit(i, bit);
            }
            //schwaechstes Populationsmitglied durch Kind ersetzen
            int weak = findWeakest();
            population[weak] = kind;
        }
    }

    /**
     * Returns the index of the weakest individual in the population
     * @return Index of weakest individual
     */
    public int findWeakest(){
        int position = -1;
        int[] weakestChromosome = new int[population[0].getLength()];
        Arrays.fill(weakestChromosome, 1);
        Individual weakest = new Individual(weakestChromosome);

        for(int i = 0; i < population.length; i++) {
            position = this.population[i].getStrength() < weakest.getStrength()? i : position;
            weakest = this.population[i].getStrength() < weakest.getStrength()? this.population[i] : weakest;
        }
        return position;
    }

    /**
     * Prints the population, represented by the sequence of 1 and 0 (chromosome)
     */
    public void printPopulution(){
        for (int i = 0; i < this.population.length; i++)
            this.population[i].print();
    }

    /**
     * Returns the strength of the population, represented by the total number of
     * 1 in the genepool
     * @return total number of 1 in genepool
     */
    public int populationStrength(){
        int strength = 0;
        for (int i = 0; i < this.population.length; i++)
            strength+=population[i].getStrength();
        return strength;
    }
}
