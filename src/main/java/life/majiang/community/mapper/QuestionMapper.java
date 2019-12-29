package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper
{
    //插入问题到数据库中
    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,tag,creator_id) VALUES " +
            "(#{title}," +
            "#{description}," +
            "#{gmtCreate},#{gmtModified},#{creator},#{tag},#{creatorId})")
    void create(Question question);
    //查询数据库中指定范围的问题
    @Select("SELECT * FROM question LIMIT #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    //查询question表中主键的数量，也就是数据行数。
    @Select("SELECT count(1) FROM question")
    Integer count();

    //查询question表中指定用户发布的问题数量
    @Select("SELECT count(1) FROM question WHERE creator=#{creator}")
    Integer myQuestionsCount(@Param("creator") String creator);

    //查询我提交的问题
    @Select("SELECT * FROM question WHERE creator=#{creator} LIMIT #{offset},#{size}")
    List<Question> myQuestionsList(@Param("creator") String creator,@Param("offset") Integer offset,
                                   @Param("size") Integer size);

    //根据问题的ID查询问题
    @Select("SELECT * FROM question WHERE id=#{id}")
    Question getById(@Param("id") Integer id);
}
