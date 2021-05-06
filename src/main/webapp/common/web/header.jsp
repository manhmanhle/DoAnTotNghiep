 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
 <c:url var="test" value="/trang-chu/test "/>
 <div id="header">
            <!-- title  -->
            <div id="title">
                <p class="text-white text-center">Trang chủ <span>( Phụ tùng trang)</span></p>
            </div>
            <div>
                <!-- logo  -->
                <div id="logo">
                    <a href="">
                        <img src="https://www.yasteq.com/img/yasteq/logo.png" alt="">
                    </a>
                </div>
                <div id="div-menu">
                    <!-- menu  -->
                    <ul id="menu">
                        
                        <li>
                            <a href="">Báo cáo TCCB <i class="fas fa-caret-down"></i></a>
                            <ul id="submenu">
                                <li>
                                    <a href="/spring-mvc/trang-chu/report">Cán bộ giảng viên</a>
                                </li>
                              
                            </ul>
                        </li>
                        
                        <li>
                            <a href="">Dashboard <i class="fas fa-caret-down"></i></a>
                            <ul id="submenu">
                                <li>
                                    <a href="/spring-mvc/trang-chu/dashboard">Đào tạo</a>
                                </li>
                               
                            </ul>
                        </li>
                        <li>
                            <a href="">Hướng dẫn dử dụng</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>