package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * mybytes框架的用法，这个类里面的所有方法，都是操作user表的
 */
@Component
@Mapper
public interface UserMapper
{
    @Insert("INSERT INTO user (name,account_id,token,gmt_create,gmt_modified,avatar_url) VALUES (#{name}," +
            "#{accountId},#{token}," +
            "#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token") String token);//由于这里的token，是字符串，不是类，所以需要额外加个@Param注解
}
