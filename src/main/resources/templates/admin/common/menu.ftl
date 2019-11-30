<#assign ctx="${request.contextPath}">

<#list currentTeacherMenu! as m>
<li class="active">

    <a>
        <#if m.menuName == "系统管理">
            <i class="fa fa-home"></i>
            <#elseif m.menuName="权限管理">
                <i class="fa fa-shield"></i>
            <#elseif m.menuName="会员管理">
                <i class="fa fa-male"></i>
            <#elseif m.menuName="学生管理">
                <i class="fa fa-user"></i>
            <#elseif m.menuName="教师管理">
                <i class="fa fa-graduation-cap"></i>
            <#else >
        </#if>
        <span class="nav-label">${m.menuName!}</span> <span class="fa arrow">
	  </span>
    </a>

    <ul class="nav nav-second-level">
        <#list m.childrens! as c>
        <li>
            <a class="J_menuItem" href="${ctx}${c.actionName!}" data-index="1">${c.menuName!}</a>
        </li>
        </#list>
    </ul>

</li>

</#list>
