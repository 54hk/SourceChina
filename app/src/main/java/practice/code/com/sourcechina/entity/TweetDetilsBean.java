package practice.code.com.sourcechina.entity;

import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

@Root(name = "oschina")
public class TweetDetilsBean {


    private TweetBean tweet;
    private String notice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public TweetBean getTweet() {
        return tweet;
    }

    public void setTweet(TweetBean tweet) {
        this.tweet = tweet;
    }

    @Root(name = "tweet")
    public static class TweetBean {
        private String id;
        private String body;
        private String author;
        private String authorid;
        private String appclient;
        private String commentCount;
        private String portrait;
        private String pubDate;
        private String imgSmall;
        private String imgBig;
        private String attach;
        private String likeCount;
        private String isLike;
        private List<UserBean> likeList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getAppclient() {
            return appclient;
        }

        public void setAppclient(String appclient) {
            this.appclient = appclient;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getImgSmall() {
            return imgSmall;
        }

        public void setImgSmall(String imgSmall) {
            this.imgSmall = imgSmall;
        }

        public String getImgBig() {
            return imgBig;
        }

        public void setImgBig(String imgBig) {
            this.imgBig = imgBig;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getIsLike() {
            return isLike;
        }

        public void setIsLike(String isLike) {
            this.isLike = isLike;
        }

        public List<UserBean> getLikeList() {
            return likeList;
        }

        public void setLikeList(List<UserBean> likeList) {
            this.likeList = likeList;
        }

        @Root(name = "user")
        public static class UserBean {
            private String name;
            private String uid;
            private String portrait;

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
        }
    }
}
