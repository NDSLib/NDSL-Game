package com.ndsl.bun133.util;

import com.ndsl.bun133.game.GameMain;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EqualMap<T,S> {
    public EqualMap(){
    }

    public List<MultiSet<T,S>> t_s_list=new ArrayList<>();

    public void put(T t_value,S s_value){
        if(containsKey(t_value)){
            t_s_list.remove(new MultiSet<T,S>(t_value,get(t_value)));
        }
        t_s_list.add(new MultiSet<T,S>(t_value,s_value));
    }

    @Nullable
    public S get(T key){
        for(MultiSet<T,S> set:t_s_list){
            if(set.t_value.equals(key)){
                return set.s_value;
            }
        }
        GameMain.logger.warn("[EqualMap]NotFoundKey");
        return null;
    }

    @NotNull
    public S getOrDefault(T key,S Default_Value){
        S result=get(key);
        if(result==null){
            return Default_Value;
        }else{
            return result;
        }
    }

    public void remove(@NotNull T key){
        t_s_list.remove(new MultiSet<T,S>(key,get(key)));
    }

    @NotNull
    public List<MultiSet<T,S>> getValues(){
        return t_s_list;
    }


    public boolean containsKey(T key){
        return get(key)!=null;
    }

    /**
     * @apiNote DO NOT CHANGE STATE!!!
     */
    public Collection<MultiSet<T,S>> keySet(){
        return new Collection<MultiSet<T, S>>() {
            @Override
            public int size() {
                return t_s_list.size();
            }

            @Override
            public boolean isEmpty() {
                return t_s_list.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return t_s_list.contains(o);
            }

            @NotNull
            @Override
            public Iterator<MultiSet<T, S>> iterator() {
                return t_s_list.iterator();
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return t_s_list.toArray();
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return t_s_list.toArray(a);
            }

            @Override
            public boolean add(MultiSet<T, S> tsMultiSet) {
                return t_s_list.add(tsMultiSet);
            }

            @Override
            public boolean remove(Object o) {
                return t_s_list.remove(o);
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return t_s_list.containsAll(c);
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends MultiSet<T, S>> c) {
                return t_s_list.addAll(c);
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                return t_s_list.removeAll(c);
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                return t_s_list.retainAll(c);
            }

            @Override
            public void clear() {
                t_s_list.clear();
            }
        };
    }
}
