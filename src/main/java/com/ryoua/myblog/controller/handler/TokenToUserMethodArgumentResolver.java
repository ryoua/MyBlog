package com.ryoua.myblog.controller.handler;

import com.ryoua.myblog.controller.annotation.TokenToUser;
import com.ryoua.myblog.entity.AdminUser;
import com.ryoua.myblog.service.AdminUserService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author ryoua Created on 2019-05-03
 */
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private AdminUserService adminUserService;

    public TokenToUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            AdminUser user = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token)) {
                user = adminUserService.getAdminUserByToken(token);
            }
            return user;
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}

