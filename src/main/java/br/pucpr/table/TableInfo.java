package br.pucpr.table;

import br.pucpr.table.model.TableData;
import br.pucpr.table.observer.Observer;
import br.pucpr.table.observer.Subject;
import java.util.Objects;

public final class TableInfo implements Observer {
  private final TableData data;

  public TableInfo(TableData data) {
    this.data = Objects.requireNonNull(data, "Data cannot be null");
    if (data instanceof Subject subject) {
      subject.attach(this);
    }
  }

  @Override
  public void update() {
    System.out.printf("linhas: %d | colunas: %d%n", data.rowCount(), data.colCount());
  }
}
