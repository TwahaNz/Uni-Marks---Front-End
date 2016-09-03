package app.tnz.com.unimarks.repositories;

import java.util.Set;

/**
 * Created by Admin on 2016/08/21.
 */
public interface Repository<Entity, ID> {

    Entity find_by_id(ID id);

    Entity insert(Entity entity);

    int update(Entity entity);

    Entity delete(Entity entity);

    Set<Entity> find_all();

    int delete_all();
}
