package practice.code.com.sourcechina.entity;

import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

@Root(name = "oschina")
public class FindUserBean {


    private List<UserBean> users;
    private String notice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

    @Root(name = "user")
    public static class UserBean {
        private String name;
        private String uid;
        private String portrait;
        private String gender;
        private String from;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }
    }
}
