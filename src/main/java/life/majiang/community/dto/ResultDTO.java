package life.majiang.community.dto;

/**
 * 返回状态码和提示信息
 */
public class ResultDTO
{
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message)
    {
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    //getter and setter

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
