package persistance.repos;

import exceptions.ElementNotFoundException;

public interface AbstractRepo<T> {
    public abstract void AbstractAdd(T entity);//c

    public abstract int AbstractGetId(String s);//r

    public abstract void AbstractUpdate(T entity);//u

    public abstract void AbstractDelete(int id);//d
}
