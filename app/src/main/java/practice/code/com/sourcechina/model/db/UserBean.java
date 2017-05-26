package practice.code.com.sourcechina.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@DatabaseTable(tableName = "user")
public class UserBean {


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "uid")
    private String uid;
    @DatabaseField(columnName = "location")
    private String location;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "followers")
    private String followers;
    @DatabaseField(columnName = "fans")
    private String fans;
    @DatabaseField(columnName = "score")
    private String score;
    @DatabaseField(columnName = "portrait")
    private String portrait;
    @DatabaseField(columnName = "favoritecount")
    private String favoritecount;
    @DatabaseField(columnName = "gender")
    private String gender;

    public UserBean(){

    }

    public UserBean(int id, String uid, String location, String name, String followers, String fans, String score, String portrait, String favoritecount, String gender) {
        this.id = id;
        this.uid = uid;
        this.location = location;
        this.name = name;
        this.followers = followers;
        this.fans = fans;
        this.score = score;
        this.portrait = portrait;
        this.favoritecount = favoritecount;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getFavoritecount() {
        return favoritecount;
    }

    public void setFavoritecount(String favoritecount) {
        this.favoritecount = favoritecount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
