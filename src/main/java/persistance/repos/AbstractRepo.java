package persistance.repos;

public interface AbstractRepo<T> {
    public abstract void AbstractAdd(T entity);//c

    public abstract int AbstractGetId(String s);//r

    public abstract void AbstractUpdate(T entity);//u
}
