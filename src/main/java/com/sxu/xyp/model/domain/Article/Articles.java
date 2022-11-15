package com.sxu.xyp.model.domain.Article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 帖子表
 * @TableName articles
 */
@TableName(value ="articles")
public class Articles implements Serializable {
    /**
     * 帖子ID
     */
    @TableId(value = "article_id")
    private Long articleId;

    /**
     * 发表用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 摘要
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Long views;

    /**
     * 评论总数
     */
    @TableField(value = "comment_count")
    private Long commentCount;

    /**
     * 收藏总数
     */
    @TableField(value = "favorites_count")
    private Long favoritesCount;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createtime;

    /**
     * 修改时间
     */
    @TableField(value = "updateTime")
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 帖子ID
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 发表用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 发表用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 浏览量
     */
    public Long getViews() {
        return views;
    }

    /**
     * 浏览量
     */
    public void setViews(Long views) {
        this.views = views;
    }

    /**
     * 评论总数
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * 评论总数
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 收藏总数
     */
    public Long getFavoritesCount() {
        return favoritesCount;
    }

    /**
     * 收藏总数
     */
    public void setFavoritesCount(Long favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    /**
     * 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}