<#import "parts/common.ftl" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="" class="form-inline">
                <input type="text" name="filter" class="form-control" placeholder="Input tag"
                       value="${filter?if_exists}"/>
                <button type="submit" class="btn btn-primary ml-2">Search by tag</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#addMessage" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="addMessage">
        <div class="form-group mt-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="input text">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="input tag">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" id="customFile" name="file"/>
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                <button type="submit" class="btn btn-primary ml-2">Send message</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <div>
                    <#if message.filename??>
                        <img src="/img/${message.filename}" alt="Проблемы с отображением" class="card-img-top"/>
                    </#if>
                </div>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No messages yet
        </#list>
    </div>
</@c.page>