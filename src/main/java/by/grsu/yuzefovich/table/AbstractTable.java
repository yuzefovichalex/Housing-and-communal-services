package by.grsu.yuzefovich.table;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractTable<E> implements Serializable {

	public abstract List<E> getRows();

	public abstract void setRows(final List<E> rows);
}
