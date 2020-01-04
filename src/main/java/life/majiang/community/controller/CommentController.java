package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController
{
    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO)   //为了将前端传过来的json自动赋值到CommentDTO对象中
    {
        //将前端传过来的json赋值给CommentDTO对象，再将CommentDTO对象中的成员属性赋值给Comment模型
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setCommentType(commentDTO.getCommentType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1);   //先测试一下
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        //由于加了@ResponseBody的注解，所以如果返回对象，框架就会自动将对象转换成utf8格式的json数据，如果返回字符串，则会时iso编码的字符串（可能导致乱码）
        Map<Object,Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message","成功");
        return objectObjectHashMap;
    }
}
