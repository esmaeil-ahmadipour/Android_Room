package ir.sample.roomsample.roomLayer.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "user_name")
    public String name;
    @ColumnInfo(name = "user_email")
    public String email;
    @ColumnInfo(name = "user_number")
    public String number;

    public UserEntity() {
    }

    public UserEntity(String name, String email, String number) {
        this.name = name;
        this.email=(email==null) ? "":email;
        this.number = number;
    }
    public UserEntity(int id, String name, String email, String number) {
        this.id = id;
        this.name = name;
        this.email = (email==null) ? "":email;
        this.number = number;
    }
}
