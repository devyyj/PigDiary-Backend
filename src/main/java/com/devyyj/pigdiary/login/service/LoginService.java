package com.devyyj.pigdiary.login.service;

import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    public void clearCookie(HttpServletResponse response);
}
