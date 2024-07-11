package com.projects.apple_crypto.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.projects.apple_crypto.entities.RequestInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor{
    
    private List<RequestInfo> requestInfoList = new ArrayList<>();

    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler
    ) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler,
        @Nullable Exception ex
    ) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        RequestInfo requestInfo = new RequestInfo(LocalDateTime.now(), request.getRequestURI(), response.getStatus(), duration);
        requestInfoList.add(requestInfo);

        if(requestInfoList.size() > 100) {
            requestInfoList.remove(0); // keep only 100 recent requests
        }
    }

    public List<RequestInfo> getRequestInfoList() {
        return requestInfoList;
    }
}
