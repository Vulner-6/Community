package life.majiang.community.model;

import org.springframework.stereotype.Component;

/**
 * 对应的数据库中question数据表的数据模型对象
 */
@Component
public class Question
{
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private String creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Integer creatorId;

    public Integer getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId)
    {
        this.creatorId = creatorId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
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

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Integer getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(Integer viewCount)
    {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount)
    {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount()
    {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount)
    {
        this.likeCount = likeCount;
    }

    @Override
    public String toString()
    {
        return "Question{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", tag='" + tag + '\'' + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", creator=" + creator + ", viewCount=" + viewCount + ", commentCount=" + commentCount + ", likeCount=" + likeCount + '}';
    }
}
