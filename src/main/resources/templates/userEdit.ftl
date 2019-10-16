<#import "parts/common.ftl" as c>
<@c.page>
    User editor
    <form action="/users" method="post">
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <input type="text" name="username" value="${user.username}">
        <input type="hidden" value="${user.id}" name="userId">
        <#list roles as role>
            <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list>
        <button type="submit">Edit current user</button>
    </form>


</@c.page>