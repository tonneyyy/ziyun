package com.hxzy.controller.api;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.vo.api.JobtableVO;
import com.hxzy.vo.api.WorksVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping(value = "/api")
@Controller
public class ApiIndexController {

    //首页学员作品
    @GetMapping(value = "/works")
    @ResponseBody
    public ResponseMessage worksApi(){
        List<WorksVO> worksArr=new ArrayList<WorksVO>();
        Random  rd=new Random();

        String[] types={"平面设计 ","网页设计","电商设计","APP设计","JAVA"};
        for(int i=0;i<types.length;i++){

            WorksVO  worksVO=new WorksVO();
            worksVO.setTypeId(i+1);
            worksVO.setTypeName(types[i]);
            //添加8项目
           List<JobtableVO>  arrVO=new ArrayList<>();
           for(int j=1;j<=8;j++){
               JobtableVO vo=new JobtableVO();
               vo.setId(rd.nextInt(1000000)+100);
               vo.setStudentName("王"+  (char)(rd.nextInt(800)+20000) );
               vo.setMajorName("平面设计");
               vo.setClassesName("UI1940");
               vo.setCover("http://localhost:8080/imgServer/conve.jpg");
               vo.setFans(rd.nextInt(50));
               vo.setLikes(rd.nextInt(80));
               vo.setMessageCount(rd.nextInt(20));
               vo.setLabel(  rd.nextInt(100) %2==0? "原创":"临摹");
               vo.setTitle("平面设计内容"+i);
               vo.setCover("http://localhost:8080/imgServer/xyt_"+(rd.nextInt(8)+1)+".jpg");
               arrVO.add(vo);
           }
           worksVO.setData(arrVO);

           //放到最外面集合
            worksArr.add(worksVO);
        }

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,worksArr);
    }


}
