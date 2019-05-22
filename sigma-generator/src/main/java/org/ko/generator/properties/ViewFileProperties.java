package org.ko.generator.properties;

public class ViewFileProperties {

    /**
     * 是否生成前台代码
     */
    private boolean enable = true;

    /**
     * 前台生成目录
     */
    private String path = "D://view";

    /**
     * js file path.
     */
    private String js = "js";

    /**
     * html file path.
     */
    private String html = "module";

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
