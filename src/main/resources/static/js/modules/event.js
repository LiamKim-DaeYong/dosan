const $event = {
    init: function () {
        this.inputAutocompleteOff();
        $checkBox.init();
        $files.init();
    },

    inputAutocompleteOff: function () {
        $('input').prop("autocomplete", "off");
    }
}

export default $event;
