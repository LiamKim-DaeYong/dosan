const $search = {
    DATE: 'DATE', ALL: 'ALL',

    search: function (type) {
        switch (type) {
            case this.DATE :
                this.dateSearch();
                break;

            case this.ALL :
                this.allSearch();
                break;
        }
    },

    dateSearch: function () {
        var searchArr = this.findChildren($("[search='search']"));
        var dateObj = {};

        searchArr.forEach(search => {
            switch (search.tagName.toLowerCase()) {
                case "input" :
                    if (search.type !== 'date') {
                        $(search).prop('value', '');
                    } else {
                        dateObj[search.id] = search.value;
                    }
                    break;

                case "select" :
                    $(search).children(":eq(0)").prop("selected", true);
                    break;
            }
        })

        if (this.dateValidation(dateObj)) {
            $("form").submit();
        }
    },

    allSearch: function () {
        var searchArr = this.findChildren($("[search='search']"));
        var dateObj = {};

        searchArr.forEach(search => {
            if (search.type == 'date') {
                dateObj[search.id] = search.value;
            }
        })

        if (this.dateValidation(dateObj)) {
            $("form").submit();
        }
    },

    init: function () {
        $url.redirect();
    },

    dateValidation: function (dateObj) {
        var result = true;
        if ((dateObj["startDate"] != "" && dateObj["endDate"] != "")
            && (dateObj["startDate"] > dateObj["endDate"])) {
            alert("날짜를 다시 입력해 주세요.");
            result = false;
        }
        return result;
    },

    findChildren: function (parent) {
        var _this = this;
        var searchList = [];

        $.each(parent, function () {
            switch (this.tagName.toLowerCase()) {
                case "div" :
                    var childList = _this.findChildren($(this).children());
                    childList.forEach(child => searchList.push(child));
                    break;

                case "input" : case "select" :
                    searchList.push(this);
                    break;
            }
        })

        return searchList;
    }
}

export default $search;
