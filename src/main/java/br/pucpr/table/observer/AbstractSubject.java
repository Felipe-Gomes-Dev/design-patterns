package br.pucpr.table.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractSubject implements Subject {
  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void attach(Observer observer) {
    Objects.requireNonNull(observer, "Observer cannot be null");
    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }

  @Override
  public void detach(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (var observer : List.copyOf(observers)) {
      observer.update();
    }
  }
}
