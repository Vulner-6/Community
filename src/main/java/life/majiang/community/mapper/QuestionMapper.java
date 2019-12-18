package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper
{
    //插入问题到数据库中
    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,tag) VALUES (#{title}," +
            "#{description}," +
            "#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    //查询数据库中的所有问题
    @Select("SELECT * FROM question")
    List<Question> list();
}
