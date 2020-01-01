package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;
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

    //根据id更新指定记录(如果传入的是自定义的持久层数据类型，那么可以直接传入成员变量，mybatis框架可以识别)
    @Update("UPDATE question SET title=#{title},gmt_modified=#{gmtModified},description=#{description},tag=#{tag} " +
            "WHERE " +
            "id=#{id}")
    Boolean update(Question question);

}
