package life.majiang.community.controller.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.controller.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

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
            return response.body().string();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
