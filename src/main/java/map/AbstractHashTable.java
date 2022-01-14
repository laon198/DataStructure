package map;

import java.util.Random;

public abstract class AbstractHashTable<K,V> extends AbstractMap<K,V> {
    protected int capacity;
    protected int size;
    private int prime;
    private long scale;
    private long shift;

    public AbstractHashTable(int capacity, int prime){
        this.prime = prime;
        this.capacity = capacity;
        Random rand = new Random();
        scale = rand.nextInt(prime-1)+1;
        shift = rand.nextInt(prime);
        createTable();
    }

    public AbstractHashTable(int capacity){
        this(capacity, 109345121);
    }

    public AbstractHashTable(){
        this(17);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void createTable();

    protected int hashing(K key){
        return (int)(
                Math.abs(
                    key.hashCode()*scale+shift
                        )%prime
                )%capacity;
    }
}
