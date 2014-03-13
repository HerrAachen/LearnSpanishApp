package de.malikatalla.ling.preparation;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class FrequencyMap<T> {

  private Map<T, AtomicInteger> freq = new HashMap<>();
  
  public int size(){
    return freq.size();
  }
  
  public int getFrequencySum(){
    int sum = 0;
    for(Map.Entry<T, AtomicInteger> entry: freq.entrySet()){
      sum+=entry.getValue().intValue();
    }
    return sum;
  }

  public void increment(T object) {
    AtomicInteger frequency = freq.get(object);
    if (frequency == null) {
      frequency = new AtomicInteger();
      freq.put(object, frequency);
    }
    frequency.incrementAndGet();
  }

  public int getFrequency(T object) {
    AtomicInteger frequency = freq.get(object);
    if (frequency == null) {
      return 0;
    }
    return frequency.intValue();
  }

  public List<Map.Entry<T, AtomicInteger>> getSortedByFrequency() {
    List<Map.Entry<T, AtomicInteger>> result = new LinkedList<>();
    for (Map.Entry<T, AtomicInteger> entry : freq.entrySet()) {
      result.add(entry);
    }
    Collections.sort(result, new Comparator<Map.Entry<T, AtomicInteger>>() {

      @Override
      public int compare(Entry<T, AtomicInteger> o1, Entry<T, AtomicInteger> o2) {
        if (o1.getValue().intValue()<o2.getValue().intValue()){
          return 1;
        }
        return -1;
      }
    });
    return result;
  }

}
