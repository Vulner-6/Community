package life.majiang.community.service;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 当需要组装不同的数据模型时，就需要service层。
 * 比如，这里需要组装QuestionMapper和UserMapper相关的操作时，就需要再额外封装一个service类。
 */
@Component
@Service
public class QuestionService
{
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list()
    {
        List<Question> questions=questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question question:questions)
        {
            //根据提交问题的创建者，找对应的User对象
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //将提交的问题所有的属性，挨个拷贝到questionDTO中
            BeanUtils.copyProperties(question,questionDTO);
            //设置之前封装的questionDTO中设置的user属性
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
