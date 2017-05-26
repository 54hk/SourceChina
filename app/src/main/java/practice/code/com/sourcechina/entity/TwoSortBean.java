package practice.code.com.sourcechina.entity;

import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

@Root(name = "oschina")
public class TwoSortBean {


    private String softwarecount;
    private List<SoftwareTypeBean> softwareTypes;
private String notice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getSoftwarecount() {
        return softwarecount;
    }

    public void setSoftwarecount(String softwarecount) {
        this.softwarecount = softwarecount;
    }

    public List<SoftwareTypeBean> getSoftwareTypes() {
        return softwareTypes;
    }

    public void setSoftwareTypes(List<SoftwareTypeBean> softwareTypes) {
        this.softwareTypes = softwareTypes;
    }

    @Root(name = "softwareType")
    public static class SoftwareTypeBean {
        private String name;
        private String tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
