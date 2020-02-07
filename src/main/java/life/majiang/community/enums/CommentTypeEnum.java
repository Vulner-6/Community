package life.majiang.community.enums;



public enum  CommentTypeEnum
{
    //在enum中添加自己定义的方法，逗号隔开，分号结尾。
    QUESTION(1),
    COMMENT(2);

    private Integer type;
    //构造方法
    CommentTypeEnum(Integer type)
    {
        this.type=type;
    }
}
