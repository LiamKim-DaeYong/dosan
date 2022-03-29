const $modal = {
    defaultOption: {
        height: 400,
        width: 400,
        title: 'title'
    },
    init: function (element, options) {
        this.defaultOption = $.extend({}, this.defaultOption, options);

        this.target = $(element);
        this.$content = this.target.find('.modal-content');
        this.$title = this.target.find('.modal-header h3');
        this.$body = this.target.find('.modal-body');
        this.$content.css({'height': this.defaultOption.height, 'width': this.defaultOption.width});

        this.setTitle(this.defaultOption.title);
        this.setContent(this.defaultOption.path);
    },

    open: function (options) {
        this.defaultOption = $.extend({}, this.defaultOption, options);

        this.setTitle(this.defaultOption.title);
        this.setContent(this.defaultOption.path);

        $(this.target).removeClass('fade');
    },

    close: function () {
        this.target.find('input[type=text], textarea').val("");
        this.target.addClass('fade');
    },

    getData: function () {
        var result = {};
        var query = this.target.find('input, textarea').serializeArray();
        query.forEach(function (data) {
            result[data.name] = data.value;
        });

        return result;
    },

    setContent: function (path) {
        if (path) {
            var _this = this;
            $.get(path, function (data) {
                $(_this.$body).html(data);
                $event.init();
            });
        } else {
            $(this.$body).html('<div>Modal Body</div>');
        }
    },

    setTitle: function (title) {
        this.defaultOption.title = title;
        $(this.$title).text(title);
    },

    setData: function (obj) {
        for ([key, value] in obj) {
            $("#" + key).val(value);
        }
    }
}

export default $modal;
