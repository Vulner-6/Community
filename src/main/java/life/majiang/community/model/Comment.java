package life.majiang.community.model;

public class Comment
{
    private Long id;
    private Long parentId;
    private Integer commentType;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Integer getCommentType()
    {
        return commentType;
    }

    public void setCommentType(Integer commentType)
    {
        this.commentType = commentType;
    }

    public Integer getCommentator()
    {
        return commentator;
    }

    public void setCommentator(Integer commentator)
    {
        this.commentator = commentator;
    }

    public Long getGmtCreate()
    {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified()
    {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public Long getLikeCount()
    {
        return likeCount;
    }

    public void setLikeCount(Long likeCount)
    {
        this.likeCount = likeCount;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
