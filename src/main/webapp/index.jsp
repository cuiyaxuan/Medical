<%--
  Created by IntelliJ IDEA.
  User: WangXinYu
  Date: 2018/2/25
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>主页</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="${ctx}/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${ctx}/components/amazeui/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="${ctx}/components/amazeui/css/amazeui.min.css"/>
    <%--表格插件 css--%>
    <link rel="stylesheet" href="${ctx}/components/amazeui/css/amazeui.datatables.min.css">
    <link rel="stylesheet" href="${ctx}/components/amazeui/css/admin.css">
    <link rel="stylesheet" href="${ctx}/components/amazeui/css/app.css">
    <link rel="stylesheet" href="${ctx}/css/index.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">
    <link rel="stylesheet" href="${ctx}/components/sider/sider.css">

    <script src="${ctx}/components/echarts/echarts.min.js"></script>
    <script src="${ctx}/components/echarts/echarts-gl.min.js"></script>
</head>
<body data-type="index">


<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <a href="javascript:;" class="tpl-logo" style="position: relative;top: 40%">
            <img src="${ctx}/img/logo.png" alt="">
        </a>
    </div>
    <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right" style="position: absolute;top: 40%">

    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
            <li><a href="javascript:" onclick="returnLogin()" class="tpl-header-list-link"><span
                    class="am-icon-sign-out tpl-header-list-ico-out-size"></span>登出</a></li>
        </ul>
    </div>
</header>


<div class="tpl-page-container tpl-page-header-fixed">


    <div class="tpl-left-nav tpl-left-nav-hover">
        <div class="tpl-left-nav-title">
            菜单列表
        </div>
        <div class="tpl-left-nav-list">
            <ul class="tpl-left-nav-menu">
                <li class="tpl-left-nav-item" data-type="home">
                    <a class="nav-link active" data-html="home" onclick="switchPage(this)">
                        <i class="am-icon-home"></i>
                        <span>首页</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item " data-type="doctor">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-table"></i>
                        <span>医生工作站</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: none;">
                        <li>
                            <a href="javascript:void(0)" data-html="patientManage" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病人管理</span>
                            </a>

                            <a href="javascript:void(0)" data-html="mRecordManage" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病历管理</span>
                            </a><a href="javascript:void(0)" data-html="workRemind" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>工作提醒</span>
                        </a><%--<a href="javascript:void(0)"  data-html="paramSetting" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>参数设置</span>
                        </a>--%>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item" data-type="nurse">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-th-large"></i>
                        <span>护士工作站</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: none;">
                        <li>
                            <a href="javascript:void(0)" data-html="patientManage" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病人管理</span>

                            </a>

                            <a href="javascript:void(0)" data-html="nursingClerical" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>护理文书</span>
                            </a><a href="javascript:void(0)" data-html="workRemind" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>工作提醒</span>
                        </a><%--<a href="javascript:void(0)" data-html="paramSetting" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>参数设置</span>
                        </a>--%>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item" data-type="medicalRecord">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-list-alt"></i>
                        <span>病历管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: none;">
                        <li>
                            <a href="javascript:void(0)" data-html="recordScanned" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病历浏览查询</span>
                            </a>

                            <a href="javascript:void(0)" data-html="recordSeal" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病历封存</span>
                            </a><a href="javascript:void(0)" data-html="recordDeblock" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>病历解封</span>
                        </a><a href="javascript:void(0)" data-html="recordCheck" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>病历签收检查</span>
                        </a>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item" data-type="department">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-hospital-o"></i>
                        <span>质控工作站</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: none;">
                        <li>
                            <a href="javascript:void(0)" data-html="recordScanned" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病历浏览查询</span>
                            </a>

                            <a href="javascript:void(0)" data-html="recordSeal" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病历封存</span>
                            </a><a href="javascript:void(0)" data-html="recordQuality" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>病历质量监控</span>
                        </a>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item" data-type="data">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-bar-chart"></i>
                        <span>数据统计分析</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: none;">
                        <li>
                            <a href="javascript:void(0)" data-html="infection" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>传染病统计</span>
                            </a>

                            <a href="javascript:void(0)" data-html="patient" onclick="switchPage(this)">
                                <i class="am-icon-angle-right"></i>
                                <span>病人统计</span>
                            </a><a href="javascript:void(0)" data-html="record" onclick="switchPage(this)">
                            <i class="am-icon-angle-right"></i>
                            <span>病历统计</span>
                        </a>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item" data-type="permission">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-tasks"></i>
                        <span>系统权限管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" style="display: none;">
                        <li>
                            <a href="javascript:void(0)" data-html="role" onclick="switchPage(this);">
                                <i class="am-icon-angle-right"></i>
                                <span>权限管理</span>
                            </a>

                            <a href="javascript:void(0)" data-html="logs" onclick="switchPage(this);">
                                <i class="am-icon-angle-right"></i>
                                <span>日志管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>


    <div class="tpl-content-wrapper" id="main-content">

    </div>
</div>
<%--模态框--%>
<div class="am-popup" id="common-modal" style="z-index: 99999;height: 70vh">
    <div class="am-popup-inner">
        <div class="am-popup-hd">
            <h4 class="am-popup-title">在远方</h4>
            <span data-am-modal-close
                  class="am-close">&times;</span>
        </div>
        <div class="am-popup-bd">
            ...
        </div>
    </div>
</div>
<%--confirm 模态框--%>
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm" style="z-index: 99999">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">提示</div>
        <div class="am-modal-bd">
            你，确定要删除这条记录吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn am-confirm-yes" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<%--alert 模态框--%>
<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert" style="z-index: 99999">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">Amaze UI</div>
        <div class="am-modal-bd">
            Hello world！
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>
</body>
<script>
    var contextPath = "${ctx}";
</script>
<script src="${ctx}/components/amazeui/js/jquery.min.js"></script>
<script src="${ctx}/components/amazeui/js/amazeui.min.js"></script>
<%--amazeui 整合的dataTables 不好用的话打死他--%>
<script src="${ctx}/components/amazeui/js/amazeui.datatables.min.js"></script>
<script src="${ctx}/components/amazeui/js/dataTables.responsive.min.js"></script>
<script src="${ctx}/components/amazeui/js/fnAjaxReload.js"></script>
<script src="${ctx}/components/amazeui/js/iscroll.js"></script>
<script src="${ctx}/js/index-common.js"></script>
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/js/index.js"></script>
<script src="${ctx}/components/amazeui/js/app.js"></script>

<%---------------------------下面是所有的子页面的js--------------------------------%>

</html>
