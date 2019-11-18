package com.hxzy.entity;

import com.hxzy.vo.TeacherVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * describe:
 *teacher实体
 * @author xxx
 * @date 2019/11/15
 */
@Data
public class Teacher {
    /**
     * 老师ID
     */
    private Integer id;
    /**
     * 教师姓名
     */
    private String name;
    /**
     * 盐
     */
    private String salt;
    /**
     * 密码
     */

    private String password;
    /**
     * 专业
     */
    private List<TeacherVO> teachKnowledge;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 性别
     */
    private String sex;
    /**
     * 性别
     */
    private Date birthday;
    /**
     * 学历：1：本科  2：专科 3：专科以下  4:研究生
     */
    private int education;
    /**
     * 状态:1正常   2：休假    3：离职
     */
    private Integer state;
}
