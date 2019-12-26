package life.majiang.community.dto;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 返回分页的信息
 */
@Component
public class PaginationDTO
{
    //一般后端都是将数据传给前端，然后前端用js写分页。这里是教程弱化了js，才在后端这么写。
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer totalPage;    //这个是数据表中数据总数
    //显示当前页
    private Integer page;
    //分页显示的数字页码数组
    private List<Integer> pages;
    //分页页码跳转的链接
    private List<String> pageUrlList;
    //最后一页的url
    private String endUrl;
    //设置点击下一页，跳转的地址
    private String pageKey;
    private String nextPage;
    //设置点击上一页，跳转的地址
    private String previousPage;

    /**
     * 传入数据条数，设置分页页码、每页展示条数
     * @param totalCount
     * @param page
     * @param size
     */
    public void setPagination(Integer totalCount, Integer page, Integer size,String pageKey)
    {
        this.pageKey=pageKey;
        this.page=page;
        //计算数据总共有多少页
        if(totalCount % size ==0)
        {
            totalPage=totalCount / size;
        }
        else
        {
            totalPage=totalCount / size+1;
        }
        //设置最后一页的url值
        endUrl=this.pageKey+totalPage;
        //先设置下一页的url值，后面的if会纠正
        nextPage=this.pageKey+(page+5);
        //先设置上一页跳转的url值，后面的if会纠正
        previousPage =this.pageKey+(page-5);
        //判断当前页的值，决定如何返回跳转的链接
        if(page>=1&&page<=5)
        {
            previousPage =this.pageKey+1;
        }
        if(page<=totalPage&&page>=totalPage-5)
        {
            nextPage=pageKey+totalPage;
        }
        //判断是否展示“上一页”标签
        if(page==1)
        {
            showPrevious=false;
        }
        else
        {
            showPrevious=true;
        }
        //判断是否显示“下一页”标签
        if(page==totalPage)
        {
            showNext=false;
        }
        else
        {
            showNext=true;
        }
        //判断是否显示“回到第一页”标签
        if(pages.contains(1))
        {
            showFirstPage=false;
        }
        else
        {
            showEndPage=true;
        }
        //判断是否显示“跳转到最后一页”标签
        if(pages.contains(totalPage))
        {
            showEndPage=false;
        }
        else
        {
            showEndPage=true;
        }

    }

    public String getPreviousPage()
    {
        return previousPage;
    }

    public void setPreviousPage(String previousPage)
    {
        this.previousPage = previousPage;
    }

    public String getEndUrl()
    {
        return endUrl;
    }

    public void setEndUrl(String endUrl)
    {
        this.endUrl = endUrl;
    }

    public Integer getTotalPage()
    {
        return totalPage;
    }

    public String getPageKey()
    {
        return pageKey;
    }

    public void setPageKey(String pageKey)
    {
        this.pageKey = pageKey;
    }

    public String getNextPage()
    {
        return nextPage;
    }

    public void setNextPage(String nextPage)
    {
        this.nextPage = nextPage;
    }

    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }

    public List<String> getPageUrlList()
    {
        return pageUrlList;
    }

    public void setPageUrlList(List<String> pageUrlList)
    {
        this.pageUrlList = pageUrlList;
    }

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
