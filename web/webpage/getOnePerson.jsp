<%@ page import="com.hongzan.util.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/error.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<%if (CommonUtil.isNull(request.getSession().getAttribute("user"))){ response.sendRedirect("../user/index"); request.getSession().setAttribute("loginMeg","请登录!");}%>
<html>
<head>
    <title>修改个人信息</title>
</head>
<style>
    .file {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
    }
    .file input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }
    .file:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        cursor: pointer;
        text-decoration: none;
    }
</style>

<link rel="stylesheet" href="/Css/layui.css">
<link rel="stylesheet" href="/Css/perosn.css">
<script src="/Js/layui.js"></script>
<script src="/Js/layui.all.js"></script>
<script src="/Js/jquery-3.3.1.js"></script>
<body>
<br>
<fieldset style="margin-top: 30px; margin-left: 34px;  width: 1073px;  height: 481px;  padding-top: 29px;">
    <legend>请假申请</legend>
<form class="layui-form" action="/leave/updateInfo" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${person.id}"/>
    <input type="hidden" name="leaNo" value="${person.leaNo}" id="id_leaNo"/>

    <input type="hidden" name="stateCode" value="${person.stateCode}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">开始时间:</label>
        <div class="layui-input-block" style="width: 27%;">
            <input type="text"  required  lay-verify="required" name="begin" value="${person.begin.substring(0,person.begin.indexOf('.'))}" readonly id="begin" autocomplete="off" class="layui-input">
            <s:errors path="begin"></s:errors>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block" style="width: 27%;">
            <input type="text"  required  lay-verify="required" name="end" value="${person.end.substring(0,person.end.indexOf('.'))}" readonly id="end" autocomplete="off" class="layui-input">
            <s:errors path="end"></s:errors>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">请假类型</label>
        <div class="layui-input-block" style="width: 27%;">
            <select name="reason" lay-verify="required">
                <option value=""></option>
                <c:forEach items="${reason}" var="vars" varStatus="vs">
                    <option value="${vars}" ${vars==person.reason?"selected":""}>${vars}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item" >
        <label class="layui-form-label">总时间数:</label>
        <div class="layui-input-block" style="width: 27%;">
            <input type="text" readonly id="id_dateTime" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" >请假原因</label>
        <div class="layui-input-block" style="width: 27%;">
            <textarea name="reasonContent" placeholder="请输入内容" required class="layui-textarea">${person.reasonContent}</textarea>
        </div>
    </div>
    <div style="margin-left: 110px; margin-bottom: 20px;">
        <c:forEach items="${upl}" var="up" varStatus="vars">
            <font>附件:${up.upath}</font>
            <a href="/leave/delUpload/${up.uid}" id="id_delUpload" class="layui-btn">删除附件</a>
            <c:if test="${vars.count>3}">
                <br/>
            </c:if>
        </c:forEach>
    </div>
    <font id="delUploadMsg"></font>

    <div class="layui-upload" style=" position: fixed;  top: 117px;  left: 579px;">
        <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr><th>文件名</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr></thead>
                <tbody id="demoList"></tbody>
            </table>
        </div>
        <button type="button" class="layui-btn" id="testListAction">开始上传</button>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn"  lay-filter="formDemo" onclick="this.form.submit();">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        ${updateMes}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>

    </div>

</form>
</fieldset>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            return false;
        });
        //手动渲染.由于网页为动态所以需要手动渲染
        form.render();
    });

    layui.use('upload', function () {
        var $ = layui.jquery ,
            upload = layui.upload;
        //多文件列表示例
        var demoListView = $('#demoList')
            , uploadListIns = upload.render({
            elem: '#testList'
            , url: '/leave/addUpload'
            , accept: 'file'
            ,data:{"leaNo":$("#id_leaNo").val()}
            , multiple: true
            , auto: false
            , bindAction: '#testListAction'
            , choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td>' + file.name + '</td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            , done: function (res, index, upload) {
                if (res== "1") { //上传成功
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            , error: function (index, upload) {
                var tr = demoListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });


    });
</script>
</body>
<script src="/Js/jquery-3.3.1.js"></script>
<script>
    $(document).ready(function () {
        getTime();
    });

    $(function(){

        layui.use('laydate', function() {
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#begin',
                min:"00:00:00",
                max:365
                //回调函数
                , done: function (value, date, endDate) {
                    // ins1.hint(value); //在控件上弹出value值
                    $("#begin").val(value);
                    if ($("#end").val()!=null&&$("#end").val()!=''){
                        getTime();
                    }
                }
                , type: 'datetime'
            });
            laydate.render({
                elem: '#end',
                min:"00:00:00",
                max:365
                //回调函数
                , done: function (value, date, endDate) {
                    $("#end").val(value);
                    getTime();
                }
                , type: 'datetime'
            });
        })

    })


    //计算请假时间
    function getTime() {
        var _beginTime=$("#begin").val();
        var _endTime=$("#end").val();
        // alert(_beginTime+":"+_endTime)
        var dateBegin = new Date(_beginTime.replace(/-/g, "/"));//将-转化为/，使用new Date
        var dateEnd = new Date(_endTime.replace(/-/g, "/"));
        var dateDiff = dateEnd.getTime() - dateBegin.getTime();//时间差的毫秒数
        var dayDiff = Math.abs(Math.floor(dateDiff / (24 * 3600 * 1000))) ;//计算出相差天数
        var leave1=dateDiff%(24*3600*1000)    //计算天数后剩余的毫秒数
        var hours=Math.abs(Math.floor(leave1/(3600*1000)))//计算出小时数
        //计算相差分钟数
        var leave2=leave1%(3600*1000)    //计算小时数后剩余的毫秒数
        var minutes=Math.abs(Math.floor(leave2/(60*1000)))//计算相差分钟数
        //计算相差秒数
        var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
        var seconds=Math.round(leave3/1000)
        if (!isNaN(dayDiff)&&!isNaN(hours)&&!isNaN(minutes)&&!isNaN(seconds)) {
            $("#id_dateTime").val(dayDiff + "天 " + hours + "小时 " + minutes + " 分钟" + seconds + " 秒");
        }
    }

    function getFileName() {
        var _name=$("input[type=file]").val()
        $("#sel_filename").html(_name);
    }

    function toSave() {
        $("#id_hide").val("yes");
        $("#leaveForm").submit();
    }
</script>
</html>
