package life.majiang.community.dto;

/**
 * 用于接收前端传过来的json格式数据
 */
public class CommentDTO
{
    private Long parentId;
    private String content;
    private Integer commentType;

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getCommentType()
    {
        return commentType;
    }

    public void setCommentType(Integer commentType)
    {
        this.commentType = commentType;
    }
}
