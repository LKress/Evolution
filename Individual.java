import java.util.Arrays;
/**
 *This class allows simulation of evolution with some simple parametres
 *<br>The Individual is represented by a chromosome (int array) where every
 * allele can only have two states (0/1)
 * The individual is also represented by strength which is defined by the number
 * of 1 in the chromosome
 *
 * @author Luis Kress
 * @version 0.1.1
 */
public class Individual {
    private int[] chromosome;
    private int strength;

    /**
     * The Individual can be initialized by only by the chromosome length
     * the chromosome will then be initialized by only 0
     * @param chromosomeLength
     */
    Individual(int chromosomeLength){
        this(new int[chromosomeLength]);
        setStrength(0);
    }

    /**
     * The Individual can be initialized by a given chromosome
     * that makes sense if a Individual has to be copied
     * @param chromosome The chromosome that the
     */
    Individual(int[] chromosome){
        this.chromosome = Arrays.copyOf(chromosome, chromosome.length);
        strength = Arrays.stream(chromosome).sum();
    }


    /**
     * Set one position of the Chromosome. Needed if there should be a mutation
     * or a mating.
     * @param position position of the allele to set
     * @param oneZero value that should be at the given position
     */
    public void setBit(int position, int oneZero){
        chromosome[position] = oneZero;
        strength+= oneZero;
    }

    /**
     * Prints the chromosome of the individual by printing the array
     */
    public void print(){
        for(int i = 0; i < this.getLength(); i++)
            System.out.print(chromosome[i]);
        System.out.println();
    }

    /**
     * Get an allele at a particular position
     * @param position the position where the allele should be returned
     * @return the allele at the given position
     */
    public int getBit(int position){
        return chromosome[position];
    }

    /**
     * Returns the Strength of the individual
     * @return strength of the individual
     */
    public int getStrength(){
        return this.strength;
    }

    /**
     * Set the strength of the individual
     * @param strength the int value the strength should be updated
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Returns the length of the chromosome of the individual instance
     * @return length of chromosome
     */
    public int getLength(){
        return chromosome.length;
    }
}
