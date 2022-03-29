const $errors = {
    valid: function (errors) {
        const fieldErrors = errors["fieldErrors"];

        if (fieldErrors) {
            $("[errors]").remove()
            $("[errorclass]").each(function () {
                $(this).removeClass($(this).attr("errorclass"));
            });

            fieldErrors.forEach(error => {
                const errorField = $("#" + error.field);
                errorField.addClass(errorField.attr("errorclass"));

                let errorMsgField
                if (errorField.attr("editor")) {
                    errorMsgField = $('[placeholder="' + error.field + '"]')
                    errorMsgField.text(error.message);
                } else {
                    errorMsgField = `<span errors="${error.field}" class="modal_error">${error.message}</span>`
                    errorField.after(errorMsgField);
                }
            });
        }
    }
}

export default $errors;
