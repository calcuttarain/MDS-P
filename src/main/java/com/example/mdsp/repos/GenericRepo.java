package persistance.repos;


import exceptions.ElementNotFoundException;

interface GenericRepo <T>{
    public void add(T entity);//c

    public T get(int id) throws ElementNotFoundException;//r

    public void update(T entity);//u

    public void delete(int id);//d
}
