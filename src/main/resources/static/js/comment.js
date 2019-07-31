function addcomment() {
    var questionId = $("#parentId").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);

}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment/save",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                alert(response.message);
                window.location.reload();
            } else {
                alert(response.message);
                return;
            }
        },
        dataType: "json"
    });
}