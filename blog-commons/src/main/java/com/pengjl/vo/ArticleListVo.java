package com.pengjl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ArticleListVo {

    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //访问量
    @Accessors(chain = true)
    private Long viewCount;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

}
