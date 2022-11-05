package com.pengjl.utils;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 通用正常状态
     */
    public static final String STATUS_NORMAL = "0";

    /**
     *评论类型为文章评论
     */
    public static final int COMMENT_ARTICLE = 0;

    /**
     * 评论类型为友情链接评论
     */
    public static final int COMMENT_LINK = 1;
    /**
     *前台登录信息redis缓存前缀
     */
    public static final String FRONT_LOGIN_REDIS_PREFIX = "bloglogin:";

    public static final String ARTICLE_VIEWCOUNT_REDIS_PREFIX = "article:viewCount";
}