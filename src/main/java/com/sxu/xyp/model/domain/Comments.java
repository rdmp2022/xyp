package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论表
 * @TableName comments
 */
@TableName(value ="comments")
public class Comments implements Serializable {
    /**
     * 评论ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 发表用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 帖子ID
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 评论时间
     */
    @TableField(value = "comment_date")
    private Date commentDate;

    /**
     * 评论内容
     */
    @TableField(value = "comment_content")
    private String commentContent;

    /**
     * 父评论ID
     */
    @TableField(value = "parent_comment_id")
    private Long parentCommentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 评论ID
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
     * 评论时间
     */
    public Date getCommentDate() {
        return commentDate;
    }

    /**
     * 评论时间
     */
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 父评论ID
     */
    public Long getParentCommentId() {
        return parentCommentId;
    }

    /**
     * 父评论ID
     */
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}