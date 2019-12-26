package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
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
    @Autowired
    private PaginationDTO paginationDTO;

    /**
     * index页面的分页列表
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size)
    {
        //size*(page-1)，设置每次首页展示的问题数量
        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question question:questions)
        {
            //根据提交问题的创建者，找对应的User对象
            User user =userMapper.findById(question.getCreatorId());
            QuestionDTO questionDTO=new QuestionDTO();
            //将提交的问题所有的属性，挨个拷贝到questionDTO中
            BeanUtils.copyProperties(question,questionDTO);
            //设置之前封装的questionDTO中设置的user属性
            if(user!=null)
            {
                questionDTO.setUser(user);
            }
            else
            {
                System.out.println("user is null!");
            }
            questionDTOList.add(questionDTO);
        }

        //配置paginationDTO的数据值
        paginationDTO.setQuestionDTOList(questionDTOList);
        Integer totalCount=questionMapper.count();
        Integer tempTotalPages;
        if(totalCount%5==0)
        {
            tempTotalPages =totalCount/5;
        }
        else
        {
            tempTotalPages =totalCount/5+1;
        }

        //根据当前页，设置分页显示数组的中的值
        List<Integer> pagesList=new ArrayList<Integer>();
        List<String> pageUrlList=new ArrayList<String>();
        for(int i = page; i<= page+5; i++)
        {
            //防止分页最后一排，超出最大页数做的判断
            if(i<=tempTotalPages)
            {
                pagesList.add(i);
                String temp="/?page="+i+"";
                pageUrlList.add(temp);
            }
        }
        paginationDTO.setPages(pagesList);
        paginationDTO.setPageUrlList(pageUrlList);
        paginationDTO.setPagination(totalCount,page,size,"/?page=");    //一些对对象赋值的操作，尽量都封装在对象内部
        return paginationDTO;
    }

    public PaginationDTO myQuestionsList(User user,Integer page,Integer size)
    {

        //利用公式，算出每页显示的问题范围
        Integer offset=size*(page-1);
        List<Question> questionList=questionMapper.myQuestionsList(user.getAccountId(),offset,size);
        //封装questionDTO，并组装成列表
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question question:questionList)
        {
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        //以下内容都是进行分页的组装
        paginationDTO.setQuestionDTOList(questionDTOList);
        Integer totalPage=0;
        Integer myQuestionCount=questionMapper.myQuestionsCount(user.getAccountId());
        //判断我的问题的总页数
        if(myQuestionCount%5==0)
        {
            totalPage=myQuestionCount/5;
        }
        else if (myQuestionCount%5>0)
        {
            totalPage=myQuestionCount/5+1;
        }
        //设置分页列表总数
        List<Integer> pagesList=new ArrayList<Integer>();
        List<String> pageUrlList=new ArrayList<String>();
        for(int i = page; i<= page+5; i++)
        {
            //防止分页最后一排，超出最大页数做的判断
            if(i<=totalPage)
            {
                pagesList.add(i);
                String temp="/profile/questions?page="+i+"";
                pageUrlList.add(temp);
            }
        }
        paginationDTO.setPages(pagesList);
        paginationDTO.setPagination(myQuestionCount,page,size,"/profile/questions?page=");

        return paginationDTO;
    }
}
