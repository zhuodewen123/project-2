package com.zhuodewen.controller;

import com.github.pagehelper.PageInfo;
import com.zhuodewen.domain.Student;
import com.zhuodewen.facade.StudentFacade;
import com.zhuodewen.util.JSONResult;
import com.zhuodewen.util.UploadUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentFacade studentFacade;

    @Value("${uploadpath}")
    private String uploadpath;


    /**
     * 根据id查询学生对象
     * @param id
     * @return
     */
    @RequestMapping(value="selectById",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id查询学生对象")
    public JSONResult selectById(@ApiParam(name="id",value="学生id",required=true)@RequestParam(value = "id")int  id){
        JSONResult jsonResult=new JSONResult();
        Student student=studentFacade.selectByPrimaryKey(id);
        return jsonResult;
    }

    /**
     * 分页查询学生列表1(PageInfo返回)
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询学生列表")
    public PageInfo list(Student student){
        PageInfo query=studentFacade.query(student);
        return query;
    }

    /**
     * 进入学生页面(测试)
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * poi导出学生列表
     * @param response
     * @throws Exception
     */
    @RequestMapping("exportXls")
    @ApiOperation("poi导出学生列表")
    public void exportXls(HttpServletResponse response) throws Exception {
        //1.设置文件头信息
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");
        //2.1.创建一个.xls文件
        Workbook wb = new HSSFWorkbook();
        //2.2创建工作簿sheet
        Sheet sheet = wb.createSheet("sheet0");
        //2.3创建一行
        Row row = sheet.createRow(0);
        //2.4创建单元格,并设置单元格内容
        row.createCell(0).setCellValue("名称");
        row.createCell(1).setCellValue("邮箱");
        row.createCell(2).setCellValue("年龄");
        //3.1查询要导出的数据
        List<Student> list = studentFacade.selectAll();
        //3.2循环写入要导出的数据
        for (int i = 0; i <list.size() ; i++) {
            Student student=list.get(i);
            //3.2.1行
            row = sheet.createRow(i+1);
            //3.2.2列
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getEmail());
            row.createCell(2).setCellValue(student.getAge());
        }
        //4.输出到响应流
        wb.write(response.getOutputStream());
    }

    /**
     * 文件上传的方法(图片)
     * @param photo
     * @return
     */
    @RequestMapping("/uploadPhoto")
    @ResponseBody
    public  String uploadPhoto(MultipartFile photo){
        //使用工具类返回一个文件地址
        return UploadUtil.upload(photo,uploadpath);
    }


}
