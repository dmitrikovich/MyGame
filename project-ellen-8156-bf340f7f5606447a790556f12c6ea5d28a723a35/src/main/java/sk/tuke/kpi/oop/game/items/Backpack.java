package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private ArrayList<Collectible> collectibles;
    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        collectibles = new ArrayList<>(capacity);
    }

//    public void add(Scene scene, Collectible collectible) {
//
//    }



    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(collectibles);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return collectibles.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if(getSize() == getCapacity()){
            throw new IllegalStateException(String.format("%s is full!", name));
        }
        collectibles.add(actor);
        System.out.println("Actor was added");
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if(getSize() == 0) return;
        for(int i = 0; i < collectibles.size(); i++){
            if(collectibles.get(i) == actor){
                collectibles.remove(i);
                System.out.println("Actor was removed");
                break;
            }
        }
    }


    @Override
    public @Nullable Collectible peek() {
//        Actor actor;
//        for(int i = 0; i < collectibles.size(); i++){
//            return (collectibles.get(i));
//        }
        if(getSize() == 0) return null;
        return collectibles.get(collectibles.size()-1);
    }

    @Override
    public void shift() {
        Collectible first = collectibles.remove(0);
        collectibles.add(first);

    }

    @Override
    public @NotNull Iterator<Collectible> iterator() {
        return collectibles.iterator();
    }
}
