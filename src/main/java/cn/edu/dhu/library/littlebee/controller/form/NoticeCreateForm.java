package cn.edu.dhu.library.littlebee.controller.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by sherry on 15-11-26.
 */
public class NoticeCreateForm {

    @NotEmpty
    private String title = "";

    private String content = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
