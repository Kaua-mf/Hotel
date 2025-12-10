
package DAO;

import java.util.List;

public interface InterfaceDAO<T> {
    void Create(T var1);

    T Retrieve(int var1);

    List<T> Retrieve(String var1, String var2);

    void Update(T var1);

    void Delete(T var1);
}
