var page = {
    saveForm: function () {
        modal.openModal({
            title: '교육과목 등록',
            target: 'modal',
            height: 200,
            width: 400,
            url: `/setting/curriculum/${type}/add`
        })
    }
}
