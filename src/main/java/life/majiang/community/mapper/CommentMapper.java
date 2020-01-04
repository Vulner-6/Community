package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CommentMapper
{
    @Insert("INSERT INTO comment (id,parent_id,comment_type,commentator,gmt_create,gmt_modified,like_count,content," +
            ") VALUES (#{id},#{parentId},#{commentType},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount}," +
            "#{content})")
    void insert(Comment comment);
}
