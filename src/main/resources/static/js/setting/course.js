var page = {
    modalOpen: function (id) {
        modal.openModal({
            target: id,
            height: 200,
            width: 400,
            title: `${description} 교육과목`,
            url: `/setting/course/${type}/add`
        })
    }
}
