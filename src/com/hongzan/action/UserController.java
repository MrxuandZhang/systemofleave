package com.hongzan.action;

import com.hongzan.entity.Leave;
import com.hongzan.entity.User;
import com.hongzan.service.UserService;
import com.hongzan.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController  {
    @Resource
    private UserService userService;

    /**
     * 用户映射
     * 到获取登录页
     * @param user 用户对象
     * @return 模型视图
     */
    @RequestMapping("/index")
    public ModelAndView index(@ModelAttribute("u") User user){
        ModelAndView modelAndView=new ModelAndView("webpage/demo");
        return modelAndView;
    }


    /**
     * 映射登录
     * @param user 用户对象
     * @param httpSession 会话对象
     * @return 模型视图
     */
    @RequestMapping("/login")
    public ModelAndView login(@ModelAttribute("u") User user, HttpSession httpSession){
        ModelAndView modelAndView=null;
        /* 调用对应的方法判断是否存在此对象 */
        User loginUser=userService.login(user);
        if (!CommonUtil.isNull(loginUser)){
            httpSession.setAttribute("user",loginUser);
            httpSession.removeAttribute("loginMeg");
            modelAndView=new ModelAndView("/webpage/mianManage");
        }else{
            /* 不存在则到登录页 */
            modelAndView=new ModelAndView("/webpage/demo");
            modelAndView.addObject("loginMsg","编号或者密码错误！");
        }
        return modelAndView;
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/queryUser")
    public ModelAndView query(){
        ModelAndView modelAndView=new ModelAndView("/webpage/login");
        User user=new User();
        Leave leave=new Leave();
        user.setLeaNo(leave);
        modelAndView.addObject("users",userService.query(user,"0","0"));
        return modelAndView;
    }
}
