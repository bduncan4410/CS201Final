/**
 * The Repository interface defines the API for all repository objects
 * These objects must use EntityBase as their parent class
 */
import java.util.Collection;

public interface Repository<T extends EntityBase>
{
	public int count();
	public T get(int ID);
	public T getFirst();
	public Collection<T> getAll();
	public Collection<T> getCollection(String sql);
	public Collection<T> search(String term);
	public int add(T entity);
	public boolean update(T entity);
	public boolean delete(T entity);
	public int addCollection(Collection<T> addList);
	public int updateCollection(Collection<T> updateList);
	public int deleteCollection(Collection<T> deleteList);
}
