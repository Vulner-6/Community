package life.majiang.community.dto;

import java.util.List;

/**
 * 返回分页的信息
 */
public class PaginationDTO
{
    //一般后端都是将数据传给前端，然后前端用js写分页。这里是教程弱化了js，才在后端这么写。
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    //显示当前页
    private Integer page;
    //分页显示的数字页码数组
    private List<Integer> pages;

    public List<QuestionDTO> getQuestionDTOList()
    {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList)
    {
        this.questionDTOList = questionDTOList;
    }

    public boolean isShowPrevious()
    {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious)
    {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage()
    {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage)
    {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNext()
    {
        return showNext;
    }

    public void setShowNext(boolean showNext)
    {
        this.showNext = showNext;
    }

    public boolean isShowEndPage()
    {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage)
    {
        this.showEndPage = showEndPage;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public List<Integer> getPages()
    {
        return pages;
    }

    public void setPages(List<Integer> pages)
    {
        this.pages = pages;
    }
}
