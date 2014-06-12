package de.malikatalla.ling;

import java.util.LinkedList;
import java.util.List;

public class OverallStatistics {

  List<GameStatistics> stats = new LinkedList<GameStatistics>();
  
  OverallStatistics(){
    
  }
  
  public void add(GameStatistics s){
    stats.add(s);
  }
  
}
