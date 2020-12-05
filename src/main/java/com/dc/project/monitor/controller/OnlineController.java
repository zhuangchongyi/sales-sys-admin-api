package com.dc.project.monitor.controller;

import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuangcy
 * @date 2020/11/3 17:22
 * @description 在线用户监控
 */
@Slf4j
@RestController
@RequestMapping("/monitor/online")
public class OnlineController {
    @Autowired
    private SessionDAO sessionDAO;

    @RequiresPermissions("monitor:online:list")
    @GetMapping
    public R list() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        List<Map<String, Object>> mapList = sessions.stream()
                .map(session -> {
                    SimplePrincipalCollection attribute = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                    if (attribute != null) {
                        Object principal = attribute.getPrimaryPrincipal();
                        if (principal instanceof SysUser) {
                            SysUser user = (SysUser) principal;
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", session.getId());
                            map.put("username", user.getUsername());
                            map.put("startTimestamp", session.getStartTimestamp());
                            map.put("lastAccessTime", session.getLastAccessTime());
                            map.put("host", session.getHost());
                            map.put("timeout", new Date(session.getLastAccessTime().getTime() + session.getTimeout()));
                            return map;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                    .sorted((o1, o2) -> o2.get("startTimestamp").toString().compareTo(o1.get("startTimestamp").toString()))
                .collect(Collectors.toList());
        return R.success().data(mapList);
    }

    @GetMapping("/count")
    public R count() {
        return R.success().data(sessionDAO.getActiveSessions().stream().filter(predicate -> {
            SimplePrincipalCollection attribute = (SimplePrincipalCollection) predicate.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            Object principal = attribute.getPrimaryPrincipal();
            return principal instanceof SysUser;
        }).count());
    }

    @RequiresPermissions("monitor:online:kickout")
    @RepeatSubmit
    @DeleteMapping
    public R kickOut(String id) {
        Session session = sessionDAO.readSession(id);
        if (session != null) {
            sessionDAO.delete(session);
            log.info(String.format("sessionId=%s 被踢出", id));
        }
        return R.success();
    }


}
