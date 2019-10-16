<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label">Login:</label>
            <div class="col-sm-10">
                <input type="text" name="username" class="form-control" placeholder="login"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group">
                <label class="col-sm-2 col-form-label">E-mail: </label>
                <div class="col-sm-10">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">register new User</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign in</#if></button>
    </form>
</#macro>

<#macro  logout>
    <form method="post" action="/logout">
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>