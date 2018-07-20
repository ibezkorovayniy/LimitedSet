package main.java.LimitedSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomSet<T> implements LimitedSet<T>, Iterable<T> {

    private final int MAX_SIZE = 10;
    private Integer count;
    private Map<T, Integer> map;

    public CustomSet() {
        map = new HashMap<>();
        this.count = 0;
    }

    @Override
    public void add(T t) {
        if (map.size() < MAX_SIZE) {
            map.put(t, count);
        } else {
            final int value = map.keySet().stream()
                    .mapToInt(e -> map.get(e))
                    .min()
                    .getAsInt();
            List<T> tmpList = map.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(entry.getValue(), value))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toCollection(ArrayList::new));
            map.remove(tmpList.get(0));
            map.put(t, count);
        }
    }

    @Override
    public boolean remove(T t) {

        return map.remove(t) != null;
    }

    @Override
    public boolean contains(T t) {
        Iterator<T> it = iterator();
        if (t == null) {
            return false;
        } else {
            while (it.hasNext())
                if (t.equals(it.next())) {
                    int tmp = map.get(t);
                    ++tmp;
                    map.put(t, tmp);
                    return true;
                }
        }
        return false;
    }

    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }

    public String toString() {
        return map.toString();
    }

}

