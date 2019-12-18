package life.majiang.community.model;

import org.springframework.stereotype.Component;

/**
 * 对应的数据库中，user数据表的数据模型对象
 */
@Component
public class User
{
    private int id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
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
}
