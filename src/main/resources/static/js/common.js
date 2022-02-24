api = {
    addressApi: function (zipCodeId, addressId, detailId) {
        new daum.Postcode({
            oncomplete: function (data) { //선택시 입력값 세팅
                document.getElementById(zipCodeId).value = data.zonecode; // 주소 넣기
                document.getElementById(addressId).value = data.address; // 주소 넣기
                document.getElementById(detailId).focus();
            }
        }).open();
    },
}

modal = {
    openModal: function (options) {
        var defaultOptions = {
            title: 'title',
        }

        options = $.extend({}, defaultOptions, options);

        console.log(options)

        $.get(options.url, function (data) {
            this.target = $("#" + options.target);
            this.target.find('.modal-content').css('height', options.height);
            this.target.find('.modal-content').css('width', options.width);

            this.target.find('.modal-title').html(options.title);
            this.target.find('.modal-body').html(data);

            this.target.removeClass('fade');
        });
    },
    closeModal: function () {
        $('#modal').addClass('fade');
    },
    submit: function () {
        var form = $($('#modal').find('form')[0]);

        $.post(form.attr("action"), form.serialize(), function (data) {
            console.log(data)
            // $('#modal').find('.modal-body').html(data);
        });
    }
};
