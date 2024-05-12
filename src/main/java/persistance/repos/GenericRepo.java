package persistance.repos;


interface GenericRepo <T>{
    public void add(T entity);//c

    public T get(int id);//r

    public void update(T entity);//u

    public void delete(int id);//d
}
