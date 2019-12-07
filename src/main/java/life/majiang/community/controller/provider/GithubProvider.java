package life.majiang.community.controller.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.controller.dto.AccessTokenDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import okhttp3.MediaType.Companion.toMediaTypeOrNull;
import okhttp3.RequestBody.Companion.asRequestBody;

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
         MediaType mediaType = MediaType.Companion.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.Companion.create(mediaType, JSON.toJSONString(accessTokenDTO));
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
