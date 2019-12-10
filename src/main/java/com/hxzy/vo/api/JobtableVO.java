package com.hxzy.vo.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 第一项目数据
 */
@Getter
@Setter
public class JobtableVO {
    private Integer id;
    private String studentId;
    private String studentName;
    private String majorName;
    private String classesName;
    //'作品标题'
   private String title;
    //'作品封面图地址'
    private String cover;
    //浏览次数
    private Integer visits=0;
    //点赞次数
    private Integer likes=0;
    //留言次数
    private Integer messageCount=0;
    //原创/临摹
    private String label;
    //作品总数量
    private Integer works=0;
    //粉丝数0
   private Integer fans=0;
   //用户头像
   private String portrait;


}
