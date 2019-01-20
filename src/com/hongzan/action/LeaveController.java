package com.hongzan.action;

import com.hongzan.entity.Leave;
import com.hongzan.entity.Upload;
import com.hongzan.entity.User;
import com.hongzan.service.LeaveService;
import com.hongzan.service.UploadService;
import com.hongzan.service.UserService;
import com.hongzan.util.CommonUtil;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 请假控制器
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
    //注入对应业务
    @Resource
    private LeaveService leaveService;
    @Resource
    private UserService userService;
    @Resource
    private UploadService uploadService;

    private String leaNo;
    private String leaNo2;


    //设置日志
    private static Logger log = Logger.getLogger(LeaveController.class);

    /**
     * 实例化模型视图时进行初始化值
     * param mm
     */
    @ModelAttribute
    public void initValue(ModelMap mm) {
        mm.put("reason", new String[]{"病假", "事假", "产假", "年假"});
    }

    /**
     * 到请假页面的处理方法
     *
     * @param leave 请假对象
     * @return 模型视图
     */
    @RequestMapping("/toLeave")
    public ModelAndView toLeave(@ModelAttribute("l") Leave leave, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/webpage/leave");
        httpSession.setAttribute("reason", new String[]{"病假", "事假", "产假", "年假"});
        return modelAndView;
    }

    /**
     * 添加请假单
     *
     * @param leave       请假单对象
     *  @parsam saveleave 是否保存
     * @param httpSession 会话对象
     * @return
     */
    @RequestMapping("/addLeave")
    public ModelAndView addLeave(Leave leave, @RequestParam("saveleave") String saveleave, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/webpage/leave");
        //从会话中获取对象
        User user = (User) httpSession.getAttribute("user");
        leave.setPerson(user);
        //获取单号 id
        leave.setId(CommonUtil.getId());
        if (CommonUtil.isNull(this.leaNo)||leaNo.equals(leaNo2)){
            leave.setLeaNo(CommonUtil.getTime());
        }else{
            leave.setLeaNo(this.leaNo);
            leaNo2=leaNo;
        }
        //判断是否需要保存
        if ("yes".equals(saveleave)) {
            leave.setStateCode(3);
        } else {
            leave.setStateCode(2);
        }

        //调用方法添加 可能由于没有对应上级的存在抛出异常
        try {
            leaveService.add(leave);
            Upload upload = new Upload();
            upload.setLeaNo(leave);
            //存放路径
//            upload.setUpath(leave.getLeaNo()+sourceName);
//            uploadService.add(upload);
            modelAndView.setView(new RedirectView("./getPersonInfo"));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/addUpload")
    @ResponseBody
    public String addUpload( @RequestParam("leaNo") String leaNo, @RequestParam("file") MultipartFile file,HttpServletRequest request) {
        String msg="1";
        int returnTag = -1;
        /* 判断是否为空 */
        if (!file.isEmpty()) {
            String sourceName = file.getOriginalFilename(); // 原始文件名
            this.leaNo=leaNo;
            String base = request.getSession().getServletContext().getRealPath("/") + "upload/" + leaNo+sourceName;
//         将文件上传到临时目录
            if (!new File(request.getSession().getServletContext().getRealPath("/") + "upload/").exists()){
                new File(request.getSession().getServletContext().getRealPath("/") + "upload/").mkdirs();
            }
            File upload2 = new File(base);
            //保存到服务器中
            if (saveTo(file, upload2)) return msg="0";
            /* 封装信息 */
            Leave leave=new Leave();leave.setLeaNo(leaNo);
            Upload upload=new Upload();
            upload.setUpath(leaNo+sourceName);
            upload.setLeaNo(leave);
            //存放路径
            try{
                uploadService.add(upload);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            return msg;

        }
    /**
     * 获取个人请假信息
     * @param httpSession 会话对象
     * @param currentPage 是否分页
     * @return
     */
    @RequestMapping("/getPersonInfo")
    public ModelAndView getPersonInfo(HttpSession httpSession,@RequestParam(value = "currentPage",required = false) Integer currentPage){
        ModelAndView modelAndView=new ModelAndView("/webpage/getPersonInfo");
        /* 创建所需参数 */
        Leave leave=new Leave();
        if (!CommonUtil.isNull(currentPage)){
            leave.setCurrentPage(currentPage);
        }
//        Map<String,List<?>> map=new HashMap<>();
        leave.setPerson((User)httpSession.getAttribute("user"));
        /* 调用方法查询 */
        List<Leave> list=leaveService.getPerson(leave);
        for(int i=0;i<list.size();i++){
            list.get(i).setList(uploadService.query(list.get(i).getLeaNo()));
        }
        modelAndView.addObject("personList",list);
        return modelAndView;
    }

    /**
     * 获取单个请假信息
     * @param sid 请假单号
     * @param httpSession 会话对象
     * @return
     */
    @RequestMapping("/get/{sid}")
    public ModelAndView get(@PathVariable String sid,HttpSession httpSession){
        ModelAndView modelAndView=new ModelAndView("/webpage/getOnePerson");
        //调用方法获取数据
        Leave leave=leaveService.get(sid);
        modelAndView.addObject("person",leave);
        List<Upload> upl=uploadService.query(leave.getLeaNo());
        modelAndView.addObject("upl",upl);
        return modelAndView;
    }

    /**
     * 修改请假单信息
     * @param leave 请假单对象
     * @param file 附件
     * @param session 会话对象
     * @return
     */
    @RequestMapping("/updateInfo")
    public ModelAndView updateInfo(Leave leave, @RequestParam(value = "file",required = false)MultipartFile file, HttpSession session){
        ModelAndView mav = new ModelAndView(new RedirectView("getPersonInfo"));
        //修改状态码
        if (leave.getStateCode()==3){
            leave.setStateCode(2);
            try{
                leaveService.updateCode(leave);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /* 判断是否为空 */
        if (!file.isEmpty()) {
            String sourceName = file.getOriginalFilename(); // 原始文件名

            Leave leave1=leaveService.get(leave.getId());//获取所需对象
            String base = session.getServletContext().getRealPath("/") + "upload/" + leave1.getLeaNo()+sourceName;
//         将文件上传到临时目录
            File upload2 = new File(base);
            //保存到服务器中
            if (saveTo(file, mav, upload2)) return mav;
            /* 封装信息 */
            Upload upload=new Upload();
            upload.setUpath(leave1.getLeaNo()+sourceName);
            upload.setLeaNo(leave1);
            //存放路径
            uploadService.add(upload);
        }
        //调用方法修改若有异常则跳转
        try{
            leaveService.update(leave);
            session.setAttribute("updateMes","");
        }catch (Exception e){
            log.error(e.getMessage());
            session.setAttribute("updateMes","修改失败！");
        }
        return mav;
    }

    //用于判断是否上传成功
    private boolean saveTo(@RequestParam(value = "file", required = false) MultipartFile file, ModelAndView mav, File upload2) {
        try {
            file.transferTo(upload2);//上传
        } catch (IOException e) {
            mav.setViewName("/webpage/leave");
            mav.addObject("AddMsg", "文件上传失败，请联系管理员");
            return true;
        }
        return false;
    }
 //用于判断是否上传成功
    private boolean saveTo(@RequestParam(value = "file", required = false) MultipartFile file, File upload2) {
        try {
            file.transferTo(upload2);//上传
        } catch (IOException e) {
            return true;
        }
        return false;
    }

    /**
     * 删除附件
     * @param sid 请假单信息
     * @return
     */
    @RequestMapping(value = "/delUpload/{sid}")
    public ModelAndView delUpload(@PathVariable("sid") Serializable sid){
        ModelAndView mav = new ModelAndView(new RedirectView("../getPersonInfo"));
        //调用方法删除
        uploadService.del(sid);
        return mav;
    }
}
