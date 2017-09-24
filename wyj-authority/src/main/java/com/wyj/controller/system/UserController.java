package com.wyj.controller.system;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.entity.Retval;
import com.wyj.entity.system.User;
import com.wyj.service.system.UserService;
import com.wyj.utils.ShiroUtils;

/**
 * 
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年8月15日 下午9:45:55
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index() {
        return "/user/user";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(@RequestParam(value = "offset", required = true, defaultValue = "1") Integer page, @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userService.list(new User());
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return JSON.toJSONString(pageInfo.getList());
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Retval save(User user) {
        Retval retval = Retval.newInstance();
        try {
            if (user.getUserId() == null) {
                userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Retval edit(@PathVariable String id) {
        Retval retval = Retval.newInstance();
        User user = userService.getUserById(Long.valueOf(id));
        retval.put("obj", user);
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Retval remove(@RequestParam Long[] ids) {
        Retval retval = Retval.newInstance();
        try {
            userService.batchRemoveUser(ids);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            retval.fail(e.getMessage());
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Retval updatePassword(String oldPassword, String newPassword) {
        HashMap<String, Object> query = new HashMap<String, Object>();
        query.put("userId", ShiroUtils.getUserId());
        query.put("oldPassword", oldPassword);
        query.put("newPassword", newPassword);
        Retval retval = userService.updatePasswordByUser(query);
        return retval;
    }

}
