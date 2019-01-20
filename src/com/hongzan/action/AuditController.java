package com.hongzan.action;

import com.hongzan.entity.Audit;
import com.hongzan.entity.Leave;
import com.hongzan.entity.User;
import com.hongzan.service.AuditService;
import com.hongzan.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 审批控制器
 */
@Controller
@RequestMapping("/audit")
public class AuditController {
    @Resource
    private AuditService auditService;//注入审核业务对象

    /**
     * 获取历史记录
     * @param httpSession 会话对象
     * @return
     */
    @RequestMapping("/getHistory")
    public ModelAndView getHistory(HttpSession httpSession,@RequestParam(value = "currentPage",required = false)Integer currentPage){
        ModelAndView modelAndView=new ModelAndView("/webpage/getHistory");
        /* 创建所需对象 */
        Audit audit=new Audit();
        if (!CommonUtil.isNull(currentPage)) {audit.setCurrentPage(currentPage);}
        User user=(User)httpSession.getAttribute("user");
        audit.setLevel(user);
        audit.setAname(user.getUname());
        //设置查询结果数据
        modelAndView.addObject("history",auditService.queryHistory(audit));
        return modelAndView;
    }

    /**
     * 获取所有可以审核的单子
     * @param httpSession 会话对象
     * @param currentPage 页面数
     * @return 模型视图
     */
    @RequestMapping("/getNoAuditInfo")
    public ModelAndView getNoAuditInfo(HttpSession httpSession,@RequestParam(value = "currentPage",required = false)Integer currentPage){
        ModelAndView modelAndView=new ModelAndView("webpage/auditInfo");
        /* 从会话对象中获取对象并且封装信息 */
        User user=(User)httpSession.getAttribute("user");
        Audit audit=new Audit();audit.setLevel(user);
        /* 判断是否具有分页条件并调用方法查询 */
        if (!CommonUtil.isNull(currentPage)){audit.setCurrentPage(currentPage);}
        modelAndView.addObject("NoauditInfo",auditService.queryNoAudit(audit));
        return modelAndView;
    }

    /**
     * 驳回请假
     * @param sid 请假单号
     * @return
     */
    @RequestMapping("/updateCode/{sid}")
    public ModelAndView updateCode(@PathVariable("sid") String sid){
      ModelAndView mav=new ModelAndView(new RedirectView("../getNoAuditInfo"));
      /* 封装信息 */
      Audit audit=new Audit();
      Leave leave=new Leave();
      leave.setLeaNo(sid);
      audit.setLeaNo(leave);
      audit.setAtime(new Date().toLocaleString());
      //修改状态以及时间
      auditService.update(audit);
      return mav;
    }

    /**
     * 修改建议 同意请假
     * @param suggest 建议
     * @param leaNo 请假单号
     * @param httpSession 会话对象
     * @return
     */
    @RequestMapping("/updateMeg")
    public ModelAndView updateMeg(@RequestParam("suggest") String suggest,@RequestParam("leaNo")String leaNo, HttpSession httpSession){
        ModelAndView modelAndView=new ModelAndView(new RedirectView("getNoAuditInfo"));
        /* 封装信息 */
        Audit audit=new Audit();
        Leave leave=new Leave();
        leave.setLeaNo(leaNo);
        audit.setLeaNo(leave);
        audit.setSuggest(suggest);
        audit.setLevel((User)httpSession.getAttribute("user"));
        audit.setAtime(new Date().toLocaleString());
        //修改
        auditService.updatemes(audit);
        return modelAndView;
    }

    /**
     * 获取对应请假单历史信息
     * @param leaNo 请假单号
     * @return 模型视图
     */
    @RequestMapping("/getLeaveHistory/{leaNo}")
    public ModelAndView getLeaveHistory(@PathVariable("leaNo") String leaNo){
        ModelAndView modelAndView=new ModelAndView("webpage/getLeaveHistory");
        /* 封装信息，查询 */
        Leave leave=new Leave();leave.setLeaNo(leaNo);
        Audit audit=new Audit();audit.setLeaNo(leave);
        modelAndView.addObject("leaveHistory",auditService.getLeaveHistory(audit));
        return modelAndView;
    }

}
