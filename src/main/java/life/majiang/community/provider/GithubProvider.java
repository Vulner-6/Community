package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider
{
    /**
     * 获取github访问用户信息的凭证
     * @param accessTokenDTO
     * @return
     *
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO)
    {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute())
        {
            String responseStr=response.body().string();
            String[] split=responseStr.split("&");
            String token=split[0].split("=")[1];
            return token;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用accessToken获取user信息
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseStr = response.body().string();
            GithubUser githubUser=JSON.parseObject(responseStr,GithubUser.class);
            return githubUser;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
