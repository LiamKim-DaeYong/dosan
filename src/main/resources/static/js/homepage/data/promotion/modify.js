$(document).ready(function () {
    $(window).on('beforeunload', function () {
        return "작성 중인 글이 존재합니다. 페이지를 나가시겠습니까?";
    })

    if (code != null) {
        let html = `<iframe width="560" height="315" src="https://www.youtube.com/embed/${code}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>`;

        $("#code").val(html)
    }

    $("#code").on('propertychange change keyup paste input', function () {
        let video = $(this).val();
        ACTIONS.VIDEO(video)
    })
})

ACTIONS = {
    SAVE: function () {
        if (!$("#title").val() || $("#title").val() === "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return false;
        }

        if (!$("#code").val() || $("#code").val() === "") {
            alert("소스코드를 입력해주세요.");
            $("#code").focus();
            return false;
        }

        var video = $("#code").val();
        let checkVideo = video.substr(0, 7);
        if (checkVideo != '<iframe') {
            alert("올바른 소스정보가 아닙니다.");
            $("#code").focus();
            return false;
        } else {
            let videoCode = video.substr(68, 11);
            $("#realCode").val(videoCode);

            if (confirm("홍보동영상을 등록하시겠습니까?")) {
                $(window).off('beforeunload');
                document.getElementById("promotion_form").submit();
            } else {
                return false;
            }
        }
    },
    VIDEO: function (video) {
        let parent = document.querySelector("#youtube_box");

        while (parent.hasChildNodes()) {
            parent.removeChild(parent.firstChild);
        }

        $("#realCode").val("");
        let checkVideo = video.substr(0, 7);
        if (checkVideo.length >= 7) {
            if (checkVideo != '<iframe') {
                $("#youtube_box").append(`<div><span>올바른 소스가 아닙니다.</span></div>`);
            } else {
                $("#youtube_box").append(`<div>${video}</div>`);
            }
        }
    }
}