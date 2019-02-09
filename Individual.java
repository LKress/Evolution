import java.util.Arrays;

public class Individual {
    int[] chromosom;
    int strength;
    int chromosomLength;

    Individual(int chromosomLength){
        this(new int[chromosomLength]);
    }

    Individual(int[] parent){
        chromosomLength = parent.length;
        chromosom = Arrays.copyOf(parent, chromosomLength);
    }

    Individual(int chromosomLength, int strength){
        this(chromosomLength);
        this.strength = strength;
    }

    public void setBit(int position, int oneZero){
        chromosom[position] = oneZero;
    }

    public void print(){
        for(int i = 0; i < 10; i++)
            System.out.print(chromosom[i]);
        System.out.println();
    }

    public int getBit(int position){
        return chromosom[position];
    }
}
