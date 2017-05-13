package practice.code.com.sourcechina.entity;

import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

@Root(name = "oschina")
public class WIFIMessageBean {


    private String catalog;
    private String pagesize;
    private String newsCount;
    private NoticeBean notice;
    private List<NewsBean> newslist;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }

    @Root(name = "notice")
    public static class NoticeBean {
        private String atmeCount;
        private String msgCount;
        private String reviewCount;
        private String newFansCount;
        private String newLikeCount;

        public String getAtmeCount() {
            return atmeCount;
        }

        public void setAtmeCount(String atmeCount) {
            this.atmeCount = atmeCount;
        }

        public String getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(String msgCount) {
            this.msgCount = msgCount;
        }

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getNewFansCount() {
            return newFansCount;
        }

        public void setNewFansCount(String newFansCount) {
            this.newFansCount = newFansCount;
        }

        public String getNewLikeCount() {
            return newLikeCount;
        }

        public void setNewLikeCount(String newLikeCount) {
            this.newLikeCount = newLikeCount;
        }
    }

    @Root(name = "news")
    public static class NewsBean {
        private String id;
        private String title;
        private String body;
        private String commentCount;
        private String author;
        private String authorid;
        private String pubDate;
        private String url;
        private NewstypeBean newstype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
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

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public NewstypeBean getNewstype() {
            return newstype;
        }

        public void setNewstype(NewstypeBean newstype) {
            this.newstype = newstype;
        }

        @Root(name = "newstype")
        public static class NewstypeBean {
            private String type;
            private String authoruid2;
            private String eventurl;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAuthoruid2() {
                return authoruid2;
            }

            public void setAuthoruid2(String authoruid2) {
                this.authoruid2 = authoruid2;
            }

            public String getEventurl() {
                return eventurl;
            }

            public void setEventurl(String eventurl) {
                this.eventurl = eventurl;
            }
        }
    }
}
