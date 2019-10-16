<#import "parts/common.ftl" as c>
<@c.page>
    List of Users
    <#if users??>
        <table>
            <thead>
            <tr>
                <th>user login</th>
                <th>user role</th>
                <th>command</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a href="/users/${user.id}">edit user</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>