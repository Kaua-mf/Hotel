package DAO;

import java.util.List;

public interface InterfaceDAO<T> {
    
    public void Create(T objeto);
    
    public List<T> Retrieve();
    
    public T Retrieve(int id);
    
    public List<T> Retrieve(String campo, String valor);
    
    public void Update(T objeto);
    
    public void Delete(T objeto);
}