package br.pucpr.table.model;

import java.util.Objects;

public final class PagedTableData implements TableData {
  private final TableData source;
  private final int page;
  private final int pageSize;
  private final long firstRow;

  public PagedTableData(TableData source, int page, int pageSize) {
    this.source = Objects.requireNonNull(source, "Source cannot be null");

    if (page < 0) {
      throw new IllegalArgumentException("Page cannot be negative");
    }
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be positive");
    }

    this.page = page;
    this.pageSize = pageSize;
    this.firstRow = (long) page * pageSize;
  }

  @Override
  public int rowCount() {
    if (firstRow >= source.rowCount()) {
      return 0;
    }

    return (int) Math.min(pageSize, source.rowCount() - firstRow);
  }

  @Override
  public int colCount() {
    return source.colCount();
  }

  @Override
  public String header(int col) {
    return source.header(col);
  }

  @Override
  public String get(int row, int col) {
    if (row < 0 || row >= rowCount()) {
      throw new IndexOutOfBoundsException("Row outside current page: " + row);
    }

    return source.get(Math.toIntExact(firstRow + row), col);
  }

  public int page() {
    return page;
  }

  public int pageSize() {
    return pageSize;
  }

  public int pageCount() {
    return (source.rowCount() + pageSize - 1) / pageSize;
  }
}
