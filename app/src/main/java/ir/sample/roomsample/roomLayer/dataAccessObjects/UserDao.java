package ir.sample.roomsample.roomLayer.dataAccessObjects;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

@Dao
public interface UserDao {

    @Query("select * from users")
    public List<UserEntity> getAll();

    @Query("select * from users where id = :selectedId")
    public UserEntity getById(int selectedId);

    @Insert
    public void insert(UserEntity user);

    @Update
    public void update(UserEntity user);

    @Delete
    public void delete(UserEntity user);

    @Query("delete from users")
    public void deleteAll();

}
