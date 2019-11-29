<#assign ctx="${request.contextPath}">
<li class="active">
    <a href="#">
        <i class="fa fa-home"></i>
        <span class="nav-label">权限管理</span> <span class="fa arrow">
	  </span>
    </a>

    <ul class="nav nav-second-level">

        <li>
            <a class="J_menuItem" href="${ctx}/admin/role/search" data-index="1">角色管理</a>
        </li>
        <li>
            <a class="J_menuItem" href="${ctx}/admin/teacher/search" data-index="2">教师管理</a>
        </li>
        <li>
            <a class="J_menuItem" href="${ctx}/admin/menu/search" data-index="2">菜单管理</a>
        </li>

        <li>
            <a class="J_menuItem" href="${ctx}/admin/student/search" data-index="2">学生管理</a>
        </li>
        <li>
            <a class="J_menuItem" href="${ctx}/admin/classes/search" data-index="2">班级管理</a>
        </li>
        <li>
            <a class="J_menuItem" href="${ctx}/admin/major/search" data-index="2">专业管理</a>
        </li>

    </ul>
</li>


