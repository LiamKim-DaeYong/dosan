var pageObj = {
    dataList: [
        {key: '김대용', value: '대리'},
        {key: '이현승', value: '사원'},
        {key: '이혜린', value: '주임(진)'},
        {key: '조선민', value: '대리'},
        {key: '조선민', value: '주임'},
        {key: '조선민', value: '사원'},
    ],
    init: function () {
        $autoSearch.init(this.dataList);
    },

    addRow(targetId) {
        $table.addRow(targetId);
        $autoSearch.init(this.dataList);
    },
}

pageObj.pageStart = function () {
    pageObj.init();
};
