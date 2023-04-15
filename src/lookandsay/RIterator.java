package lookandsay;

import java.util.Iterator;

public interface RIterator<T> extends Iterator<T> {
    public T prev();

    public boolean hasPrevious();
}
