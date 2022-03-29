const $form = {
    getData: function (target = $("form")) {
        const formData = new FormData(target[0]);

        if ($(".file-input").length) {
            formData.delete("files");

            for (const file of $files.getFiles()) {
                formData.append("files", file);
            }

            return formData;
        } else {
            return Object.fromEntries(formData);
        }
    }
}

export default $form;
