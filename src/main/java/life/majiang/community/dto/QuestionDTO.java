package life.majiang.community.dto;

import life.majiang.community.model.User;
import org.springframework.stereotype.Component;

/**
 * 用于传输的数据模型，和数据表中的数据有点不一样。因此才要封装成DTO
 * 包含question表的全部字段，额外又增加了User数据模型
 */
@Component
public class QuestionDTO
{
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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

    public Integer getCreator()
    {
        return creator;
    }

    public void setCreator(Integer creator)
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
